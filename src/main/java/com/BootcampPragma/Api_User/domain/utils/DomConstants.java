package com.BootcampPragma.Api_User.domain.utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.regex.Pattern;

public class DomConstants {

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    public static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?\\d{12}$");
    public static final Pattern ID_DOCUMENT_PATTERN = Pattern.compile("^\\d+$");
    public static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy"))
            .toFormatter();


    public DomConstants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated.");

    }
}