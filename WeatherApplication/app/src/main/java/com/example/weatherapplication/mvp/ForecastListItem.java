package com.example.weatherapplication.mvp;

import com.example.weatherapplication.model.data.Conditions;

public class ForecastListItem {

    private String main;
    private long dt;
    private Double temp;
    private String imgIcon;
    private String humidity;
    private String pressure;

    public ForecastListItem(Conditions condition) {
        main = condition.getWeather().get(0).getMain();
        dt = condition.getDt();
        temp = condition.getMain().getTemp();

        humidity = condition.getMain().getHumidity().toString();
        pressure =  condition.getMain().getPressure().toString();
        imgIcon = condition.getWeather().get(0).getIcon() + ".png";
    }

    public String getMain() {
        return main;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity(){
        return humidity;
    }

    public long getDt() {
        return dt;
    }

    public Double getTemp() {
        return temp;
    }

    public String getImgIcon() {
        return imgIcon;
    }
}
