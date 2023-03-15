package com.example.jwt_prac.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SuccessCode {

    // 200 클라이언트에 반환 성공
    ADD_POST_SUCCESS(200, "게시글을 생성에 성공했습니다."),
    UPDATE_POST_SUCCESS(200, "게시글을 수정에 성공했습니다."),
    DELETE_POST_SUCCESS(200, "게시글을 삭제에 성공했습니다."),

    ADD_COMMENT_SUCCESS(200, "댓글을 생성에 성공했습니다."),
    UPDATE_COMMENT_SUCCESS(200, "댓글을 수정에 성공했습니다."),
    DELETE_COMMENT_SUCCESS(200, "댓글을 삭제에 성공했습니다.");

    private final int status;
    private final String msg;


}
