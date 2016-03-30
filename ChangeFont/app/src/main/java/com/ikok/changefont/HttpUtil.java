package com.ikok.changefont;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Anonymous on 2016/3/29.
 */
public class HttpUtil {

    private String address;
    private String inputText;
    private int type;
    private String key;

    public HttpUtil(String address,String inputText,int type,String key){
        this.address = address;
        this.inputText = inputText;
        this.type = type;
        this.key = key;
    }


    public void sendHttpRequest(final HttpCallbackListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    address = address + "?text=" + URLEncoder.encode(inputText,"UTF-8") + "&type=" + type + "&key=" + key;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.d("Anonymous", "" + address);

                HttpURLConnection conn = null;
                try {
                    URL url = new URL(address);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                    StringBuffer buffer = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    if (listener != null){
                        listener.onFinish(buffer.toString());
                    }
                    Log.d("Anonymous", "sendHttpRequest: buffer:" + buffer.toString());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    if (listener != null){
                        listener.onError(e);
                    }
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }
}
