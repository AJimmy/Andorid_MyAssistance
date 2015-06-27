package com.alice.assistance.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 15-6-26.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-26
 */
public class WeatherAsyncTask extends AsyncTask<String, Void, Weather> {
    public static final String TAG = "WeatherAsyncTask";
    public static final  int dayTime = 24*60*60*1000;
    private Context mContext;
    private View view;
    private WeatherAdapter weatherAdapter;
    private ProgressDialog dialog;

    public WeatherAsyncTask(Context mContext, View view) {
        this.mContext = mContext;
        this.view = view;
        dialog = new ProgressDialog(mContext);
        dialog.setMessage("正在刷新...");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.show();
    }

    @Override
    protected Weather doInBackground(String... params) {
        //TODO 提醒正在刷新的Dialog显示
        try {
           byte[] arr =  HttpTool.getHttpByteArray(params[0]);
            if (arr != null) {
                String jsonStr= new String(arr, "utf-8");
//            Log.d(TAG, jsonStr);
                return ParserWeatherJsonTool.parserWeatherJsonStr(jsonStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<WeatherDay> getListOfWeatherDay(Weather weather) throws ParseException {
        if (weather != null) {
            String date = weather.getDate_y()+" "+weather.getWeek();
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 E");
            Date dateObj = format.parse(date);
            long time = dateObj.getTime();
            List<WeatherDay> days = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                String newDate = format.format(new Date(time)).substring(12);
                WeatherDay weatherDay = new WeatherDay(newDate, weather.getFl().get(i), Integer.parseInt(weather.getImg().get(i*2)),
                        weather.getTemp().get(i), weather.getWeather().get(i));
                days.add(weatherDay);
                time += dayTime;
            }
            return days;
        }
        return null;
    }
    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
        //将Weather类中的信息转为List<WeatherDay>
        dialog.dismiss();
        List<WeatherDay> days = null;
        try {
            days = getListOfWeatherDay(weather);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (days != null) {
            TextView txt_city = (TextView) view.findViewById(R.id.weather_txt_city);
            TextView txt_date = (TextView) view.findViewById(R.id.weather_txt_date);
            TextView txt_week = (TextView) view.findViewById(R.id.weather_txt_week);
            TextView txt_update = (TextView) view.findViewById(R.id.weather_txt_update);
            //将现在的时间与更新时间格式为MM月dd日E ， HH:ss:mm
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            SimpleDateFormat dayWeekFormat = new SimpleDateFormat("MM月dd日");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
            SimpleDateFormat updateFormat = new SimpleDateFormat("HH:ss:mm");
            String dateWeek = "";
            String update = "";
            try {
                Date today = format.parse(weather.getDate_y());
                dateWeek = dayWeekFormat.format(today);
                today = format2.parse(weather.getUpdate_time());
                update = updateFormat.format(today);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            txt_city.setText(weather.getCity()+"天气");
            txt_date.setText(dateWeek);
            txt_update.setText("更新："+update);
            txt_week.setText(weather.getWeek());

            weatherAdapter = new WeatherAdapter(mContext, days);
            ListView listWeather = (ListView) view.findViewById(R.id.list_weather);
            listWeather.setAdapter(weatherAdapter);
        }
    }
}
