package com.example.jwt_prac.dto;

import com.example.jwt_prac.entity.Comment;
import com.example.jwt_prac.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto extends Timestamped {

    Long id;
    String comment;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comments){
        this.comment = comments.getComment();
        this.createdAt = comments.getCreatedAt();
        this.modifiedAt = comments.getModifiedAt();
        this.id = comments.getId();
    }
}
