package com.alice.assistance.entity;

import java.util.List;

/**
 * Created by Administrator on 15-6-26.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-26
 */
public class Weather {
    private String city;//城市名称，eg:北京
    private String cityid;//城市编号， eg:"101010100"
    private String week;//星期，eg:星期六
    private String date_y;//日期，eg:2015年06月27日
    private List<String> fl;//6个 风力 eg:微风
    private List<String> img;//12个，图片的编号 eg:2
    private List<String> img_title;//12个，图片名称，eg:阴
    private String pm;// pm2.5 eg:50
    private String pm_level;//pm等级 eg:优
    private List<String> temp;//最高温~最低温 6个， eg:"31℃~22℃"
    private List<String> weather;// 6个，eg:阴
    private String update_time;//更新时间，json字符串中不在一个类中

    public Weather() {
    }

    public Weather(String cityName) {
        this.city = cityName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getDate_y() {
        return date_y;
    }

    public void setDate_y(String date_y) {
        this.date_y = date_y;
    }

    public List<String> getFl() {
        return fl;
    }

    public void setFl(List<String> fl) {
        this.fl = fl;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public List<String> getImg_title() {
        return img_title;
    }

    public void setImg_title(List<String> img_title) {
        this.img_title = img_title;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getPm_level() {
        return pm_level;
    }

    public void setPm_level(String pm_level) {
        this.pm_level = pm_level;
    }

    public List<String> getTemp() {
        return temp;
    }

    public void setTemp(List<String> temp) {
        this.temp = temp;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public List<String> getWeather() {
        return weather;
    }

    public void setWeather(List<String> weather) {
        this.weather = weather;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", cityid='" + cityid + '\'' +
                ", week='" + week + '\'' +
                ", date_y='" + date_y + '\'' +
                ", fl=" + fl +
                ", img=" + img +
                ", img_title=" + img_title +
                ", pm='" + pm + '\'' +
                ", pm_level='" + pm_level + '\'' +
                ", temp=" + temp +
                ", weather=" + weather +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
