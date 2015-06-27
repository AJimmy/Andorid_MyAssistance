package com.alice.assistance.asynctask;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Adapter;
import android.widget.ListView;
import com.alice.assistance.adapter.NewsAdapter;
import com.alice.assistance.entity.News;
import com.alice.assistance.tools.HttpTool;
import com.alice.assistance.tools.ParserNewsJsonTool;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 15-6-27.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-27
 */
public class NewsAsyncTask extends AsyncTask<String, Void, List<News>> {
    private Context mContext;
    private NewsAdapter adapter;
    private ProgressDialog dialog;

    public NewsAsyncTask(Context mContext, NewsAdapter adapter) {
        this.adapter = adapter;
        this.mContext = mContext;
        dialog = new ProgressDialog(mContext);
        dialog.setMessage("正在刷新...");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.show();
    }

    @Override
    protected List<News> doInBackground(String... params) {
        try {
            byte[] arr = HttpTool.getHttpByteArray(params[0]);
            if (arr != null) {
                return ParserNewsJsonTool.parserJson(new String(arr, "utf-8"));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<News> newses) {
        super.onPostExecute(newses);
        dialog.dismiss();
        adapter.addList(newses);
        adapter.notifyDataSetChanged();
    }
}
