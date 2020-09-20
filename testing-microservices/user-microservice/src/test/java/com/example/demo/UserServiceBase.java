package com.example.demo;

import com.example.demo.user.AuthService;
import com.example.demo.user.User;
import com.example.demo.user.UserController;
import com.example.demo.user.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserServiceBase {

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    org.junit.Testore

    public void setup() {

        User actual = new User("com/example/demo/user", "Jack", "Frost", "jfrost@example.com");
        actual.setLastModified(12345L);
        actual.setCreatedAt(12345L);
        actual.setId(0L);
        given(this.userService.getUserByPrincipal(() -> "com/example/demo/user")).willReturn(actual);

        given(this.authService.getAuthenticatedUser(null)).willReturn(() -> "com/example/demo/user");

        RestAssuredMockMvc.standaloneSetup(new UserController(userService, authService));
    }

    public void assertThatRejectionReasonIsNull(Object rejectionReason) {
        assert rejectionReason == null;
    }
}