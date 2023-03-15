package com.example.jwt_prac.service;

import com.example.jwt_prac.contoller.CommentController;
import com.example.jwt_prac.dto.PostRequestDto;
import com.example.jwt_prac.dto.PostResponseDto;
import com.example.jwt_prac.entity.Post;
import com.example.jwt_prac.entity.UserRoleEnum;
import com.example.jwt_prac.entity.Users;
import com.example.jwt_prac.error.CustomException;
import com.example.jwt_prac.error.ErrorCode;
import com.example.jwt_prac.error.JwtUnvalidException;
import com.example.jwt_prac.jwt.JwtUtil;
import com.example.jwt_prac.repository.PostsRepository;
import com.example.jwt_prac.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        List<Post> posts = postsRepository.findAllByOrderByModifiedAtDesc();
//        List<PostResponseDto> postsResponseDtos = new ArrayList<>();
//        for (Post post : posts) {
//            PostResponseDto postResponseDto = new PostResponseDto(post);
//            postsResponseDtos.add(postResponseDto);
//        }
        return posts.stream().map(post -> new PostResponseDto(post)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPostComment(Long postId) {
        Post post = postsRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    //fixme: 나를 고쳐줘, 메소드 안에서 중복되는 걸 제거해라
    @Transactional
    public void createPosts(PostRequestDto requestDto, String username) {
        Users user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Post post = new Post(requestDto, user);
        postsRepository.save(post);
    }

    @Transactional
    public void updatePosts(Long postId, PostRequestDto requestDto, String username) {
        Users user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Post post = postsRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (user.isAdmin(user) | user.isPostWriter(post)){
            post.update(requestDto);
            postsRepository.save(post);
        } else {
            throw new CustomException(ErrorCode.DIFFERENT_AUTHOR);
        }
    }

    @Transactional
    public void deletePosts(Long postId, String username) {
        Post post = postsRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND));

        Users user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // ADMIN 이거나 내가 쓴 글일 경우 삭제 가능
        if (user.isAdmin(user) | user.isPostWriter(post)){
            postsRepository.delete(post);
        } else {
            throw new CustomException(ErrorCode.DIFFERENT_AUTHOR);
        }
    }
}
