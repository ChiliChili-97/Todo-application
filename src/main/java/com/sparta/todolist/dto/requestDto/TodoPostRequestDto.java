package com.sparta.todolist.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoPostRequestDto {
    private String title;
    private String content;
}
