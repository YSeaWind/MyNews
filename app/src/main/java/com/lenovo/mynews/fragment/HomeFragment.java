package com.lenovo.mynews.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.lenovo.mynews.R;
import com.lenovo.mynews.adapter.CollectRecycleAdapter;
import com.lenovo.mynews.adapter.MyHomeFvAdapter;
import com.lenovo.mynews.bean.Data;
import com.lenovo.mynews.fragment.childFragment.NewsFragment;
import com.lenovo.mynews.fragment.childFragment.ShehuiFragment;
import com.lenovo.mynews.services.WebService;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private List<Fragment> list;
    private View view;
    private ViewPager viewPager;
    private FragmentManager manager;
    private List<Data> dataList = new ArrayList<>();
    RecyclerView recyclerView;
    CollectRecycleAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        } else {
            view = inflater.inflate(R.layout.fraagment_home, null);
            initView();
            return view;
        }
    }

    private void initView() {

        /**
         * 返回一个专用的FragmentManager，用于放置和管理内容。
         * android官方对它的解释是，返回一个FragmentManager为了设置和管理当前Fragment内部的Fragment的们。
         * 举个例子，在Fragment布局中添加了一个Fragment容器，目的是在Fragment执行的过程中动态的添加一个Fragment，并且对他进行操作。
         *
         * 需要管理相互独立的并且隶属于Activity的Fragment使用FragmentManager()，
         * 而在Fragment中动态的添加Fragment要使用getChildFragmetManager（）来管理!
         */
        manager = getChildFragmentManager();
        viewPager = view.findViewById(R.id.viewpager01);
        list = new ArrayList<>();
        list.add(new NewsFragment());
        list.add(new ShehuiFragment());
        viewPager.setAdapter(new MyHomeFvAdapter(manager, 0, list));
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
        recyclerView = view.findViewById(R.id.recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //网格布局
        // GridLayoutManager layoutManager = new GridLayoutManager(this,5);

        recyclerView.setLayoutManager(layoutManager);
        //设置分割线
        /*recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));*/
        adapter = new CollectRecycleAdapter(dataList, getActivity());
        dataList.add(new Data("头条"));
        dataList.add(new Data("社会"));
        dataList.add(new Data("国内"));
        dataList.add(new Data("国际"));
        dataList.add(new Data("娱乐"));
        dataList.add(new Data("体育"));
        dataList.add(new Data("军事"));
        dataList.add(new Data("科技"));
        dataList.add(new Data("财经"));
        dataList.add(new Data("时尚"));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CollectRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                viewPager.setCurrentItem(position);
            }
        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        adapter.setBg(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
