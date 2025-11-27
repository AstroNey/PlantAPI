package com.personal.project.model.request;

public record UserRequest(
        String firstname,
        String username,
        String lastname,
        String password,
        String email) {
}
