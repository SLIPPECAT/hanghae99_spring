package com.example.jwt_prac.service;

import com.example.jwt_prac.dto.AuthenticatedUser;
import com.example.jwt_prac.entity.UserRoleEnum;
import com.example.jwt_prac.error.CustomException;
import com.example.jwt_prac.error.ErrorCode;
import com.example.jwt_prac.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final JwtUtil jwtUtil;

    public JwtService(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }
    public AuthenticatedUser validateAndGetInfo(String token){
        if (jwtUtil.validateToken(token)) {
            Claims claims = jwtUtil.getUserInfoFromToken(token);
            String username = claims.getSubject();
            UserRoleEnum role = UserRoleEnum.valueOf(claims.get("auth").toString());  // "USER", "ADMIN"  // String을 갖고 Enum객체 만들기
            return new AuthenticatedUser(role, username);
        } else {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }
}
