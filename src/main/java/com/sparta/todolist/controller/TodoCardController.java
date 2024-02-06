package com.sparta.todolist.controller;

import com.sparta.todolist.dto.requestDto.TodoPostRequestDto;
import com.sparta.todolist.dto.responseDto.GetTodoResponseDto;
import com.sparta.todolist.dto.responseDto.TodoPostResponseDto;
import com.sparta.todolist.entity.TodoCard;
import com.sparta.todolist.jwt.JwtUtil;
import com.sparta.todolist.sercurity.UserDetailsImpl;
import com.sparta.todolist.service.TodoCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoCardController {

    private final TodoCardService todoCardService;
    private final JwtUtil jwtUtil;

    @PostMapping("/post")
    public ResponseEntity<TodoPostResponseDto> todoPost(@RequestBody TodoPostRequestDto todoPostRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoPostResponseDto todoPostResponseDto = todoCardService.todoPost(todoPostRequestDto, userDetails.getUser());
        return ResponseEntity.ok(todoPostResponseDto);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<GetTodoResponseDto>> findAllTodoCard() {
        List<GetTodoResponseDto> todoList = todoCardService.findAll()
                .stream()
                .map(GetTodoResponseDto::new)
                .toList();

        return ResponseEntity.ok().body(todoList);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<GetTodoResponseDto> findTodoCard(@PathVariable Long id) {
        TodoCard todoCard = todoCardService.findById(id);

        return ResponseEntity.ok().body(new GetTodoResponseDto(todoCard));
    }
}

