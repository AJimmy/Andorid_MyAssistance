package com.alice.assistance.entity;

/**
 * Created by Administrator on 15-6-27.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-27
 */

/**
 * 书签类，存储书签的名字，网址，图片
 */
public class Mark {
    private String name;
    private String url;
    private String id;

    public Mark() {
    }

    public Mark(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
