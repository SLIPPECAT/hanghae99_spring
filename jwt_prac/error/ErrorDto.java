package com.example.jwt_prac.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {

    private int status;
    private String msg;

    public ErrorDto(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
