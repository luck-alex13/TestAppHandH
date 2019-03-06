package com.example.user.testapphandh.network;

import com.example.user.testapphandh.BuildConfig;
import com.example.user.testapphandh.data.WeatherResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {

    private static final int TIME_OUT = 60;

    private static WeatherClient _instance;
    private final RestApi restApi;

    public WeatherClient() {

        restApi = createApi();
    }

    private RestApi createApi() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS);

        httpClient.addNetworkInterceptor(new ApiKeyInterceptor(Const.API_KEY));
        //logging
        if (BuildConfig.LOGS_ENABLED) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            // add logging as last interceptor
            httpClient.addInterceptor(logging);
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        if (BuildConfig.LOCALE) {
            builder.baseUrl(Const.LOCAL_API_URL);
        } else {
            builder.baseUrl(Const.PROD_API_URL);
        }

        Retrofit retrofit = builder
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(RestApi.class);
    }

    public static WeatherClient getInstance() {
        if (_instance == null)
            _instance = new WeatherClient();
        return _instance;
    }

    private <T> Observable<T> makeAsynk(Observable<T> func) {
        return func.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<WeatherResponse> getWeatherInCity(String cityId, String languageCode, String unit) {
        return makeAsynk(restApi.getWeatherInCity(cityId, languageCode, unit));
    }
}
