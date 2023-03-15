package com.example.jwt_prac.repository;

import com.example.jwt_prac.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comment, Long>{
}
