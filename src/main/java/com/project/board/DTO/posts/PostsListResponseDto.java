package com.project.board.DTO.posts;

import com.project.board.Domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
    }
}
