package com.project.board.auth;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SocailLoginController {
    private final GoogleService googleService;
    @PostMapping("/google")
    public @ResponseBody ResponseEntity<MsgEntity> kakaoLogin(@RequestBody UserVO uservo, HttpServletResponse response) throws Exception{
//        if(!jwtService.validateToken(uservo.getToken())) //올바른 액세스 토큰인지 확인
//            return ResponseEntity.badRequest().build();

        String value = googleService.getUserInfoWIthToken(uservo.getToken(),"login");
//        if (value== "202")
//            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(new MsgEntity("access","refresh"));
    }
}
