package com.example.demo.util;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%^&*()_-+=<>?";

    private static final String PASSWORD_ALLOW = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static Random random = new SecureRandom();

    public static String generateRandomPassword(int length) {
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(PASSWORD_ALLOW.length());
            password.append(PASSWORD_ALLOW.charAt(randomIndex));
        }

        return password.toString();
    }
}
