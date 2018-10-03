# WeatherApplication
 An application that uses the OpenWeatherMap 5 day weather forecast API to retrieve the next 5 day weather forecast.

**Introduction**

This simple Android app uses the OpenWeatherMap 5 day weather forecast API to retrieve the current 5 day weather forecast and display a part of it in a RecyclerView.
It takes input as city name from user and displays the relevant weather information to the user.

**Running and Building**
- This app was built with Android Studio version 3.2.1. Before building and running, please insert your own Open Weather Api key in Constant.java by altering the OPEN_WEATHER_API String.

**Testing**
- I have tested the app on OnePlus6 device which runs on android 9.

**Following ;notable libraries have been used in this project**
1. Retrofit is type-safe REST client for Android and Java which aims to make it easier to consume RESTful web services.
2. Dagger is a fully static, compile-time dependency injection framework for both Java and Android.
3. RxJava is a Java VM implementation of [Reactive Extensions](http://reactivex.io/): a library for composing asynchronous and event-based programs by using observable sequences.
4. ButterKnife library is a view injection library that injects views into android activity/fragments using annotations.
5. Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. Gson can work with arbitrary Java objects including pre-existing objects that you do not have source-code.
