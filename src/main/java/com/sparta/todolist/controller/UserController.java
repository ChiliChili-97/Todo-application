package com.sparta.todolist.controller;

import com.sparta.todolist.dto.requestDto.SignupRequestDto;
import com.sparta.todolist.dto.responseDto.SignupResponseDto;
import com.sparta.todolist.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    // 회원가입 컨트롤러
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        // 검증 예외 처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        if (!fieldErrors.isEmpty()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            SignupResponseDto responseDto = new SignupResponseDto("회원가입 실패", 403);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);

        } else {
            userService.signup(signupRequestDto);
            SignupResponseDto responseDto = new SignupResponseDto("회원가입 성공", 200);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }
}
