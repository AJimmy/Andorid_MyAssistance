package com.alice.assistance.entity;

import java.util.List;

/**
 * Created by Administrator on 15-6-26.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-26
 */
public class City {
    private String id;
    private String name;
    private List<District> districts;

    public City(List<District> districts, String id, String name) {
        this.districts = districts;
        this.id = id;
        this.name = name;
    }

    public City() {
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
