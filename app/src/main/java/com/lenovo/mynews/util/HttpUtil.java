package com.lenovo.mynews.util;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    public String connectServer( String type) throws IOException, JSONException {
        String url = "xxxxx=" + type + "xxxxx";
        //okhttp连接器
        OkHttpClient client = new OkHttpClient();
        //请求体
        RequestBody body = new FormBody.Builder().add("", "").build();
        //获取请求
        Request request = new Request.Builder().post(body).url(url).build();
        //发送请求，获得响应（在服务器中获取的数据） 一定要放在子线程中执行
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            //获取响应中的数据，并转换成String
            String data = response.body().string();
            return data;
        } else {
            //获取响应中的数据，并转换成String
            return "网络连接失败，请检查您的网络";
        }
    }
}
