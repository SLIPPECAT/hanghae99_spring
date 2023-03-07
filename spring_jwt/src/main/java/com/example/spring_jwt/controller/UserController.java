package com.example.spring_jwt.controller;


import com.example.spring_jwt.dto.LoginRequestDto;
import com.example.spring_jwt.dto.SignupRequestDto;
import com.example.spring_jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public ModelAndView signupPage(){
        return new ModelAndView("signup");
    }

    @GetMapping("/login")
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        String message = "회원가입이 완료되었습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.login(loginRequestDto, response);
        String message = "로그인이 완료되었습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
