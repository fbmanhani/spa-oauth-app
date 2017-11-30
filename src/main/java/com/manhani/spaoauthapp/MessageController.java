package com.manhani.spaoauthapp;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/mod")
    public String getMessageOfTheDay(final Principal principal) {
        return "The message of the day is boring for user: " + principal.getName();
    }
}
