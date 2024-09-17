package com.BootcampPragma.Api_User.domain.model;
import com.BootcampPragma.Api_User.domain.utils.UserBuilder;

public class User {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private RoleEnum role;
    private String idDocument;
    private String phoneNumber;
    private String birthDate;

    public User() {
    }

    public User(UserBuilder builder) {
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.birthDate = builder.birthDate;
        this.idDocument = builder.idDocument;
        this.phoneNumber = builder.phoneNumber;
        this.id = builder.id;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public long getId() {
        return id;
    }

    public String getIdDocument() {
        return idDocument;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdDocument(String idDocument) {
        this.idDocument = idDocument;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
