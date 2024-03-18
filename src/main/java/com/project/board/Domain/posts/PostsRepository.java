package com.project.board.Domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository  extends JpaRepository<Posts,Long> { //DB Layer 접근자 => MyBatis 에서 Dao라고 불림

}
