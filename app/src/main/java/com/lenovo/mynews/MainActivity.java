package com.lenovo.mynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lenovo.mynews.fragment.HomeFragment;
import com.lenovo.mynews.fragment.MineFragment;
import com.lenovo.mynews.fragment.VideoFragment;
import com.lenovo.mynews.services.WebService;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener  {
    RadioGroup rgMenu;
    RadioButton rbHome;
    RadioButton rbVideo;
    RadioButton rbMine;
    TextView tvTitle;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    /**
     * 初始化控件
     */
    private void initView() {
        rgMenu = findViewById(R.id.rg_menu);
        rbHome = findViewById(R.id.rb_home);
        rbVideo = findViewById(R.id.rb_video);
        rbMine = findViewById(R.id.rb_mine);
        tvTitle=findViewById(R.id.tv_title);
        rgMenu.setOnCheckedChangeListener(this);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.mFrameLayout, new HomeFragment()).commit();

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.rb_home:
                transaction.replace(R.id.mFrameLayout, new HomeFragment());
                tvTitle.setText("掌上新闻");
                break;
            case R.id.rb_video:
                transaction.replace(R.id.mFrameLayout, new VideoFragment());
                tvTitle.setText("视频");
                break;
            case R.id.rb_mine:
                transaction.replace(R.id.mFrameLayout, new MineFragment());
                tvTitle.setText("我的");
                break;
        }
        transaction.commit();
    }


}
