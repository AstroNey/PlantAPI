package com.personal.project.tools;

import com.password4j.Password;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tools class.
 * Contains utility methods.
 */
public class Tools {

    /**
     * The constant EMAIL_REGEX.
     */
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    /**
     * Private constructor to prevent instantiation.
     */
    private Tools() {
        // Private constructor to prevent instantiation
    }

    /**
     * Encrypt password.
     * @param password the password
     * @return the encrypted password
     */
    public static @NotNull @NotEmpty String encryptPwd(final String password) {
        return Password.hash(password).withArgon2().getResult();
    }

    /**
     * Check mail.
     * @param newEmail the email
     * @return true if the email is valid.
     */
    public static @NotNull @NotEmpty Boolean isValidEmail(
            final String newEmail) {
        boolean isValid = false;

        if (newEmail != null && !newEmail.trim().isEmpty()) {
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = pattern.matcher(newEmail);
            isValid = matcher.matches();
        }
        return isValid;
    }
}
