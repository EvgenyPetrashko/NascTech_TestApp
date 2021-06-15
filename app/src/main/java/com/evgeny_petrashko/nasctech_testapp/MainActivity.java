package com.evgeny_petrashko.nasctech_testapp;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.evgeny_petrashko.nasctech_testapp.network.WeatherObject;
import com.evgeny_petrashko.nasctech_testapp.viewModels.WeatherViewModel;

import net.cachapa.expandablelayout.ExpandableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kotlin.Lazy;
import static org.koin.java.KoinJavaComponent.inject;

public class MainActivity extends AppCompatActivity {
    // header
    @BindView(R.id.collapse_layout) ExpandableLayout expandableHeader;
    @BindView(R.id.zip_code_title) EditText zip_code;
    @BindView(R.id.enter_zip_code_button) Button enter_zip_code;
    @BindView(R.id.collapse_expand_button) ImageButton collapse_extend_button;
    //body
    @BindView(R.id.location_title) TextView locationTitle;
    @BindView(R.id.temperature_title) TextView temperatureTitle;
    @BindView(R.id.wind_speed_title) TextView wind_speedTitle;
    @BindView(R.id.humidity_title) TextView humidityTitle;
    @BindView(R.id.visibility_title) TextView visibilityTitle;
    @BindView(R.id.time_of_sunrise_title) TextView time_of_sunriseTitle;
    @BindView(R.id.time_of_sunset_title) TextView time_of_sunsetTitle;
    // model
    WeatherViewModel model;
    private Lazy<WeatherViewModel> lazy_model = inject(WeatherViewModel.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        model = lazy_model.getValue();
        ButterKnife.bind(this);
        LiveData();
    }

    @OnClick(R.id.collapse_expand_button)
    void onCollapseExpand(){
        if (expandableHeader.isExpanded()){
            expandableHeader.collapse();
            collapse_extend_button.setImageResource(R.drawable.arrow_down);
        }else {
            expandableHeader.expand();
            collapse_extend_button.setImageResource(R.drawable.arrow_up);
        }
    }

    @OnClick(R.id.enter_zip_code_button)
    void onEnterZipCode(){
        String zip = zip_code.getText().toString();
        model.refresh(zip);
    }

    private void LiveData(){
        model.getWeather().observe(this, new Observer<WeatherObject>() {
            @Override
            public void onChanged(WeatherObject weatherObject) {
                populateView(weatherObject);
            }
        });
    }

    private void populateView(WeatherObject weatherObject){
        if (weatherObject != null){
            locationTitle.setText(weatherObject.getLocation());
            temperatureTitle.setText(weatherObject.getTemperature() + " F");
            wind_speedTitle.setText(weatherObject.getWind_speed() + " mph");
            humidityTitle.setText(Integer.toString(weatherObject.getHumidity()));
            visibilityTitle.setText(weatherObject.getVisibility());
            time_of_sunriseTitle.setText(weatherObject.getTime_sunrise());
            time_of_sunsetTitle.setText(weatherObject.getTime_sunset());
        }
    }

}
