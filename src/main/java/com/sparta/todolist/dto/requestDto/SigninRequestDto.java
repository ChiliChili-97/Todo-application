package com.sparta.todolist.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SigninRequestDto {
    private String username;
    private String password;
}