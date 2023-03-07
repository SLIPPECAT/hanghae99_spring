package com.example.spring_jwt.entity;

import com.example.spring_jwt.dto.PostsRequestDto;
import com.example.spring_jwt.dto.PostsResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@RequiredArgsConstructor
@Getter
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(name = "createdDate")
    @OrderBy(value = "createdDate DESC")
    private LocalDateTime createdDate;

    @PrePersist
    public void setCreateDate(){
        this.createdDate = LocalDateTime.now();
    }

//    public Posts(PostsResponseDto responseDto){
//        this.username = responseDto.getUsername();
//        this.contents = responseDto.getContent();
//        this.title = responseDto.getTitle();
//        this.createdDate = responseDto.getCreatedDate();
//    }

    public Posts(PostsRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }

    public void update(PostsRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }
}