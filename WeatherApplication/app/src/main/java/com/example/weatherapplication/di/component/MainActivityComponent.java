package com.example.weatherapplication.di.component;

import android.content.Context;


import com.example.weatherapplication.di.module.AdapterModule;
import com.example.weatherapplication.di.module.MainActivityMvpModule;
import com.example.weatherapplication.di.qualifier.ActivityContext;
import com.example.weatherapplication.di.scopes.ActivityScope;
import com.example.weatherapplication.ui.MainActivity;

import dagger.Component;


@ActivityScope
@Component(modules = {AdapterModule.class, MainActivityMvpModule.class}, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();

    void injectMainActivity(MainActivity mainActivity);
}
