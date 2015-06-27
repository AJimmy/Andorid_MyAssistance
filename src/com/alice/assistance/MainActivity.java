package com.alice.assistance;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import com.alice.assistance.fragment.MarkFragment;
import com.alice.assistance.fragment.NewsFragment;
import com.alice.assistance.fragment.WeatherFragment;

public class MainActivity extends FragmentActivity {
    private FragmentManager manager;
    private WeatherFragment weatherFragment;
    private NewsFragment newsFragment;
    private MarkFragment markFragment;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        manager = getSupportFragmentManager();

        //开启显示天气
        FragmentTransaction transaction = manager.beginTransaction();
        if (weatherFragment == null) {
            weatherFragment = new WeatherFragment();
        }
        transaction.add(R.id.content_containter, weatherFragment, "weather");
        transaction.commit();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_tab_bar);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.radio_weather:
                        if (weatherFragment == null) {
                            weatherFragment = new WeatherFragment();
                        }
                        transaction.replace(R.id.content_containter, weatherFragment, "weather");
                        break;
                    case R.id.radio_news:
                        if (newsFragment == null) {
                            newsFragment = new NewsFragment();
                        }
                        transaction.replace(R.id.content_containter, newsFragment, "news");
                        break;
                    case R.id.radio_mark:
                        if (markFragment == null) {
                            markFragment = new MarkFragment();
                        }
                        transaction.replace(R.id.content_containter, markFragment, "mark");
                        break;
                }
//                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }


}
