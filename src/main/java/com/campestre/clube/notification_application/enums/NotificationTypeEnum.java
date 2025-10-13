package com.campestre.clube.notification_application.enums;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

import static com.campestre.clube.notification_application.utils.MessageExtensions.*;

public enum NotificationTypeEnum {
    RESET_PASSWORD_EMAIL(SUBJECT_RESET_PASSWORD_EMAIL);

    private final String subject;

    NotificationTypeEnum(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public static NotificationTypeEnum fromString(String value) {
        return fromString(NotificationTypeEnum.class, value, new RuntimeException(ERROR_NOTIFICATION_TYPE_ENUM));
    }

    private static final Pattern NON_ASCII = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    public static <E extends Enum<E>> E fromString(Class<E> enumClass, String value, RuntimeException exception) {
        if (value == null) throw exception;
        try {
            return Enum.valueOf(enumClass, normalize(value).toUpperCase(Locale.ROOT).replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw exception;
        }
    }

    private static String normalize(String input) {
        return NON_ASCII.matcher(Normalizer.normalize(input, Normalizer.Form.NFD)).replaceAll("");
    }
}
