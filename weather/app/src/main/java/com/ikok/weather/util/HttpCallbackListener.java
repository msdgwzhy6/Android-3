package com.ikok.weather.util;

/**
 * Created by Anonymous on 2016/2/19.
 */
public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);
}
