package com.example.weatherapplication;

import android.app.Activity;
import android.app.Application;

import com.example.weatherapplication.di.component.ApplicationComponent;
import com.example.weatherapplication.di.component.DaggerApplicationComponent;
import com.example.weatherapplication.di.module.ContextModule;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

