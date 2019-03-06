package com.example.user.testapphandh;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.user.testapphandh.data.WeatherResponse;
import com.example.user.testapphandh.helpers.Utils;
import com.example.user.testapphandh.helpers.ValidationHelper;
import com.example.user.testapphandh.network.Const;
import com.example.user.testapphandh.network.WeatherClient;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class LoginViewModel extends AndroidViewModel {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void loginClicked(EditText emailEditText, EditText passEditText) {
        if (validateEmail(emailEditText) && validatePassword(passEditText)) {
            hideKeyboard(passEditText);
            requestWeather();
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

    public void requestWeather(){
        if(checkConnection(getApplication(), R.string.no_internet_connection)){
           Disposable dp = WeatherClient.getInstance().getWeatherInCity(Const.SPB_CITY_ID)
                    .subscribe(new Consumer<WeatherResponse>() {
                        @Override
                        public void accept(WeatherResponse weatherResponse) throws Exception {

                        }
                    });
        }
    }

    public static boolean checkConnection(Context appContext, @StringRes int errStr) {
        boolean res = Utils.hasConnection(appContext);
        if (!res) {
            Utils.showToast(appContext, errStr);
        }
        return res;
    }

}
