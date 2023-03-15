package com.example.jwt_prac.repository;

import com.example.jwt_prac.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long>{

    List<Post> findAll();

    List<Post> findAllByOrderByModifiedAtDesc();
}
