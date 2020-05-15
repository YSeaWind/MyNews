package com.lenovo.mynews.util;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * 自定义返回按钮图片
 */
public class BackImage extends AppCompatImageView {
    Activity mActivity;
    public BackImage(Context context) {
        super(context);

    }

    public BackImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        mActivity = (Activity) context;
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    public BackImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
