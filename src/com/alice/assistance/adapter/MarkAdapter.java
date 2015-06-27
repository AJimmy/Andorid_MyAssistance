package com.alice.assistance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.alice.assistance.R;
import com.alice.assistance.entity.Mark;

import java.util.List;

/**
 * Created by Administrator on 15-6-27.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-27
 */
public class MarkAdapter extends BaseAdapter {
    public static final int[] drawable = {0, R.drawable.baidu72, R.drawable.sina72,
            R.drawable.github72, R.drawable.stackoverflow72,
            R.drawable.ic_launcher, R.drawable.zhihu72};

    private Context mContext;
    List<Mark> list;

    public MarkAdapter(Context mContext, List<Mark> list) {
        this.mContext = mContext;
        this.list = list;
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mark, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.item_mark_img);
            holder.name = (TextView) convertView.findViewById(R.id.item_mark_name);
            holder.url = (TextView) convertView.findViewById(R.id.item_mark_url);
            convertView.setTag(holder);

        }
        holder = (ViewHolder) convertView.getTag();
        holder.image.setImageResource(drawable[Integer.parseInt(list.get(position).getId())]);
        holder.name.setText(list.get(position).getName());
        holder.url.setText(list.get(position).getUrl());
        return convertView;
    }

    private static class ViewHolder {
        ImageView image;
        TextView name;
        TextView url;
    }
}
