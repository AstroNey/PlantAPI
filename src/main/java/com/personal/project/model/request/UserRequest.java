package com.personal.project.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequest {
    private String username;
    private String lastname;
    private String password;
    private String email;
}
