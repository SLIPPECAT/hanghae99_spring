package com.example.spring_jwt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostsRequestDto {
    private String username;
    private String contents;
    private String title;
}
