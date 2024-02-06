package com.sparta.todolist.service;

import com.sparta.todolist.dto.requestDto.IsCompleteRequestDto;
import com.sparta.todolist.dto.requestDto.TodoPostRequestDto;
import com.sparta.todolist.dto.requestDto.UpdateTodoRequest;
import com.sparta.todolist.dto.responseDto.TodoPostResponseDto;
import com.sparta.todolist.entity.TodoCard;
import com.sparta.todolist.entity.User;
import com.sparta.todolist.repository.TodoCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoCardService {

    private final TodoCardRepository todoCardRepository;

    // 카드 생성
    public TodoPostResponseDto todoPost(TodoPostRequestDto todoPostRequestDto, User user) {
        TodoCard todoCard = todoCardRepository.save(new TodoCard(todoPostRequestDto, user));
        return new TodoPostResponseDto(todoCard);
    }

    // 전체 조회
    public List<TodoCard> findAll() {
        return todoCardRepository.findAll();
    }

    // 단건 조회
    public TodoCard findById(Long id) {
        return todoCardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found : " + id));
    }

    // 카드 업데이트
    @Transactional
    public TodoCard update(Long id, UpdateTodoRequest updateTodoRequest) {
        TodoCard todoCard = todoCardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found : " + id));

        todoCard.update(updateTodoRequest.getTitle(), updateTodoRequest.getContent());
        return todoCard;
    }

    // 할일 완료
    @Transactional
    public void isComplete(Long id, IsCompleteRequestDto isCompleteRequestDto, User user) {
        TodoCard todoCard = todoCardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found : " + id));

        todoCard.completeTodo();
    }
}
