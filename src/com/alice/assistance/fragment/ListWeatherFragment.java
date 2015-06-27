package com.alice.assistance.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alice.assistance.R;
import com.alice.assistance.asynctask.WeatherAsyncTask;

/**
 * Created by Administrator on 15-6-27.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-27
 */

/**
 * 根据Spinner中选择的城市，连接网络，获取天气信息，并显示在Fragment中
 */

public class ListWeatherFragment extends Fragment {
    public static final String TAG = "ListWeatherFragment";
    private FragmentManager manager;
    private String cityId;
    public static final String WEATHER_DIR = "http://weather.123.duba.net/static/weather_info/";

    public ListWeatherFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_weather, container, false);
        manager = getActivity().getSupportFragmentManager();
        cityId = ((WeatherFragment)manager.findFragmentByTag("weather")).getCityId();

        String path = WEATHER_DIR + cityId+".html";
        // 执行异步任务，网络获取json字符串，并解析
        new WeatherAsyncTask(getActivity(), view).execute(path);
        return view;
    }

    /**
     * 获取上一个Fragment(WeatherFragment)的cityID
     */
    public void test(){
        WeatherFragment weatherFragment = (WeatherFragment)manager.findFragmentByTag("weather");
        if (weatherFragment != null) {
            Log.d(TAG, "ListWeatherFragment id   " + weatherFragment.getCityId());
        }
    }
}