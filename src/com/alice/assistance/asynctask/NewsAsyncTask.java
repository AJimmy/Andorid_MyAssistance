package com.alice.assistance.asynctask;

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
    private List<News> list;
    private NewsAdapter adapter;
    private ListView listView;

    public NewsAsyncTask(NewsAdapter adapter, List<News> list, ListView listView) {
        this.adapter = adapter;
        this.list = list;
        this.listView = listView;
    }

    @Override
    protected List<News> doInBackground(String... params) {
        try {
            byte[] arr = HttpTool.getHttpByteArray(params[0]);
            //TODO 解析网络获取的json，得到List<News>
            return ParserNewsJsonTool.parserJson(new String(arr, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<News> newses) {
        super.onPostExecute(newses);
        adapter.addList(newses);
        adapter.notifyDataSetChanged();
    }
}
