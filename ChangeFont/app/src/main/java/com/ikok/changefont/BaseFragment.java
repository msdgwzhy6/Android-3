package com.ikok.changefont;

import android.app.Fragment;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Anonymous on 2016/3/29.
 */
public class BaseFragment extends Fragment {

    public static final String ADDRESS = "http://japi.juhe.cn/charconvert/change.from";
    public static final String APP_KEY = "1e706cd709e9b0c4dd268cf640bb2eeb";
    public static final int TO_SIMPLE = 0;
    public static final int TO_COMPLEX = 1;
    public static final int TO_HXW = 2;

    public String parseJson(String json){
        try {
            JSONObject object = new JSONObject(json);
            int result = object.getInt("error_code");
            String outputText;
            if (result == 0){
                outputText = object.getString("outstr");
                return outputText;
            } else {
                Toast.makeText(getActivity(), "出错啦.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
