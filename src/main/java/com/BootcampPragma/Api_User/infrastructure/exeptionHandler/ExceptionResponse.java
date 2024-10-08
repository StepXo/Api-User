package com.BootcampPragma.Api_User.infrastructure.exeptionHandler;


public enum ExceptionResponse {
    EMAIL_FORMAT("Please provide a valid email address."),

    INVALID_AGE("You must be at least 18 years old."),
    INVALID_ID_DOCUMENT("Invalid ID document format. Only numeric values are allowed."),
    PHONE_NUMBER_FORMAT("Invalid phone number format. Please provide a valid phone number."),
    USER_ID_ALREADY_EXIST ("There is already a user with that identification Number"),
    USER_EMAIL_ALREADY_EXIST("There is already a user with that email"),
<<<<<<< Updated upstream
=======
    USER_NOT_FOUND("No user was found"),
    PASSWORD_FORMAT("The password can't be null or empty"),
>>>>>>> Stashed changes

    ;


    private String message;

    ExceptionResponse(String message) {
            this.message = message;
        }
        public String getMessage() {
            return this.message;
        }

}
