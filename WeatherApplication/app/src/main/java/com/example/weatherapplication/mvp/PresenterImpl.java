package com.example.weatherapplication.mvp;

import android.util.Log;

import com.example.weatherapplication.Constants;
import com.example.weatherapplication.model.data.Conditions;
import com.example.weatherapplication.model.data.Place;
import com.example.weatherapplication.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PresenterImpl implements MainActivityContract.Presenter {

    APIInterface apiInterface;
    MainActivityContract.View mView;

    @Inject
    public PresenterImpl(APIInterface apiInterface, MainActivityContract.View mView) {
        this.apiInterface = apiInterface;
        this.mView = mView;
        mView.showSpinnerData();
    }

    @Override
    public void loadData(String city) {

        mView.showProgress();



        apiInterface.getForecastByCity(city, Constants.forecastDaysNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Place>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error", e+"");
                        mView.showError("Error occurred");
                        mView.hideProgress();
                    }

                    @Override
                    public void onNext(Place place) {
                        mView.showData( mapPlaceToForecastListItem(place));
                    }

                });
    }

    List<ForecastListItem> mapPlaceToForecastListItem(Place data) {

        List<ForecastListItem> items = new ArrayList<ForecastListItem>();

        if (data != null) {
            for (Conditions condition : data.getList()) {
                items.add(new ForecastListItem(condition));
            }
        }

        return items;
    }

}
