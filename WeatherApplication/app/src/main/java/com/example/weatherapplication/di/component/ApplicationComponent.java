package com.example.weatherapplication.di.component;

import android.content.Context;

import com.example.weatherapplication.MyApplication;
import com.example.weatherapplication.di.module.ContextModule;
import com.example.weatherapplication.di.module.RetrofitModule;
import com.example.weatherapplication.di.qualifier.ApplicationContext;
import com.example.weatherapplication.di.scopes.ApplicationScope;
import com.example.weatherapplication.retrofit.APIInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    APIInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication myApplication);
}
