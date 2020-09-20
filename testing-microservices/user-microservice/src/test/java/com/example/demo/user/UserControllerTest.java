package com.example.demo.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.security.Principal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    @Autowired
    @InjectMocks
    private UserController userController;

    @Test
    public void getUserShouldReturnUser() throws Exception {
        //language=JSON
        String content = "{\"username\": \"user\", \"firstName\": \"Jack\", \"lastName\": \"Frost\", \"email\": \"jfrost@example.com\"}";
        Principal principal = () -> "user";

        given(this.userService.getUserByPrincipal(principal))
                .willReturn(new User("user", "Jack", "Frost", "jfrost@example.com"));

        given(this.authService.getAuthenticatedUser(null)).willReturn(principal);

        this.mvc.perform(get("/uaa/v1/me").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andExpect(content().json(content));
    }
}
