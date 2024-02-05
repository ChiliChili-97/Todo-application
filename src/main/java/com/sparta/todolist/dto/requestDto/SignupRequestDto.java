package com.sparta.todolist.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d).{4,10}$", message = "아이디는 최소 4자 이상, 10자 이하, 알파벳 소문자와 숫자로 구성되어야 합니다.")
    private String username;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,15}$", message = "비밀번호는 최소 8자 이상, 15자 이하, 알파벳 대소문자와 숫자로 구성되어야 합니다.")
    private String password;
}
