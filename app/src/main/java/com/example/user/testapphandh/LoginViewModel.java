package com.example.user.testapphandh;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.widget.EditText;

public class LoginViewModel extends AndroidViewModel {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public boolean validateEmail(EditText editText) {
        boolean isValid = ValidationHelper.isValidEmail(editText.getText().toString());
        if (!isValid) {
            if (editText.getText().length() == 0)
                editText.setError(getApplication().getString(R.string.error_field_required));
            else
                editText.setError(getApplication().getString(R.string.error_invalid_email));
            editText.requestFocus();
        }
        return isValid;
    }

    public boolean validatePassword(EditText editText) {
        boolean isValid = ValidationHelper.isValidPassword(editText.getText().toString());
        if (!isValid) {
            if (editText.getText().length() == 0)
                editText.setError(getApplication().getString(R.string.error_field_required));
            else
                editText.setError(getApplication().getString(R.string.error_incorrect_password));
            editText.requestFocus();
        }
        return isValid;
    }
}
