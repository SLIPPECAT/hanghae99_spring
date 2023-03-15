package com.example.jwt_prac.contoller;


import com.example.jwt_prac.dto.AuthenticatedUser;
import com.example.jwt_prac.dto.PostRequestDto;
import com.example.jwt_prac.dto.PostResponseDto;
import com.example.jwt_prac.dto.StatusResponseDto;
import com.example.jwt_prac.error.SuccessCode;
import com.example.jwt_prac.jwt.JwtUtil;
import com.example.jwt_prac.service.JwtService;
import com.example.jwt_prac.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    private final JwtUtil jwtUtil;
    private final JwtService jwtService;

    @PostMapping("/post")
    public ResponseEntity<Object> createPosts(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        // 인증
        String token = jwtUtil.resolveToken(request);
        AuthenticatedUser authenticatedUser = jwtService.validateAndGetInfo(token);
        postService.createPosts(requestDto, authenticatedUser.getUsername());

        StatusResponseDto statusResponseDto = new StatusResponseDto();
        statusResponseDto.setStatus(SuccessCode.ADD_POST_SUCCESS.getStatus());
        statusResponseDto.setMsg(SuccessCode.ADD_POST_SUCCESS.getMsg());

        return new ResponseEntity<>(statusResponseDto, HttpStatus.OK);
    }

    @GetMapping("/post")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/post/{postId}")
    public PostResponseDto getPostComment(@PathVariable Long postId){
        return postService.getPostComment(postId);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<Object> updatePosts(@PathVariable Long postId, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        // 인증
        String token = jwtUtil.resolveToken(request);
        AuthenticatedUser authenticatedUser = jwtService.validateAndGetInfo(token);
        postService.updatePosts(postId, requestDto, authenticatedUser.getUsername());

        StatusResponseDto statusResponseDto = new StatusResponseDto();
        statusResponseDto.setStatus(SuccessCode.UPDATE_POST_SUCCESS.getStatus());
        statusResponseDto.setMsg(SuccessCode.UPDATE_POST_SUCCESS.getMsg());

        return new ResponseEntity<>(statusResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Object> deletePosts(@PathVariable Long postId, HttpServletRequest request) {
        // 인증
        String token = jwtUtil.resolveToken(request);
        AuthenticatedUser authenticatedUser = jwtService.validateAndGetInfo(token);

        StatusResponseDto statusResponseDto = new StatusResponseDto();
        statusResponseDto.setStatus(SuccessCode.DELETE_POST_SUCCESS.getStatus());
        statusResponseDto.setMsg(SuccessCode.DELETE_POST_SUCCESS.getMsg());

        postService.deletePosts(postId, authenticatedUser.getUsername());

        return new ResponseEntity<>(statusResponseDto, HttpStatus.OK);
    }
}