package com.alice.assistance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.alice.assistance.R;
import com.alice.assistance.entity.WeatherDay;

import java.util.List;

/**
 * Created by Administrator on 15-6-26.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-26
 */

/**
 * 天气Fragment中ListView的自定义Adapter
 */
public class WeatherAdapter extends BaseAdapter {
    private Context mContext;
    private List<WeatherDay> list;

    public WeatherAdapter(Context mContext, List<WeatherDay> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_weather, parent, false);
            holder = new ViewHolder();
            holder.txt_date = (TextView)convertView.findViewById(R.id.item_weather_date);
            holder.txt_weather = (TextView)convertView.findViewById(R.id.item_weather_weather);
            holder.txt_temp = (TextView)convertView.findViewById(R.id.item_weather_temp);

            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        //TODO 第几天的显示需要修改为日期
        holder.txt_date.setText("第" + (position+1)+"天");
        holder.txt_temp.setText(list.get(position).getTemp());
        holder.txt_weather.setText(list.get(position).getWeather());
        return convertView;
    }

    public  static class ViewHolder{
        TextView txt_date;
        TextView txt_weather;
        TextView txt_temp;
    }
}
