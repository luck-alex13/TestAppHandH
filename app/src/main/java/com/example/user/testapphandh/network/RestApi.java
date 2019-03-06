package com.example.user.testapphandh.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET(Const.WEATHER)
    Observable<BaseRequest> getWeatherInCity(
            @Query("id") String cityId
    );
}
