package com.project.board.auth.dto;

import com.project.board.Domain.users.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    public SessionUser(Users users){
        this.name= users.getName();
        this.email = users.getEmail();
    }
}
