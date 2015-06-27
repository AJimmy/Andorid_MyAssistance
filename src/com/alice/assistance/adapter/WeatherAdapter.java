package com.alice.assistance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

    public static final int[] IMAGE_DAY= {R.drawable.d00, R.drawable.d01, R.drawable.d02, R.drawable.d03,
            R.drawable.d04, R.drawable.d05, R.drawable.d06, R.drawable.d07,
            R.drawable.d08, R.drawable.d09, R.drawable.d10, R.drawable.d11,
            R.drawable.d12, R.drawable.d13, R.drawable.d14, R.drawable.d15,
            R.drawable.d16, R.drawable.d17, R.drawable.d18, R.drawable.d19,
            R.drawable.d20, R.drawable.d21, R.drawable.d22, R.drawable.d23,
            R.drawable.d24, R.drawable.d25, R.drawable.d26, R.drawable.d27,
            R.drawable.d28, R.drawable.d29, R.drawable.d30, R.drawable.d31 };

    public static final int[] IMAGE_NIGHT= {R.drawable.n00, R.drawable.n01, R.drawable.n01, R.drawable.n03,
            R.drawable.n04, R.drawable.n05, R.drawable.n06, R.drawable.n07,
            R.drawable.n08, R.drawable.n09, R.drawable.n10, R.drawable.n11,
            R.drawable.n12, R.drawable.n13, R.drawable.n14, R.drawable.n15,
            R.drawable.n16, R.drawable.n17, R.drawable.n18, R.drawable.n19,
            R.drawable.n20, R.drawable.n21, R.drawable.n22, R.drawable.n23,
            R.drawable.n24, R.drawable.n25, R.drawable.n26, R.drawable.n27,
            R.drawable.n28, R.drawable.n29, R.drawable.n30, R.drawable.n31 };

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
            holder.txt_date = (TextView) convertView.findViewById(R.id.item_weather_date);
            holder.txt_weather = (TextView) convertView.findViewById(R.id.item_weather_weather);
            holder.txt_temp = (TextView) convertView.findViewById(R.id.item_weather_temp);
            holder.image = (ImageView) convertView.findViewById(R.id.item_weather_image);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.txt_date.setText(list.get(position).getDate());
        holder.image.setImageResource(IMAGE_DAY[list.get(position).getImgId()]);
        if (position == 0) {
            holder.txt_temp.setText("最低：" + list.get(position).getTemp());
        } else {
            holder.txt_temp.setText(list.get(position).getTemp());
        }
        holder.txt_weather.setText(list.get(position).getWeather());
        return convertView;
    }

    public static class ViewHolder {
        TextView txt_date;
        ImageView image;
        TextView txt_weather;
        TextView txt_temp;
    }
}
