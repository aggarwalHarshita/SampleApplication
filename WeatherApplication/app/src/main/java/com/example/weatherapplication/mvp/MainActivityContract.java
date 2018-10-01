package com.example.weatherapplication.mvp;

import java.util.List;

public interface MainActivityContract {
    interface View {
        void showData(List<ForecastListItem> data);

        void showError(String message);

        void showComplete();

        void showProgress();

        void hideProgress();

        void showSpinnerData();
    }

    interface Presenter {
        void loadData(String city);
    }
}
