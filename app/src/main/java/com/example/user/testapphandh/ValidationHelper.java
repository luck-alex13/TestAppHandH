package com.example.user.testapphandh;

import android.util.Patterns;

import java.util.regex.Pattern;

public class ValidationHelper {

    public static boolean isValidEmail(String email) {
        if (email == null) return false;

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String pass) {
        return true;
    }

}
