package com.alice.assistance.tools;

import com.alice.assistance.entity.News;
import org.json.JSONArray;
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
public class ParserNewsJsonTool {
    public static List<News> parserJson(String jsonStr) throws JSONException {
        List<News> list = null;
        JSONObject jsonObj = new JSONObject(jsonStr);
        if ("ok".equals(jsonObj.getString("status"))){
            list = new ArrayList<>();
            JSONObject paramz = jsonObj.getJSONObject("paramz");
            JSONArray feeds =  paramz.getJSONArray("feeds");
            for (int i = 0; i < feeds.length(); i++) {
                JSONObject dataObj = feeds.getJSONObject(i);
                JSONObject dataObject = dataObj.getJSONObject("data");
                String subject = dataObject.getString("subject");
                String summary = dataObject.getString("summary");
                String cover = dataObject.getString("cover");
                String changed = dataObject.getString("changed");
                list.add(new News(summary, subject, cover, changed));
            }
        }
        return list;
    }
}
