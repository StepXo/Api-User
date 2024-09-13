package com.BootcampPragma.Api_User.domain.utils;

import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import com.BootcampPragma.Api_User.domain.model.User;

public class UserBuilder {

    public String name;
    public String lastName;
    public String email;
    public String password;
    public RoleEnum roleEnum;
    public String idDocument;
    public String phoneNumber;
    public String birthDate;
    public long id;

    // Métodos de configuración
    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }
    public UserBuilder idDocument(String idDocument) {
        this.idDocument = idDocument;
        return this;
    }
    public UserBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public UserBuilder birthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder roleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
        return this;
    }

    // Método para construir el objeto User
    public User build() {
        return new User(this);
    }

    public UserBuilder id(long id) {
        this.id = id;
        return this;
    }
}
