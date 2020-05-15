package com.lenovo.mynews.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.gson.Gson;
import com.lenovo.mynews.bean.MyJson;
import com.lenovo.mynews.util.HttpUtil;

import org.json.JSONException;

import java.io.IOException;

public class WebService extends Service {
    private HttpUtil httpUtil;
    public static final String ACTION = "com.lenovo.mynews.services.WebService";

    public WebService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        httpUtil=new HttpUtil();
        String type = intent.getStringExtra("type");
        getDataFromServer(type);
        return super.onStartCommand(intent, flags, startId);
    }

    private void getDataFromServer( final String type) {
        new Thread() {
            @Override
            public void run() {
                try {
                    String jsonData = httpUtil.connectServer( type);
                    Gson gson = new Gson();
                    MyJson myJson = gson.fromJson(jsonData, MyJson.class);
                    Intent intent = new Intent();
                    intent.setAction(ACTION);
                    intent.putExtra("myWebJson", myJson);
                    sendBroadcast(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
