package com.example.jwt_prac.contoller;

import com.example.jwt_prac.dto.AuthenticatedUser;
import com.example.jwt_prac.dto.CommentRequestDto;
import com.example.jwt_prac.dto.CommentResponseDto;
import com.example.jwt_prac.dto.StatusResponseDto;
import com.example.jwt_prac.entity.UserRoleEnum;
import com.example.jwt_prac.error.SuccessCode;
import com.example.jwt_prac.jwt.JwtUtil;
import com.example.jwt_prac.service.CommentService;
import com.example.jwt_prac.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/post")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final JwtUtil jwtUtil;
    private final JwtService jwtService;

    @PostMapping("/{postId}/comment")
    public ResponseEntity<Object> addComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto,
                                             HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        AuthenticatedUser authenticatedUser = jwtService.validateAndGetInfo(token);
        commentService.addComment(postId, requestDto, authenticatedUser.getUsername());

        StatusResponseDto statusResponseDto = new StatusResponseDto();
        statusResponseDto.setStatus(SuccessCode.ADD_COMMENT_SUCCESS.getStatus());
        statusResponseDto.setMsg(SuccessCode.ADD_COMMENT_SUCCESS.getMsg());

        return new ResponseEntity<>(statusResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{postId}/comment")
    public List<CommentResponseDto> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }

    @PutMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<Object> updateComments(@PathVariable Long postId, @PathVariable Long commentId,
                                                 @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        AuthenticatedUser authenticatedUser = jwtService.validateAndGetInfo(token);
        commentService.updateComment(postId, commentId, requestDto, authenticatedUser.getUsername());

        StatusResponseDto statusResponseDto = new StatusResponseDto();
        statusResponseDto.setStatus(SuccessCode.UPDATE_COMMENT_SUCCESS.getStatus());
        statusResponseDto.setMsg(SuccessCode.UPDATE_COMMENT_SUCCESS.getMsg());

        return new ResponseEntity<>(statusResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("{postId}/comment/{commentId}")
    public ResponseEntity<Object> deleteComments(@PathVariable Long postId, @PathVariable Long commentId,
                                                 HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        AuthenticatedUser authenticatedUser = jwtService.validateAndGetInfo(token);
        commentService.deleteComment(postId, commentId, authenticatedUser.getUsername());

        StatusResponseDto statusResponseDto = new StatusResponseDto();
        statusResponseDto.setStatus(SuccessCode.DELETE_COMMENT_SUCCESS.getStatus());
        statusResponseDto.setMsg(SuccessCode.DELETE_COMMENT_SUCCESS.getMsg());

        return new ResponseEntity<>(statusResponseDto, HttpStatus.OK);

    }

//    // 인증과 인가
//    @DeleteMapping("admin/comment/{commentId}")
//    public ResponseEntity<Object> adminDeleteComments(@PathVariable Long commentId, HttpServletRequest request){
//        // 인증
//        String token = jwtUtil.resolveToken(request);
//        AuthenticatedUser authenticatedUser = jwtUtil.validateAndGetInfo(token);
//        // 인가
//        if(!authenticatedUser.getUserRoleEnum().equals(UserRoleEnum.ADMIN)) throw new IllegalArgumentException("권한이 없습니다.");
//
//        StatusResponseDto responseDto = new StatusResponseDto();
//        responseDto.setStatus(SuccessCode.DELETE_COMMENT_SUCCESS.getStatus());
//        responseDto.setMsg(SuccessCode.DELETE_COMMENT_SUCCESS.getMsg());
//
//        commentService.deleteByAdmin(commentId);
//
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//    }
}