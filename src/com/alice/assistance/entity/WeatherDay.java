package com.alice.assistance.entity;

/**
 * Created by Administrator on 15-6-27.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-27
 */
public class WeatherDay {
    private String date;
    private String fl;
    private int imgId;
    private String temp;
    private String weather;

    public WeatherDay() {
    }

    public WeatherDay(String date, String fl, int imgId, String temp, String weather) {
        this.date = date;
        this.fl = fl;
        this.imgId = imgId;
        this.temp = temp;
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
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
