package com.project.board.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MsgEntity {
    private String access;
    private String refresh;
    private Object result;

    public MsgEntity(Object result) {
        this.result  = result;
    }
    public MsgEntity(String access,String refresh) {
        this.access = access;
        this.refresh  = refresh;
    }
}