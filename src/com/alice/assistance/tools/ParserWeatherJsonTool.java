package com.alice.assistance.tools;

import android.util.Log;
import com.alice.assistance.entity.Weather;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-6-27.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-27
 */
public class ParserWeatherJsonTool {
    public static final String TAG = "ParserWeatherJsonTool";
    public static Weather parserWeatherJsonStr(String jsonStr) throws JSONException {
        //json字符串不符合，需要处理
        int start = jsonStr.indexOf("(");
        int end = jsonStr.indexOf(")");
        jsonStr = jsonStr.substring(start + 1, end);
        Weather weather = new Weather();
        JSONObject obj1 = new JSONObject(jsonStr);
        JSONObject weatherinfo = obj1.getJSONObject("weatherinfo");
        weather.setCity(weatherinfo.getString("city"));
        weather.setCityid(weatherinfo.getString("cityid"));
        weather.setWeek(weatherinfo.getString("week"));
        weather.setDate_y(weatherinfo.getString("date_y"));

        List<String> fls = new ArrayList<>();
        fls.add(weatherinfo.getString("fl1"));
        fls.add(weatherinfo.getString("fl2"));
        fls.add(weatherinfo.getString("fl3"));
        fls.add(weatherinfo.getString("fl4"));
        fls.add(weatherinfo.getString("fl5"));
        fls.add(weatherinfo.getString("fl6"));
        weather.setFl(fls);

        List<String> imgs = new ArrayList<>();
        imgs.add(weatherinfo.getString("img1"));
        imgs.add(weatherinfo.getString("img2"));
        imgs.add(weatherinfo.getString("img3"));
        imgs.add(weatherinfo.getString("img4"));
        imgs.add(weatherinfo.getString("img5"));
        imgs.add(weatherinfo.getString("img6"));
        imgs.add(weatherinfo.getString("img7"));
        imgs.add(weatherinfo.getString("img8"));
        imgs.add(weatherinfo.getString("img9"));
        imgs.add(weatherinfo.getString("img10"));
        imgs.add(weatherinfo.getString("img11"));
        imgs.add(weatherinfo.getString("img12"));
        weather.setImg(imgs);

        List<String> img_titles = new ArrayList<>();
        img_titles.add(weatherinfo.getString("img_title1"));
        img_titles.add(weatherinfo.getString("img_title2"));
        img_titles.add(weatherinfo.getString("img_title3"));
        img_titles.add(weatherinfo.getString("img_title4"));
        img_titles.add(weatherinfo.getString("img_title5"));
        img_titles.add(weatherinfo.getString("img_title6"));
        img_titles.add(weatherinfo.getString("img_title7"));
        img_titles.add(weatherinfo.getString("img_title8"));
        img_titles.add(weatherinfo.getString("img_title9"));
        img_titles.add(weatherinfo.getString("img_title10"));
        img_titles.add(weatherinfo.getString("img_title11"));
        img_titles.add(weatherinfo.getString("img_title12"));
        weather.setImg_title(img_titles);

        weather.setPm(weatherinfo.getString("pm"));
        weather.setPm_level(weatherinfo.getString("pm-level"));

        List<String> temps = new ArrayList<>();
        temps.add(weatherinfo.getString("temp1"));
        temps.add(weatherinfo.getString("temp2"));
        temps.add(weatherinfo.getString("temp3"));
        temps.add(weatherinfo.getString("temp4"));
        temps.add(weatherinfo.getString("temp5"));
        temps.add(weatherinfo.getString("temp6"));
        weather.setTemp(temps);

        List<String> weathers = new ArrayList<>();
        weathers.add(weatherinfo.getString("weather1"));
        weathers.add(weatherinfo.getString("weather2"));
        weathers.add(weatherinfo.getString("weather3"));
        weathers.add(weatherinfo.getString("weather4"));
        weathers.add(weatherinfo.getString("weather5"));
        weathers.add(weatherinfo.getString("weather6"));
        weather.setWeather(weathers);

        weather.setUpdate_time(obj1.getString("update_time"));

//        Log.d(TAG, weather.toString());
        return weather;
    }
}
