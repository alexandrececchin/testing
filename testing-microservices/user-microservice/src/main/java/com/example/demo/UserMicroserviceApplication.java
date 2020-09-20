package com.example.demo;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserMicroserviceApplication.class, args);
    }

    //A different way to initialize the DB
//	@Bean
    ApplicationRunner init(UserRepository userRepository) {
        return args -> {
            userRepository.deleteAll();
            User user = new User("User", "Jhon", "Doe", "john.doe@example.com");
            userRepository.save(user);
        };
    }

}
