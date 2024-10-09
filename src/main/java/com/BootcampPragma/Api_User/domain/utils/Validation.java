package com.BootcampPragma.Api_User.domain.utils;



import com.BootcampPragma.Api_User.domain.exeption.EmailFormatException;
import com.BootcampPragma.Api_User.domain.exeption.InvalidAgeException;
import com.BootcampPragma.Api_User.domain.exeption.InvalidIdDocumentException;
import com.BootcampPragma.Api_User.domain.exeption.PhoneNumberFormatException;
import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.domain.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

public class Validation {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?\\d{12}$");
    private static final Pattern ID_DOCUMENT_PATTERN = Pattern.compile("^\\d+$");
    private static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy"))
            .toFormatter();

    private static void validateEmail(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new EmailFormatException();
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new PhoneNumberFormatException();
        }
    }

    private static void validateIdDocument(String idDocument) {
        if (!ID_DOCUMENT_PATTERN.matcher(idDocument).matches()) {
            throw new InvalidIdDocumentException();
        }
    }

    private static void validateAge(String birthDateStr) {
        LocalDate birthDate;

        birthDate = LocalDate.parse(birthDateStr, DATE_FORMATTER);

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

