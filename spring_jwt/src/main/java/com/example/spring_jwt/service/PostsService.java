package com.example.spring_jwt.service;

import com.example.spring_jwt.dto.PostsRequestDto;
import com.example.spring_jwt.entity.Posts;
import com.example.spring_jwt.entity.User;
import com.example.spring_jwt.entity.UserRoleEnum;
import com.example.spring_jwt.jwt.JwtUtil;
import com.example.spring_jwt.repository.PostsRepository;
import com.example.spring_jwt.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {

    // Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYWFhYWFhYSIsImF1dGgiOiJVU0VSIiwiZXhwIjoxNjc4MTE4NTg5LCJpYXQiOjE2NzgxMTQ5ODl9.Wf0tuukHjzdO4XBjoE0p-W7UP3lzV3tbX66LhPA26_s

    private final JwtUtil jwtUtil;
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Transactional
    public Posts createPosts(PostsRequestDto requestDto, HttpServletRequest httpServletRequest) {
        // request에서 token 가져오기
        String token = jwtUtil.resolveToken(httpServletRequest);
        Claims claims;

        // token 검증
        Posts posts = null;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                //토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다요.")
            );

            // 사용자가 존재하면 포스트 작성 가능
            if (user.getUsername().equals(requestDto.getUsername())) {
                posts = new Posts(requestDto);
                postsRepository.save(posts);
            }
        }
        return posts;
    }

    @Transactional(readOnly = true)
    public List<Posts> getPosts() {
        return postsRepository.findAll();
    }

    @Transactional
    public Long updatePosts(Long id, PostsRequestDto requestDto, HttpServletRequest httpServletReq) {
        String username = requestDto.getUsername();

        String token = jwtUtil.resolveToken(httpServletReq);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                //토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
        }

        // 해당 사용자가 작성한 게시글인지 확인
        Posts posts = postsRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (username.equals(posts.getUsername())) {
            posts.update(requestDto);
        }
        return posts.getId();
    }

    @Transactional
    public void deletePosts(Long id, PostsRequestDto reqDto, HttpServletRequest httpServletReq){
        String username = reqDto.getUsername();
        System.out.println(id);
        System.out.println(username);
        String token = jwtUtil.resolveToken(httpServletReq);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                //토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
        }

        Posts post = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        System.out.println(post.getUsername());

        if(post.getUsername().equals(username)){
            postsRepository.delete(post);
        }
    }
}
