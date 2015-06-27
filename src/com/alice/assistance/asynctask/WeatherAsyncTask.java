package com.alice.assistance.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.alice.assistance.R;
import com.alice.assistance.adapter.WeatherAdapter;
import com.alice.assistance.entity.Weather;
import com.alice.assistance.entity.WeatherDay;
import com.alice.assistance.tools.HttpTool;
import com.alice.assistance.tools.ParserWeatherJsonTool;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-6-26.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-26
 */
public class WeatherAsyncTask extends AsyncTask<String, Void, Weather> {
    public static final String TAG = "WeatherAsyncTask";
    private Context mContext;
    private View view;
    private WeatherAdapter weatherAdapter;

    public WeatherAsyncTask(Context mContext, View view) {
        this.mContext = mContext;
        this.view = view;
    }



    @Override
    protected Weather doInBackground(String... params) {
        //TODO 提醒正在刷新的Dialog显示
        try {
           byte[] arr =  HttpTool.getHttpByteArray(params[0]);
            String jsonStr= new String(arr, "utf-8");
            Log.d(TAG, jsonStr);
            return ParserWeatherJsonTool.parserWeatherJsonStr(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
        //将Weather类中的信息转为List<WeatherDay>
        List<WeatherDay> days = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            days.add(new WeatherDay(weather.getFl().get(i), weather.getTemp().get(i), weather.getWeather().get(i)));
        }
        TextView txt_city = (TextView) view.findViewById(R.id.weather_txt_city);
        TextView txt_date = (TextView) view.findViewById(R.id.weather_txt_date);
        TextView txt_week = (TextView) view.findViewById(R.id.weather_txt_week);
        TextView txt_update = (TextView) view.findViewById(R.id.weather_txt_update);

        txt_city.setText(weather.getCity());
        txt_date.setText(weather.getDate_y());
        txt_update.setText(weather.getUpdate_time());
        txt_week.setText(weather.getWeek());

        weatherAdapter = new WeatherAdapter(mContext, days);
        ListView listWeather = (ListView) view.findViewById(R.id.list_weather);
        listWeather.setAdapter(weatherAdapter);

    }
}
