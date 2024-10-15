package com.BootcampPragma.Api_User.domain.utils;



import com.BootcampPragma.Api_User.domain.exeption.*;
import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.domain.model.User;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;

public class Validation {



    private static void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new EmailFormatException();
        }
        if (!DomConstants.EMAIL_PATTERN.matcher(email).matches()) {
            throw new EmailFormatException();
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new PhoneNumberFormatException();
        }
        if (!DomConstants.PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new PhoneNumberFormatException();
        }
    }

    private static void validateIdDocument(String idDocument) {
        if (idDocument == null || idDocument.isEmpty()) {
            throw new InvalidIdDocumentException();
        }
        if (!DomConstants.ID_DOCUMENT_PATTERN.matcher(idDocument).matches()) {
            throw new InvalidIdDocumentException();
        }
    }

    private static void validateAge(String birthDateStr) {
        if (birthDateStr == null || birthDateStr.isEmpty()) {
            return;
        }
        LocalDate birthDate;
        birthDate = LocalDate.parse(birthDateStr, DomConstants.DATE_FORMATTER);

        LocalDate now = LocalDate.now();
        long age = ChronoUnit.YEARS.between(birthDate, now);

        if (age < 18) {
            throw new InvalidAgeException();
        }
    }
    private static void validateNullable(User user){
        if (user == null){
            throw new UserIsNullException();
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()){
            throw new PasswordIsNullException();
        }
        if (user.getName() == null || user.getName().isEmpty()){
            throw new NameIsNullException();
        }
    }

    public static void validate(User user){
        validateNullable(user);
        validateEmail(user.getEmail());
        validatePhoneNumber(user.getPhoneNumber());
        validateIdDocument(user.getIdDocument());
        validateAge(user.getBirthDate());
    }
    public static void validate(Authentication user){
        validateEmail(user.getEmail());
    }

}

