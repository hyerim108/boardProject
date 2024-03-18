package com.project.board.DTO.posts;

import com.project.board.Domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
    }
}
