package com.example.jwt_prac.error;


public class JwtUnvalidException extends RuntimeException{
    public JwtUnvalidException(String message) {
        super(message);
    }
}
