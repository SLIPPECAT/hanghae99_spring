package com.example.jwt_prac.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatusResponseDto {
        private Integer status;
        private String msg;

    public StatusResponseDto() {
    }

    public StatusResponseDto (Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }
}
