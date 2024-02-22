package com.sparta.todolist.service;

import com.sparta.todolist.entity.User;
import com.sparta.todolist.repository.TodoCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoCardServiceTest {

    @InjectMocks
    private TodoCardService todoCardService;

    @Mock
    private TodoCardRepository todoCardRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder().userId(1L).build();
    }
}