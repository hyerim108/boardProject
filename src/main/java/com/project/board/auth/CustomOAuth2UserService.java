package com.project.board.auth;

import com.project.board.Domain.users.Users;
import com.project.board.Domain.users.UsersRepository;
import com.project.board.auth.dto.OAuthAttributes;
import com.project.board.auth.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>
{
    private final UsersRepository usersRepository;
    private final HttpSession httpSession;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest,OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        //로그인진행중인 서비스 구분
        //카카오 구글 등등..
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //OAuth2로그인 진행시 키가 되는 필드값
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        //OAuth2UserService를 통해 가져온 OAuth2User의 attribute담을 클래스
        OAuthAttributes attributes = OAuthAttributes.of(registrationId,userNameAttributeName,oAuth2User.getAttributes());
        //사용자 저장 또는 업데이트
        Users user = saveOrUpdate(attributes);
        httpSession.setAttribute("user",new SessionUser(user));
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }
    private Users saveOrUpdate(OAuthAttributes attributes){
        Users user = usersRepository.findByEmail(attributes.getEmail())
                //구글 사용자 정보 업데이트(이미 가입된)
                .map(entity->entity.update(attributes.getName()))
                .orElse(attributes.toEntity());
        return user;
    }
}
