package com.alice.assistance.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;
import com.alice.assistance.R;
import com.alice.assistance.adapter.NewsAdapter;
import com.alice.assistance.asynctask.NewsAsyncTask;
import com.alice.assistance.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-6-26.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-26
 */
public class NewsFragment extends Fragment implements AbsListView.OnScrollListener {
    public static final String TAG = "NewsFragment";
    public static final String NEWPATH = "http://litchiapi.jstv.com/api/GetFeeds?column=0&PageSize=20&pageIndex=";
    public static final String NEWPATHSUB = "&val=100511D3BE5301280E0992C73A9DEC41";
    public static final String NEWSDIE = "http://litchiapi.jstv.com/";

    private int pageIndex = 1;
    private boolean isBottom = false;

    private ListView listView;
    private NewsAdapter adapter;
    private List<News> list;

    public NewsFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        list = new ArrayList<>();
        adapter = new NewsAdapter(getActivity(), list);
        listView = (ListView) view.findViewById(R.id.list_news);
        listView.setAdapter(adapter);

        new NewsAsyncTask(getActivity(), adapter).execute(NEWPATH + pageIndex++ + NEWPATHSUB);

        listView.setOnScrollListener(this);

        return view;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && isBottom){
            Toast.makeText(getActivity(), "正在加载...", Toast.LENGTH_SHORT).show();
            new NewsAsyncTask(getActivity(), adapter).execute(NEWPATH + pageIndex++ + NEWPATHSUB);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        isBottom = (firstVisibleItem + visibleItemCount)==totalItemCount;
    }
}