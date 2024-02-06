package com.sparta.todolist.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IsCompleteRequestDto {
    private boolean isComplete;
}
