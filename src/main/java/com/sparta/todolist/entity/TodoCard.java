package com.sparta.todolist.entity;

import com.sparta.todolist.dto.requestDto.TodoPostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "todoCards")
public class TodoCard extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todoCard_id", updatable = false)
    private Long todocardId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public TodoCard(TodoPostRequestDto todoPostRequestDto, User user) {
        this.title = todoPostRequestDto.getTitle();
        this.content = todoPostRequestDto.getContent();
        this.user = user;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
