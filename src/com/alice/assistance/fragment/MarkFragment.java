package com.alice.assistance.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.alice.assistance.R;
import com.alice.assistance.adapter.MarkAdapter;
import com.alice.assistance.entity.Mark;
import com.alice.assistance.tools.ParserMarkJsonTool;
import com.alice.assistance.tools.ParserNewsJsonTool;
import org.json.JSONException;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created by Administrator on 15-6-26.
 * Project: MyAssistance
 * User: Alice
 * Data: 15-6-26
 */
public class MarkFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private List<Mark> list;

    public MarkFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mark, container, false);
        String markJsonStr;
        try {
            markJsonStr = ParserMarkJsonTool.getMarkJsonStr(getActivity(), R.raw.mark_json);
            if (markJsonStr != null) {
                list = ParserMarkJsonTool.parserMarkJson(markJsonStr);
                if (list != null){
                    MarkAdapter adapter = new MarkAdapter(getActivity(), list);
                    listView = (ListView) view.findViewById(R.id.list_mark);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(this);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.equals(listView)){
            String url = list.get(position).getUrl();
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }
}