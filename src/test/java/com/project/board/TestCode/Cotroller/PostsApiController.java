package com.project.board.TestCode.Cotroller;

import com.project.board.DTO.posts.PostsSaveRequestDto;
import com.project.board.DTO.posts.PostsUpdateRequestDto;
import com.project.board.Domain.posts.Posts;
import com.project.board.Domain.posts.PostsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiController {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private PostsRepository postsRepository;
    @Test
    public void posts_save() throws Exception{
        //given
        String title = "title";
        String content ="content";
        String author = "author";

        PostsSaveRequestDto saveRequestDto = new PostsSaveRequestDto(title,content,author);
        String url = "http://localhost:"+port+"/api/v1/posts";
        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url,saveRequestDto,Long.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
    }
    @Test
    public void posts_update() throws Exception{
        //given
        Posts savePosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author").build());
        Long updateId = savePosts.getId();
        String updateTitle="title2";
        String updateContent="content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(updateTitle).content(updateContent)
                .build();
        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity =
                testRestTemplate.exchange(url,HttpMethod.POST,requestEntity,Long.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(updateTitle);
        assertThat(all.get(0).getContent()).isEqualTo(updateContent);
    }
}
