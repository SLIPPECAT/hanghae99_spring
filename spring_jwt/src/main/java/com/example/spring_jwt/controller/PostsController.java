package com.example.spring_jwt.controller;

import com.example.spring_jwt.dto.PostsRequestDto;
import com.example.spring_jwt.entity.Posts;
import com.example.spring_jwt.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    } // 저번주꺼 + 이번주꺼

    @PostMapping("/api/posts")
    public Posts createPosts(@RequestBody PostsRequestDto requestDto, HttpServletRequest httpServletReq){
        return postsService.createPosts(requestDto, httpServletReq);
    }

    @GetMapping("/api/posts")
    public List<Posts> getPosts(){
        return postsService.getPosts();
    }

    @PutMapping("/api/posts/{id}")
    public ResponseEntity updatePosts(@PathVariable Long id, @RequestBody PostsRequestDto reqDto, HttpServletRequest httpServletReq){
        postsService.updatePosts(id,reqDto, httpServletReq);
        String message = "게시글이 수정되었습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity deletePosts(@PathVariable Long id, @RequestBody PostsRequestDto reqDto, HttpServletRequest httpServletRequest){
        postsService.deletePosts(id, reqDto, httpServletRequest);
        String message = "게시글이 삭제되었습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
