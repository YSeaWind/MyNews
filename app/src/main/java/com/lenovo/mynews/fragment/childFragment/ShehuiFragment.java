package com.lenovo.mynews.fragment.childFragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lenovo.mynews.R;
import com.lenovo.mynews.bean.MyJson;
import com.lenovo.mynews.util.HttpUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShehuiFragment extends Fragment {
    HttpUtil util;
    ImageView imageView;

    public ShehuiFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shehui, container, false);
        imageView = view.findViewById(R.id.imageview);
        Button button = view.findViewById(R.id.btn);
        util=new HttpUtil();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        String url = "http://v.juhe.cn/toutiao/index?type=yule&key=f5c521bc79d013645e9d07592aa88aaa";
                        try {
                            String jsonData = util.connectServer(url);
                            Gson gson = new Gson();
                            MyJson myJson = gson.fromJson(jsonData, MyJson.class);
                            String picUrl = myJson.getResult().getData().get(0).getThumbnail_pic_s02();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });
        return view;
    }
}