package com.project.board.TestCode;

import com.project.board.Contoller.Controller;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class) //테스트를 진행할때 Junit에 내장된 실행자외 다른 실행자를 실행시킨다
@WebMvcTest(controllers = Controller.class) //web에 집중할수있는 어노테이션
public class HelloContollerTest {
    @Autowired//스프링이 관리하는 빈을 주입받음
    private MockMvc mvc; //웹 API를 테스트할때 사용
    @Test
    public void hello_return() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello")) //get요청
                .andExpect(status().isOk())//결과를 검증
                .andExpect(content().string(hello)); //값이 맞는지 확인
    }
    @Test
    public void helloDto_return() throws Exception{
        String name="hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name",name)
                    .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.amount").value(amount)
            );
    }
}
