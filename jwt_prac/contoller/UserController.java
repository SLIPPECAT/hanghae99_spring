package com.example.jwt_prac.contoller;


import com.example.jwt_prac.dto.LoginRequestDto;
import com.example.jwt_prac.dto.SignupRequestDto;
import com.example.jwt_prac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody SignupRequestDto requestDto){
        userService.signUp(requestDto);
        String msg = "회원가입이 완료되었습니다.";
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse responseDto){
        userService.login(requestDto, responseDto);
        String msg = "로그인이 완료되었습니다.";
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
