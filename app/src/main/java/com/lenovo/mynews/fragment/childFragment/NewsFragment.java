package com.lenovo.mynews.fragment.childFragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.mynews.R;
import com.lenovo.mynews.adapter.MyNewsAdapter;
import com.lenovo.mynews.bean.Data;
import com.lenovo.mynews.bean.MyJson;
import com.lenovo.mynews.services.MyReceiver;
import com.lenovo.mynews.services.WebService;
import com.lenovo.mynews.util.CustomLoadMoreView;
import com.lenovo.mynews.util.HttpUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;


    private int count = 0;
    private int loadMoreCount = 0;
    private MyNewsAdapter myNewsAdapter;
    private MyJson myJson;
    private Data newsData;//新闻数据
    List<Data> dataList;//新闻数据集合
    private View view;
    private Context context;
    private MyReceiver myReceiver;
    //  MyHandler handler;
    private Intent serviceIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_news, null);
            initView();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    private void initView() {
        context = getActivity().getApplicationContext();
        //开启服务
        serviceIntent = new Intent(context, WebService.class);
        serviceIntent.putExtra("type", "top");
        context.startService(serviceIntent);
        initReceiver();
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        myNewsAdapter = new MyNewsAdapter(dataList);
       // myNewsAdapter.setNewData(dataList);
        myNewsAdapter.setLoadMoreView(new CustomLoadMoreView());


        /**
         * item的点击事件
         */
        myNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Data item = myNewsAdapter.getItem(position);
            }
        });

       /* myNewsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       *//* List<MyJson> newsDataList = getDatas(false);

                        if (loadMoreCount == 1) {
                            //正常加载更多，还有下一页
                            myNewsAdapter.addData(newsList);
                            myNewsAdapter.loadMoreComplete();
                        } else if (loadMoreCount == 2) {
                            //返回加载失败
                            myNewsAdapter.loadMoreFail();
                        } else if (loadMoreCount == 3) {
                            //加载到最后
                            myNewsAdapter.addData(newsDataList.subList(0, 6));
                            myNewsAdapter.loadMoreEnd();
                        }*//*
                    }
                }, 3000);
            }
        }, recyclerView);*/
        recyclerView.setAdapter(myNewsAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);
       onRefresh();
    }

    /**
     * 获取数据
     * @param isRefresh
     * @return
     */
    private List<Data> getDatas(boolean isRefresh) {
        if (isRefresh) {
            count = 0;
        }

        if (isRefresh){
            //下拉刷新
            Log.e("getDatas: ", "下拉刷新");

        }
       // List<Data> dataList = new ArrayList<>();
        /*for (int i = 0; i <dataList.size(); i++) {
            if (isRefresh) {
                loadMoreCount = 0;
                //MyJson myJson = new MyJson();
                if (context != null) {
                    context.startService(serviceIntent);
                    context.startService(serviceIntent);
                    //myJson.setRefuresh("下拉刷新数据");
                    dataList.add(newsData);
                }
            } else {
                MyJson myJson = new MyJson();
                myJson.setRefuresh("上拉加载更多数据");
                dataList.add(newsData);
                dataList.add(newsData);
            }
        }*/
        return dataList;
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               List<Data> userDataList = getDatas(true);
                myNewsAdapter.setNewData(userDataList);
                myNewsAdapter.loadMoreComplete();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    /**
     * 广播接收者
     */
    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (WebService.ACTION.equals(action)) {
                myJson = (MyJson) intent.getSerializableExtra("myWebJson");
               dataList = myJson.getResult().getData();
                for (int i = 0; i < dataList.size(); i++) {
                     newsData=dataList.get(i);
                   if(newsData.getThumbnail_pic_s03()==null ||newsData.getThumbnail_pic_s03()==""){
                       newsData.setItemType(1);
                   }else{
                       newsData.setItemType(2);
                   }
                }
            }
        }
    }

    /**
     * 注册广播接收者
     */
    private void initReceiver() {
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WebService.ACTION);
        context.registerReceiver(myReceiver, intentFilter);
    }

    /**
     * fragment不可见时调用
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            //界面可见
           if (context != null) {
                serviceIntent = new Intent(context, WebService.class);
                serviceIntent.putExtra("type", "top");
                context.startService(serviceIntent);
                initReceiver();
            }
        } else {
            //界面不可见 相当于onpause
            if (context != null && serviceIntent != null) {
                context.stopService(serviceIntent);
                context.unregisterReceiver(myReceiver);
            }

        }
    }
}
