package com.BootcampPragma.Api_User.domain.utils;



import com.BootcampPragma.Api_User.domain.Exeptions.EmailFormatException;
import com.BootcampPragma.Api_User.domain.Exeptions.InvalidAgeException;
import com.BootcampPragma.Api_User.domain.Exeptions.InvalidIdDocumentException;
import com.BootcampPragma.Api_User.domain.Exeptions.PhoneNumberFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

public class Validation {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?\\d{1,13}$");
    private static final Pattern ID_DOCUMENT_PATTERN = Pattern.compile("^\\d+$");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void validateEmail(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new EmailFormatException();
        }
    }

    public static void validatePhoneNumber(String phoneNumber) {
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new PhoneNumberFormatException();
        }
    }

    public static void validateIdDocument(String idDocument) {
        if (!ID_DOCUMENT_PATTERN.matcher(idDocument).matches()) {
            throw new InvalidIdDocumentException();
        }
    }

    public static void validateAge(String birthDateStr) {
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(birthDateStr, DATE_FORMATTER);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Expected format is dd/MM/yyyy.");
        }
        LocalDate now = LocalDate.now();
        long age = ChronoUnit.YEARS.between(birthDate, now);

        if (age < 18) {
            throw new InvalidAgeException();
        }
    }
}

