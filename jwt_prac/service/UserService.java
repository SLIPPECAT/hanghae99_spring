package com.example.jwt_prac.service;


import com.example.jwt_prac.dto.LoginRequestDto;
import com.example.jwt_prac.dto.SignupRequestDto;
import com.example.jwt_prac.entity.UserRoleEnum;
import com.example.jwt_prac.entity.Users;
import com.example.jwt_prac.error.CustomException;
import com.example.jwt_prac.error.ErrorCode;
import com.example.jwt_prac.jwt.JwtUtil;
import com.example.jwt_prac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@Validated
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public void signUp(@Valid SignupRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        // 회원 중복 확인
        Optional <Users> found = userRepository.findByUsername(username);
        if(found.isPresent()){
            throw new CustomException(ErrorCode.DUPLICATED_USERNAME);
        }
        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()){
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new CustomException(ErrorCode.INVALID_TOKEN);
            }
            role = UserRoleEnum.ADMIN;
        }
        Users user = new Users(username, password, role);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void login(LoginRequestDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 사용자 확인
        Users user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        // 비밀번호 확인
        if(!user.getPassword().equals(password)){
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
    }
}
