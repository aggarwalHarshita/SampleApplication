package com.example.weatherapplication.retrofit;

import com.example.weatherapplication.Constants;
import com.example.weatherapplication.model.data.Place;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface APIInterface {

    @GET(Constants.API_FORECAST + Constants.API_APP_ID)
    Observable<Place> getForecastByCity(
            @Query("q") String cityName, @Query("cnt") String units);
}
