package com.example.spring_jwt;

import com.example.spring_jwt.entity.Posts;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtApplication.class, args);


//        Posts posts = new Posts("준영", "내용", "제목");



    }
}
