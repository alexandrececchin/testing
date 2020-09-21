package com.example.demo.user;

import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthService {

    public Principal getAuthenticatedUser(Principal principal) {
        // Retrieves a dummy user principal
        // for this example project
        return principal == null ? () -> "user" : principal;
    }
}
