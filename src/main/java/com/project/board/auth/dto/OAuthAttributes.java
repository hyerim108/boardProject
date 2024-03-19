package com.project.board.auth.dto;

import com.project.board.Domain.users.Role;
import com.project.board.Domain.users.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String name,
                           String email){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }
    //OAuth2User 에서 반환하는 사용자 정보는 Map
    //값하나하나를 반환
    public static OAuthAttributes of(String registrationId,String userNameAttributeName,
                                     Map<String, Object> attributes){
        return ofGoogle(userNameAttributeName,attributes);
    }
    public static OAuthAttributes ofGoogle(String usernameAttribute,Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(usernameAttribute)
                .build();
    }
    public Users toEntity(){
        return Users.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
