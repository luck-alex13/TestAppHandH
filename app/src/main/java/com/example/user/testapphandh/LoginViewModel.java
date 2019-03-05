package com.example.user.testapphandh;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class LoginViewModel extends AndroidViewModel {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void loginClicked(EditText emailEditText, EditText passEditText) {
        if (validateEmail(emailEditText) && validatePassword(passEditText)) {
            hideKeyboard(passEditText);
            SnackbarHelper.showSnack(emailEditText, "All right!");
        }
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

    public void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
