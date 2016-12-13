package com.example.estebancastro.proyectofinal.networking;

/**
 * Created by ESTEBAN CASTRO on 11/12/2016.
 */

import com.example.estebancastro.proyectofinal.model.WeatherApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface CitiesWeatherService {
    @GET("find")
    Call<WeatherApiResponse> getCitiesWeather(@Query("lat") double latValue,
                                              @Query("lon") double lonValue,
                                              @Query("cnt") int cntValue,
                                              @Query("APPID") String appIdValue,
                                              @Query("units") String unit);
}