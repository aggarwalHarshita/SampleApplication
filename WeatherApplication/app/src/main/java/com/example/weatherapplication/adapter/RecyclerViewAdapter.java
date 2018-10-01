package com.example.weatherapplication.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapplication.R;
import com.example.weatherapplication.TemperatureTextView;
import com.example.weatherapplication.mvp.ForecastListItem;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.weatherapplication.Constants.IMG_SRC_W_URL;
import static com.example.weatherapplication.Constants.TEMPERATURE_CELSIUS;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private RecyclerViewAdapter.ClickListener clickListener;
    private final static SimpleDateFormat sdf = new SimpleDateFormat("E, dd-MM");

    private Context context;

    List<ForecastListItem> forecastListItems;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        forecastListItems = new ArrayList<>();
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.recycler_view_list_row, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ForecastListItem item = forecastListItems.get(position);

        holder.weatherDesc.setText(item.getMain());

        holder.weatherDate.setText(getReadableDate(position));

        String formatter = context.getString(R.string.format_temperature);
        String formattedTemperature = String.format(formatter, convertKelvinToCelsius(item.getTemp()));
        holder.weatherHighTemp.setText(formattedTemperature);

        holder.weatherUnitTemp.setTemperatureUnit(TEMPERATURE_CELSIUS);

        Picasso.with(context)
                .load(IMG_SRC_W_URL + item.getImgIcon())
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .into(holder.weatherIcon);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_img) ImageView weatherIcon;
        @BindView(R.id.weather_date_text) TextView weatherDate;
        @BindView(R.id.weather_desc_text) TextView weatherDesc;
        @BindView(R.id.weather_high_temp_text) TextView weatherHighTemp;
        @BindView(R.id.weather_temp_unit_text) TemperatureTextView weatherUnitTemp;
        @BindView(R.id.cardView) CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent(forecastListItems.get(getAdapterPosition()).getHumidity().toString(), forecastListItems.get(getAdapterPosition()).getPressure().toString());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return forecastListItems.size();
    }

    public String getReadableDate(int i) {
        Date d = new Date();
        Calendar gc = new GregorianCalendar();
        gc.setTime(d);
        gc.add(GregorianCalendar.DAY_OF_MONTH, i);

        return sdf.format(gc.getTime());
    }

    //T(°C) = T(K) - 273.15
    private double convertKelvinToCelsius(double kelvin) {
        return kelvin - 273.16;
    }

    public interface ClickListener {
        void launchIntent(String humidity, String pressure);
    }

    public void setData(List<ForecastListItem> data, Context context) {
        forecastListItems.clear();
        this.forecastListItems.addAll(data);
        this.context = context;
        notifyDataSetChanged();
    }
}
