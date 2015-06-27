package com.alice.assistance.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 15-6-27.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-27
 */
public class HttpTool {
    public static byte[] getHttpByteArray(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(10000);
        con.setDoInput(true);

        if (con.getResponseCode() == 200){
            InputStream in = con.getInputStream();

            byte[] b = new byte[1024];
            int len;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while((len = in.read(b)) != -1){
                bos.write(b, 0, len);
            }
//            new String(bos.toByteArray(), "utf-8")
            return bos.toByteArray();
        }
        return null;
    }

}
