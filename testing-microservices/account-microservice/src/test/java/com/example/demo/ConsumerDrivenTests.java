package com.example.demo;

import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ContextConfiguration(classes = AccountMicroserviceApplication.class)
@AutoConfigureStubRunner(ids = {"com.example:user-microservice:+:stubs:8081"}) // <1>
public class ConsumerDrivenTests {

    @Autowired
    private UserService service; // <2>

    @Test
    public void shouldReturnAuthenticatedUser() {
        User actual = service.getAuthenticatedUser();

        assertThat(actual).isNotNull();
        assertThat(actual.getUsername()).matches("[A-Za-z0-9]+");
        assertThat(actual.getFirstName()).matches("[A-Za-z]+");
        assertThat(actual.getLastName()).matches("[A-Za-z]+");
        assertThat(actual.getEmail()).matches("[A-Za-z0-9]+\\@[A-Za-z0-9]+\\.[A-Za-z]+");
    }
}
