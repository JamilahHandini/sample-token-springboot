package com.tujuhsembilan.library.utility;

public class StringMaskingUtil {

    private StringMaskingUtil() {
    }

    public static String getMaskedEmailDomainOnly(String maskingSource) {
        return maskingSource.replaceAll("(?<=.{1}).(?=[^@]*?@)", "*");
    }

    public static String getAllMasked(String maskingSource) {
        return "*".repeat(maskingSource.length());
    }

    public static String getMaskedShowEnds(String maskingSource, int amountOfCharToBeShown) {
        return amountOfCharToBeShown < maskingSource.length()
                ? "*".repeat(maskingSource.length() - amountOfCharToBeShown)
                        .concat(maskingSource.substring(maskingSource.length() - amountOfCharToBeShown))
                : "*".repeat(maskingSource.length());
    }
}
