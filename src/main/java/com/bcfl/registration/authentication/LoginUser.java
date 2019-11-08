package com.bcfl.registration.authentication;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * POJO for JSON parsing
 */
@Data
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username; // user email
    private String password;

    public LoginUser() {
    }

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
