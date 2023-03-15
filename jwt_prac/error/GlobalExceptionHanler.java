package com.example.jwt_prac.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.jwt_prac.error.ErrorCode.TOKEN_SECURITY_ERROR;

@RestControllerAdvice
public class GlobalExceptionHanler {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<Object> handleCustomException(CustomException e){
        return new ResponseEntity<>(new ErrorDto(e.getErrorCode().getStatus(), e.getErrorCode().getMsg()),
                HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler({JwtUnvalidException.class})
    protected ResponseEntity<Object> JwtUnvalidException(JwtUnvalidException e){
//        return ResponseEntity<>status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

//    @ExceptionHandler({Exception.class})
//    protected ResponseEntity<Object> handleServerException(Exception e) {
//        return new ResponseEntity<>(new ErrorDto(TOKEN_SECURITY_ERROR.getStatus(), TOKEN_SECURITY_ERROR.getMsg()), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
