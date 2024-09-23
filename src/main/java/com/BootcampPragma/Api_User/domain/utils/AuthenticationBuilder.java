package com.BootcampPragma.Api_User.domain.utils;

import com.BootcampPragma.Api_User.domain.model.Authentication;

public class AuthenticationBuilder {

    public String email;
    public String password;
    public long id;
    public String token;

    public AuthenticationBuilder email(String email) {
        this.email = email;
        return this;
    }

    public AuthenticationBuilder password(String password) {
        this.password = password;
        return this;
    }

    public AuthenticationBuilder token(String token) {
        this.token = token;
        return this;
    }

    public Authentication build() {
        return new Authentication(this);
    }

    public AuthenticationBuilder id(long id) {
        this.id = id;
        return this;
    }
}
