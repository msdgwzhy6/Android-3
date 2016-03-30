package com.ikok.font;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Anonymous on 2016/3/29.
 */
public class SimpleToComplex extends BaseFragment {

    private EditText mInputText;
    private Button mTransBtn;
    private TextView mOutputText;
    private String outputText;
    private Handler handler = new Handler();
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.simple2complex,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        mInputText = (EditText) view.findViewById(R.id.s2c_input_text);
        mTransBtn = (Button) view.findViewById(R.id.s2c_btn);
        mOutputText = (TextView) view.findViewById(R.id.s2c_output_text);

        mTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpUtil(ADDRESS,mInputText.getText().toString(),TO_COMPLEX,APP_KEY).sendHttpRequest(new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        outputText = parseJson(response);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mOutputText.setText(outputText);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(),"出错啦.",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

}
