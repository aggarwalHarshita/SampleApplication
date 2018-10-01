package com.example.weatherapplication.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.weatherapplication.Constants;
import com.example.weatherapplication.MyApplication;
import com.example.weatherapplication.R;
import com.example.weatherapplication.adapter.RecyclerViewAdapter;
import com.example.weatherapplication.di.component.ApplicationComponent;
import com.example.weatherapplication.di.component.DaggerMainActivityComponent;
import com.example.weatherapplication.di.component.MainActivityComponent;
import com.example.weatherapplication.di.module.MainActivityContextModule;
import com.example.weatherapplication.di.module.MainActivityMvpModule;
import com.example.weatherapplication.di.qualifier.ActivityContext;
import com.example.weatherapplication.di.qualifier.ApplicationContext;
import com.example.weatherapplication.mvp.ForecastListItem;
import com.example.weatherapplication.mvp.MainActivityContract;
import com.example.weatherapplication.mvp.PresenterImpl;
import com.example.weatherapplication.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View,  RecyclerViewAdapter.ClickListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.city_spinner) Spinner citySpinner;

    @BindArray(R.array.city_array)
    protected String [] mStrings;

    MainActivityComponent mainActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.setAdapter(recyclerViewAdapter);

        citySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        presenter.loadData(parent.getItemAtPosition(position).toString());
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

    @Override
    public void launchIntent(String humidity, String pressure) {
        Toast.makeText(mContext, "Humidity : " + humidity + " Pressure : " + pressure, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<ForecastListItem> data) {
        recyclerViewAdapter.setData(data, MainActivity.this);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showSpinnerData() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mStrings);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(dataAdapter);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
