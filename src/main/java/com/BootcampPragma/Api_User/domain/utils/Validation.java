package com.BootcampPragma.Api_User.domain.utils;



import com.BootcampPragma.Api_User.domain.exeption.EmailFormatException;
import com.BootcampPragma.Api_User.domain.exeption.InvalidAgeException;
import com.BootcampPragma.Api_User.domain.exeption.InvalidIdDocumentException;
import com.BootcampPragma.Api_User.domain.exeption.PhoneNumberFormatException;
import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.domain.model.User;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;

public class Validation {



    private static void validateEmail(String email) {
        if (!DomConstants.EMAIL_PATTERN.matcher(email).matches()) {
            throw new EmailFormatException();
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        if (!DomConstants.PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new PhoneNumberFormatException();
        }
    }

    private static void validateIdDocument(String idDocument) {
        if (!DomConstants.ID_DOCUMENT_PATTERN.matcher(idDocument).matches()) {
            throw new InvalidIdDocumentException();
        }
    }

    private static void validateAge(String birthDateStr) {
        LocalDate birthDate;

        birthDate = LocalDate.parse(birthDateStr, DomConstants.DATE_FORMATTER);

        LocalDate now = LocalDate.now();
        long age = ChronoUnit.YEARS.between(birthDate, now);

        if (age < 18) {
            throw new InvalidAgeException();
        }
    }

    public static void validate(User user){
        validateEmail(user.getEmail());
        validatePhoneNumber(user.getPhoneNumber());
        validateIdDocument(user.getIdDocument());
        validateAge(user.getBirthDate());
    }
    public static void validate(Authentication user){
        validateEmail(user.getEmail());
    }

}

