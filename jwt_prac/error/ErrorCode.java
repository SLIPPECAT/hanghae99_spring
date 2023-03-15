package com.example.jwt_prac.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // 400 BAD REQUEST
    INVALID_TOKEN(400, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(400, "토큰이 유효하지 않습니다."),
    DIFFERENT_AUTHOR(400, "작성자만 삭제/수정할 수 있습니다."),
    DUPLICATED_USERNAME(400, "중복된 username 입니다."),
    USER_NOTFOUND(400, "회원을 찾을 수 없습니다."),


    // 404 NOT_FOUND 잘못된 리소스 접근
    USER_NOT_FOUND(400, "사용자를 찾을 수 없습니다."),
    POST_NOT_FOUND(400, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(400, "댓글을 찾을 수 없습니다."),

    // 500 INTERNAL SERVER ERROR;
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다."),
    TOKEN_SECURITY_ERROR(500, "안전하지 않은 토큰입니다.");
    private final int status;
    private final String msg;
}
