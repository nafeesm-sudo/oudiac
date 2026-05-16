package com.app.oudiac.utils;

import java.util.UUID;

public class SkuGenerator {

    public static String generateSku(String brand, String category, String product, String variant) {

        String random = UUID.randomUUID().toString()
                .substring(0, 4)
                .toUpperCase();

        return String.format("%s-%s-%s-%s-%s",
                normalize(brand),
                normalize(category),
                normalize(product),
                normalize(variant),
                random
        );
    }

    private static String normalize(String input) {
        return input.replaceAll("\\s+", "")
                .toUpperCase()
                .substring(0, Math.min(4, input.length()));
    }
}