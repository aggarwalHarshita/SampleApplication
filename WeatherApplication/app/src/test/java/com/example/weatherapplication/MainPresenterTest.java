package com.example.weatherapplication;

import com.example.weatherapplication.model.data.Place;
import com.example.weatherapplication.mvp.MainActivityContract;
import com.example.weatherapplication.mvp.PresenterImpl;
import com.example.weatherapplication.retrofit.APIInterface;

import org.junit.Before;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Observable;

public class MainPresenterTest {

    @Mock
    private MainActivityContract.View mainView;

    @Mock
    APIInterface apiInterface;

    private PresenterImpl presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new PresenterImpl(apiInterface, mainView);
    }

    @Test
    public void loadData(){
        Mockito.when(apiInterface.getForecastByCity(Constants.CITY_NAME, Constants.forecastDaysNum)).thenReturn(Observable.<Place>empty());
    }

}
