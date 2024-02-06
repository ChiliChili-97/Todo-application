package com.sparta.todolist.service;

import com.sparta.todolist.dto.requestDto.TodoPostRequestDto;
import com.sparta.todolist.dto.responseDto.TodoPostResponseDto;
import com.sparta.todolist.entity.TodoCard;
import com.sparta.todolist.entity.User;
import com.sparta.todolist.repository.TodoCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoCardService {

    private final TodoCardRepository todoCardRepository;

    public TodoPostResponseDto todoPost(TodoPostRequestDto todoPostRequestDto, User user) {
        TodoCard todoCard = todoCardRepository.save(new TodoCard(todoPostRequestDto, user));
        return new TodoPostResponseDto(todoCard);
    }

    public List<TodoCard> findAll() {
        return todoCardRepository.findAll();
    }

    public TodoCard findById(Long id) {
        return todoCardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found : " + id));
    }
}
