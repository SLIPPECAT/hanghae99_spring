package com.example.jwt_prac.dto;

import com.example.jwt_prac.entity.Comment;
import com.example.jwt_prac.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private String author;
    private String contents;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private List<CommentResponseDto> comments;

    public PostResponseDto(Post post) {
        this.author = post.getAuthor();
        this.contents = post.getContent();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.id = post.getId();
        List<CommentResponseDto> list = new ArrayList<>();
        for (Comment comment : post.getComments()){
            list.add(new CommentResponseDto(comment));
        }
        this.comments = list;
    }
}
