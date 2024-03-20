package com.project.board.auth;
import org.json.simple.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;

@Service
public class GoogleService {
    private final static String GOOGLE_API_URL="https://www.googleapis.com";
    public String getUserInfoWIthToken(String accessToken, String type) throws ParseException, net.minidev.json.parser.ParseException {
        //HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " + accessToken);
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        //header담기
        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(
                GOOGLE_API_URL + "/v1/nid/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );
        //Response 데이터 파싱
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj    = (JSONObject) jsonParser.parse(response.getBody());
        System.out.println(jsonObj.toJSONString());
        return "200";
    }
}
