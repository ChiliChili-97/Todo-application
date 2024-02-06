package com.sparta.todolist.dto.responseDto;

import com.sparta.todolist.entity.TodoCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GetTodoResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public GetTodoResponseDto(TodoCard todoCard) {
        this.id = todoCard.getTodocardId();
        this.title = todoCard.getTitle();
        this.content = todoCard.getContent();
        this.createdAt = todoCard.getCreatedAt();
        this.modifiedAt = todoCard.getModifiedAt();
    }

}
