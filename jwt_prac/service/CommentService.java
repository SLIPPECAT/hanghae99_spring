package com.example.jwt_prac.service;

import com.example.jwt_prac.dto.CommentRequestDto;
import com.example.jwt_prac.dto.CommentResponseDto;
import com.example.jwt_prac.entity.Comment;
import com.example.jwt_prac.entity.Post;
import com.example.jwt_prac.entity.UserRoleEnum;
import com.example.jwt_prac.entity.Users;
import com.example.jwt_prac.error.CustomException;
import com.example.jwt_prac.error.ErrorCode;
import com.example.jwt_prac.jwt.JwtUtil;
import com.example.jwt_prac.repository.CommentsRepository;
import com.example.jwt_prac.repository.PostsRepository;
import com.example.jwt_prac.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentsRepository commentsRepository;
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public List<CommentResponseDto> getComments(Long postId) {
        Post posts = postsRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        List<Comment> comments = posts.getComments();
//        List<CommentResponseDto> commentsResponseDtos = new ArrayList<>();
//        for (Comment comment : comments) {
//            CommentResponseDto commentsResponseDto = new CommentResponseDto(comment);
//            commentsResponseDtos.add(commentsResponseDto);
//        }
        List<CommentResponseDto> responseDtos = comments.stream().map(comment -> new CommentResponseDto(comment)).collect(Collectors.toList());
        return responseDtos;
    }

    @Transactional
    public void addComment(Long postId, CommentRequestDto commentRequestDto, String username) {
        Post post = postsRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND));
        Users user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Comment comments = new Comment(commentRequestDto, post, user);
        commentsRepository.save(comments);
    }

    @Transactional
    public void updateComment(Long postId, Long commentId, CommentRequestDto requestDto, String username) {
        Post post = postsRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND));
        Users user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Comment comment = commentsRepository.findById(commentId).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if (user.isAdmin(user) | user.isCommentWriter(comment)) {
            comment.update(requestDto);
            commentsRepository.save(comment);
        } else {
            throw new CustomException(ErrorCode.DIFFERENT_AUTHOR);
        }
    }

    @Transactional
    public void deleteComment(Long postId, Long commentId, String username) {
        Post post = postsRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND));
        Users user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Comment comment = commentsRepository.findById(commentId).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if (user.isAdmin(user) | user.isCommentWriter(comment)) {
            commentsRepository.delete(comment);
        } else {
            throw new CustomException(ErrorCode.DIFFERENT_AUTHOR);
        }

//    @Transactional
//    public void deleteByAdmin(Long commentId) {
//        commentsRepository.deleteById(commentId);
//    }
    }
}
