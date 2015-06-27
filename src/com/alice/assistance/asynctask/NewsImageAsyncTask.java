package com.alice.assistance.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import com.alice.assistance.tools.HttpTool;

import java.io.IOException;

/**
 * Created by Administrator on 15-6-27.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-27
 */
public class NewsImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private ImageCallBack callBack;

    public NewsImageAsyncTask(ImageCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            byte[] arr = HttpTool.getHttpByteArray(params[0]);
            return BitmapFactory.decodeByteArray(arr, 0, arr.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        callBack.sendImage(bitmap);
    }

    public interface ImageCallBack{
        public void sendImage(Bitmap bitmap);
    }
}
