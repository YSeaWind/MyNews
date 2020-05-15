package com.lenovo.mynews.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lenovo.mynews.bean.MyJson;

public class MyReceiver extends BroadcastReceiver {
    public MyJson myJson;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(WebService.ACTION)) {
            myJson = (MyJson) intent.getSerializableExtra("myWebJson");
        }
    }
}
