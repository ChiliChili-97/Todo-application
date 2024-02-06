package com.sparta.todolist.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class IsCompleteResponseDto {
    private String msg;
    private int statusCode;
}
