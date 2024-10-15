package com.BootcampPragma.Api_User.infrastructure.exeptionHandler;


public enum ExceptionResponse {
    EMAIL_FORMAT("Please provide a valid email address."),

    INVALID_AGE("You must be at least 18 years old."),
    INVALID_ID_DOCUMENT("Invalid ID document format. Only numeric values are allowed."),
    PHONE_NUMBER_FORMAT("Invalid phone number format. Please provide a valid phone number."),
    USER_ID_ALREADY_EXIST ("There is already a user with that identification Number"),
    USER_EMAIL_ALREADY_EXIST("There is already a user with that email"),
    USER_NOT_FOUND("No user was found with that email"),
    PASSWORD_FORMAT("The password can't be null or empty"),
    PASSWORD_IS_NULL("Password cannot be null or empty"),
    USER_IS_NULL("User cannot be null"),
    NAME_IS_NULL("Name cannot be null or empty"),
    ;


    private String message;

    ExceptionResponse(String message) {
            this.message = message;
        }
        public String getMessage() {
            return this.message;
        }

}
