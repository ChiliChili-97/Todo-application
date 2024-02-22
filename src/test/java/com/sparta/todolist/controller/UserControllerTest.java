package com.sparta.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.todolist.config.MockSpringSecurityConfig;
import com.sparta.todolist.config.WebSecurityConfig;
import com.sparta.todolist.dto.requestDto.SignupRequestDto;
import com.sparta.todolist.jwt.JwtAuthenticationFilter;
import com.sparta.todolist.repository.UserRepository;
import com.sparta.todolist.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfig.class)})
class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(jwtAuthenticationFilter)
                .apply(springSecurity(new MockSpringSecurityConfig()))
                .build();
    }

    @Test
    @DisplayName("회원가입 요청 승인")
    void signUp() throws Exception {

        // given
        String username = "jsb1234";
        String password = "1234";

        SignupRequestDto signupRequestDto = new SignupRequestDto(username, password);

        String user = objectMapper.writeValueAsString(signupRequestDto);

        // when - then
        mvc.perform(post("/auth/signup")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}