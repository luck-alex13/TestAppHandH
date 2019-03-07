package com.example.user.testapphandh.helpers;

import android.util.Patterns;

import java.util.regex.Pattern;

public class ValidationHelper {

    /*
        (			# Start of group
      (?=.*\d)		#   must contains one digit from 0-9
      (?=.*[a-z])		#   must contains one lowercase characters
      (?=.*[A-Z])		#   must contains one uppercase characters
                  .		#     match anything with previous condition checking
                    {6,32}	#        length at least 6 characters and maximum of 32
        )			# End of group
    */
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

    public static boolean isValidEmail(String email) {
        if (email == null) return false;

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        if (password == null) return false;

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }

}
