package com.project.board.Domain.users;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    @Builder
    public Users(String name, String email,Role role){
        this.name=name;
        this.email=email;
        this.role = role;
    }
    public Users update(String name){
        this.name = name;
        return this;
    }
    public String getRoleKey(){
        return this.role.getKey();
    }
    public Users toEntity(){
        return Users.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
