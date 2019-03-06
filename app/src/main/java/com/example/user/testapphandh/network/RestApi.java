package com.example.user.testapphandh.network;

import com.example.user.testapphandh.data.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET(Const.WEATHER)
    Observable<WeatherResponse> getWeatherInCity(
            @Query("id") String cityId,
            @Query("lang") String languageCode,
            @Query("unit") String unit
    );
}
