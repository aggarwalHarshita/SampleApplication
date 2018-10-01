package com.example.weatherapplication.di.module;


import com.example.weatherapplication.adapter.RecyclerViewAdapter;
import com.example.weatherapplication.di.scopes.ActivityScope;
import com.example.weatherapplication.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getForecastByCity(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }
}
