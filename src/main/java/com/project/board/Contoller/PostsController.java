package com.project.board.Contoller;

import com.project.board.DTO.posts.PostsResponseDto;
import com.project.board.DTO.posts.PostsSaveRequestDto;
import com.project.board.DTO.posts.PostsUpdateRequestDto;
import com.project.board.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsController {
    private final PostsService postsService;
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto dto){
        return postsService.save(dto);
    }
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }
    //조회
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
    @DeleteMapping("/api/v1/posts/{id}")
    public void delete(@PathVariable("id") Long id){
        postsService.delete(id);
    }
}
