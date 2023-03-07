package com.example.jwt_prac.contoller;


import com.example.jwt_prac.entity.Posts;
import com.example.jwt_prac.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;


//    @GetMapping("/")
//    public ModelAndView home(){
//        return "index";
//    }

    @GetMapping("/api/posts")
    public List<Posts> getPosts(){
        return postsService.getPosts();
    }



}
