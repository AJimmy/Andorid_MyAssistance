package com.alice.assistance.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.alice.assistance.R;
import com.alice.assistance.asynctask.NewsImageAsyncTask;
import com.alice.assistance.entity.News;
import com.alice.assistance.fragment.NewsFragment;

import java.util.List;

/**
 * Created by Administrator on 15-6-27.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-27
 */

/**
 * 新闻ListView的自定义适配器
 */
public class NewsAdapter extends BaseAdapter {
    private static final String TAG = "NewsAdapter";
    private Context mContext;
    private List<News> list;
    ViewHolder holder;
    public NewsAdapter(Context mContext, List<News> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public List<News> addList(List<News> list) {
        this.list.addAll(list);
        return this.list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
            holder = new ViewHolder();
            holder.cover = (ImageView) convertView.findViewById(R.id.item_news_cover);
            holder.subject = (TextView) convertView.findViewById(R.id.item_news_subject);
            holder.summary = (TextView) convertView.findViewById(R.id.item_news_summary);
            holder.changed = (TextView) convertView.findViewById(R.id.item_news_changed);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
//        Log.d(TAG, position + ", " + list.get(position).getSubject()+""+list.get(position).getSummary() +""+list.get(position).getChanged());

        holder.subject.setText(list.get(position).getSubject());
        holder.summary.setText(list.get(position).getSummary());
        holder.changed.setText(list.get(position).getChanged().substring(11));
        //TODO 图片刷新错位
        String path = NewsFragment.NEWSDIE + list.get(position).getCover();
        Log.d(TAG, "图片路径："+ path);
        new NewsImageAsyncTask(new NewsImageAsyncTask.ImageCallBack() {
            @Override
            public void sendImage(Bitmap bitmap) {
                holder.cover.setImageBitmap(bitmap);
            }
        }).execute(path);
        return convertView;
    }

    public static class ViewHolder {
        ImageView cover;
        TextView subject;
        TextView summary;
        TextView changed;
    }
}
