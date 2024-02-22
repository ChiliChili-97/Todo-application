package com.sparta.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.todolist.config.MockSpringSecurityConfig;
import com.sparta.todolist.config.WebSecurityConfig;
import com.sparta.todolist.dto.requestDto.TodoPostRequestDto;
import com.sparta.todolist.entity.User;
import com.sparta.todolist.sercurity.UserDetailsImpl;
import com.sparta.todolist.service.TodoCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TodoCardController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfig.class)})
class TodoCardControllerTest {

    private MockMvc mvc;
    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    TodoCardService todoCardService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity(new MockSpringSecurityConfig()))
                .build();
    }

    private void setMockPrincipal() {
        String username = "asdf1234";
        String password = "asdf@1234";
        User testUser = new User(username, password);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, null, null);
    }

    @Test
    @DisplayName("게시물 작성 성공")
    public void createPost() throws Exception {
        // given
        setMockPrincipal();
        String title = "제목 테스트";
        String content = "내용 테스트";

        TodoPostRequestDto todoPostRequestDto = new TodoPostRequestDto(title, content);

        String post = objectMapper.writeValueAsString(todoPostRequestDto);

        // when - then
        mvc.perform(post("/todo/post")
                        .content(post)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal))
                .andExpect(status().isOk())
                .andDo(print());
    }
}