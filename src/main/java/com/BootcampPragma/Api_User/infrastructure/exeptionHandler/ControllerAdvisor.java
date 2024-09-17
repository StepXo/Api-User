package com.BootcampPragma.Api_User.infrastructure.exeptionHandler;


import com.BootcampPragma.Api_User.domain.exeption.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    private static final String MESSAGE = "Message";

    //AlreadyExist
    @ExceptionHandler(UserIdAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> userIdAlreadyExistsException(
            UserIdAlreadyExistsException userIdAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_ID_ALREADY_EXIST.getMessage()));
    }
    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> userEmailAlreadyExistsException(
            UserEmailAlreadyExistsException userEmailAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_EMAIL_ALREADY_EXIST.getMessage()));
    }


    //NotFound
    /*@ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleItemNotFoundException(
            ItemNotFoundException itemNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ITEM_NOT_FOUND.getMessage()));
    }*/

    //BadRequest
    @ExceptionHandler(EmailFormatException.class)
    public ResponseEntity<Map<String, String>> emailFormatException(
            EmailFormatException emailFormatException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EMAIL_FORMAT.getMessage()));
    }
    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<Map<String, String>> invalidAgeException(
            InvalidAgeException invalidAgeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_AGE.getMessage()));
    }
    @ExceptionHandler(InvalidIdDocumentException.class)
    public ResponseEntity<Map<String, String>> invalidIdDocumentException(
            InvalidIdDocumentException invalidIdDocumentException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_ID_DOCUMENT.getMessage()));
    }
    @ExceptionHandler(PhoneNumberFormatException.class)
    public ResponseEntity<Map<String, String>> phoneNumberFormatException(
            PhoneNumberFormatException phoneNumberFormatException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PHONE_NUMBER_FORMAT.getMessage()));
    }

}
