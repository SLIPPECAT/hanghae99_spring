package com.example.jwt_prac.dto;

import com.example.jwt_prac.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {

    private String comment;
    private Post post;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
