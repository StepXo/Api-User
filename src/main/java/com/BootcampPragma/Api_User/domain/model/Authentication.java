package com.BootcampPragma.Api_User.domain.model;
import com.BootcampPragma.Api_User.domain.utils.AuthenticationBuilder;

public class Authentication {
    private String email;
    private String password;
    private String token;

    public Authentication() {
    }

    public Authentication(AuthenticationBuilder builder) {

        this.email = builder.email;
        this.password = builder.password;
        this.token = builder.token;

    }

    public static AuthenticationBuilder builder() {
        return new AuthenticationBuilder();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
