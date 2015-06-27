package com.alice.assistance.entity;

/**
 * Created by Administrator on 15-6-27.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-27
 */
public class WeatherDay {
    private  String fl;
    private String temp;
    private String weather;

    public WeatherDay() {
    }

    public WeatherDay(String fl, String temp, String weather) {
        this.fl = fl;
        this.temp = temp;
        this.weather = weather;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
