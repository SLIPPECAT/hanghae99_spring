package com.example.jwt_prac.entity;

import com.example.jwt_prac.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped{

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String comment;

    // ManyToOne에는 LAZY 타입을 붙여라.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 쿼리가 엄청 날아감.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public Comment(CommentRequestDto requestDto, Post post, Users user){
        this.comment = requestDto.getComment();
        this.post = post;
        this.user = user;
    }

    public void update(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
    }
}