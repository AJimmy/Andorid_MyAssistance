package com.alice.assistance.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import com.alice.assistance.R;
import com.alice.assistance.entity.City;
import com.alice.assistance.entity.District;
import com.alice.assistance.entity.Province;
import com.alice.assistance.entity.Weather;
import com.alice.assistance.tools.PullParserXMLTool;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 15-6-26.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-26
 */
public class WeatherFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public static final String TAG = "WeatherFragment";
    private ListView listView;
    private List<Weather> weathers;
    private Spinner spinner_city;
    private ArrayAdapter<City> cityAdapter;
    private Spinner spinner_province;
    private ArrayAdapter<Province> provinceAdapter;
    private List<Province> provinces;
    private Spinner spinner_district;
    private ArrayAdapter<District> districtAdapter;
    private List<District> districts;
    private int provinceId;
    private int cityId;//记录当前选择的city的ID;
    private String districtIdStr;

    public String getDistrictIdStr() {
        return districtIdStr;
    }

    public WeatherFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO 界面布局的美化 更新对话框的提醒
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        if (view != null) {
            spinner_province = (Spinner) view.findViewById(R.id.spinner_province);
            spinner_city = (Spinner) view.findViewById(R.id.spinner_city);
            spinner_district = (Spinner) view.findViewById(R.id.spinner_district);

//            解析XML，得到List<Weather> 数据。
            try {
                provinces = PullParserXMLTool.parserWeatherXml(getActivity().getResources().openRawResource(R.raw.citys_weather));
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (provinces != null) {
                //Spinner显示省，市，添加事件监听
                provinceAdapter = new ArrayAdapter<Province>(getActivity(), android.R.layout.simple_list_item_1, provinces);
                spinner_province.setAdapter(provinceAdapter);
                spinner_province.setOnItemSelectedListener(this);
                spinner_city.setOnItemSelectedListener(this);
                spinner_district.setOnItemSelectedListener(this);
            }
        }
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.equals(spinner_province)) {
            cityAdapter = new ArrayAdapter<City>(getActivity(), android.R.layout.simple_list_item_1, provinces.get(position).getCities());
            spinner_city.setAdapter(cityAdapter);
            provinceId = position;
        } else if (parent.equals(spinner_city)) {
            // 加载天气情况，ListWeatherFragment
            districtAdapter = new ArrayAdapter<District>(getActivity(), android.R.layout.simple_list_item_1, provinces.get(provinceId).getCities().get(position).getDistricts());
            spinner_district.setAdapter(districtAdapter);
            cityId = position;
        } else if (parent.equals(spinner_district)) {
            districtIdStr = provinces.get(provinceId).getCities().get(cityId).getDistricts().get(position).getId();
        }
//        Log.d(TAG, "districtIdStr "+districtIdStr);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.weather_container, new ListWeatherFragment());
        transaction.commit();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}