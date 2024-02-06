package com.sparta.todolist.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpateTodoRequest {

    private String title;
    private String content;
    private LocalDateTime modifiedAt;
}
