package com.practice.urlshortener.util;

import java.security.SecureRandom;

public class ShortCode {

    public static String generateShortCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i = 0; i < 7; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }

}
