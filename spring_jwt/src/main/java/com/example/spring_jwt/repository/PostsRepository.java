package com.example.spring_jwt.repository;

import com.example.spring_jwt.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    List<Posts> findAll();

}
