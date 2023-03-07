package com.example.jwt_prac.service;


import com.example.jwt_prac.entity.Posts;
import com.example.jwt_prac.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public List<Posts> getPosts(){
        return postsRepository.findAll();
    }

}
