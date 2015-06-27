package com.alice.assistance.tools;

import android.content.Context;
import com.alice.assistance.entity.Mark;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-6-27.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-27
 */

/**
 * 通过本地资源id，获取文件，读取文件的json字符串。
 * 解析json字符串为List集合
 */
public class ParserMarkJsonTool {
    public static String getMarkJsonStr(Context mContext, int id) throws IOException {
        InputStream in = mContext.getResources().openRawResource(id);
        int len;
        byte[] b = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = in.read(b)) != -1){
            bos.write(b,0,len);
        }
        return new String(bos.toByteArray(), "utf-8");
    }
    public static List<Mark> parserMarkJson(String jsonStr) throws JSONException {
        List<Mark> list;
        JSONObject object = new JSONObject(jsonStr);
        JSONArray jsonArray = object.getJSONArray("mark");
        list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject urlObject = jsonArray.getJSONObject(i);
            String name = urlObject.getString("name");
            String url = urlObject.getString("url");
            String src = urlObject.getString("src");
            list.add(new Mark(src, name, url));
        }

        return list;
    }
}
