package com.alice.assistance.tools;

import android.util.Log;
import com.alice.assistance.entity.City;
import com.alice.assistance.entity.District;
import com.alice.assistance.entity.Province;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-6-26.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-26
 */
public class PullParserXMLTool {
    public static List<Province> parserWeatherXml(InputStream in) throws XmlPullParserException, IOException {
        List<Province> provinces = null;
        Province province = null;
        List<City> cities = null;
        City city = null;
        List<District> districts = null;
        District district = null;


        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(in, "utf-8");

        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    provinces = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    String tagName = parser.getName();
                    if (tagName.equals("p")) {
                        province = new Province();
                        cities = new ArrayList<>();
                        if ("p_id".equals(parser.getAttributeName(0))) {
                            province.setId(parser.getAttributeValue(0));
                        }
                    } else if (tagName.equals("pn")) {
                        province.setName(parser.nextText());
                    } else if (tagName.equals("c")) {
                        city = new City();
                        districts = new ArrayList<>();
                        if ("c_id".equals(parser.getAttributeName(0))) {
                            city.setId(parser.getAttributeValue(0));
                        }
                    } else if (tagName.equals("cn")) {
                        city.setName(parser.nextText());
                    } else if (tagName.equals("d")) {
                        district = new District();
                        if ("d_id".equals(parser.getAttributeName(0))) {
                            district.setId(parser.getAttributeValue(0));
                        }
                        district.setName(parser.nextText());
                        districts.add(district);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    String tagNameStr = parser.getName();
                    if ("c".equals(tagNameStr)) {
                        city.setDistricts(districts);
                        cities.add(city);
                    } else if ("p".equals(tagNameStr)) {
                        province.setCities(cities);
                        provinces.add(province);
                    }
                    break;
            }
            event = parser.next();
        }

        return provinces;
    }
}
