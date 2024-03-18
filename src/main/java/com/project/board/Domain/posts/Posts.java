package com.project.board.Domain.posts;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor //lombok어노테이션 ->기본생성자 자동추가
@Entity //jpa어노테이션
public class Posts { //실제 DB테이블과 매칭될 클래스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String author;
    @Builder //해당 클래스의 빌더 패턴 클래스를 생성 
    public Posts(String title,String content,String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public void update(String title,String content){
        this.title = title;
        this.content = content;
    }
}
