package com.evgeny_petrashko.nasctech_testapp;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.cachapa.expandablelayout.ExpandableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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

}
