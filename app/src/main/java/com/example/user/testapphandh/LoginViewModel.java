package com.example.user.testapphandh;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.user.testapphandh.helpers.Utils;
import com.example.user.testapphandh.helpers.ValidationHelper;
import com.example.user.testapphandh.network.Const;
import com.example.user.testapphandh.network.WeatherClient;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public class LoginViewModel extends AndroidViewModel {

    private static final String TAG = LoginViewModel.class.getSimpleName();

    private MutableLiveData<String> weatherInfo = new MutableLiveData<>();
    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

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

    public void requestWeather() {
        if (checkConnection(getApplication(), R.string.no_internet_connection)) {
            showProgress.postValue(true);
            Disposable dp = WeatherClient.getInstance()
                    .getWeatherInCity(Const.SPB_CITY_ID, Locale.getDefault().getLanguage(), Const.UNIT_METRIC)
                    .delay(2, TimeUnit.SECONDS)// only for long request simulation to show progress
                    .subscribe(weatherResponse -> {
                        showProgress.postValue(false);
                        String weather = Utils.format(getApplication(), R.string.weather_info,
                                weatherResponse.getMain().getTemp(),
                                weatherResponse.getMain().getHumidity(),
                                weatherResponse.getWind().getSpeed());
                        weatherInfo.postValue(weather);
                    }, throwable -> {
                        showProgress.postValue(false);
                        handleError(getApplication(), throwable);
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

    private void handleError(Context context, Throwable throwable) {
        //throwable.printStackTrace();
        Utils.err(TAG, throwable.getMessage(), throwable);
        if (throwable instanceof HttpException) {
            HttpException e = (HttpException) throwable;
            Utils.showToast(context, Utils.format(context, R.string.network_error_code, e.code()));
        } else if (throwable instanceof SocketTimeoutException) {
            Utils.showToast(context, R.string.server_timeout);
        } else if (throwable instanceof ConnectException || throwable instanceof UnknownHostException) {
            Utils.showToast(context, R.string.no_internet_connection);
        } else {
            Utils.showToast(context, R.string.unhandled_error);
        }
    }

    public MutableLiveData<String> getWeatherInfo() {
        return weatherInfo;
    }

    public MutableLiveData<Boolean> getShowProgress() {
        return showProgress;
    }
}
