package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/uaa/v1")
public class UserController {

    private UserService userService;

    private AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    // <1>
    @RequestMapping(path = "/me")
    public ResponseEntity<User> me(Principal principal) throws Exception {
        Principal p = authService.getAuthenticatedUser(principal);
        if (p != null) {
            User userByPrincipal = userService.getUserByPrincipal(p);
            return ResponseEntity.ok().body(userByPrincipal);
        }
        return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
    }
}
