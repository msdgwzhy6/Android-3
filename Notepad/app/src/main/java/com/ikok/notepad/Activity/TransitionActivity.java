package com.ikok.notepad.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.ikok.notepad.R;

/**
 * Created by Anonymous on 2016/3/25.
 */
public class TransitionActivity extends Activity {

    boolean isFirstIn = false;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_view);

        final SharedPreferences sharedPreferences = getSharedPreferences("is_first_in_data",MODE_PRIVATE);
        isFirstIn = sharedPreferences.getBoolean("isFirstIn",true);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                if (isFirstIn){
                    Toast.makeText(TransitionActivity.this,"First log",Toast.LENGTH_SHORT).show();
                    intent = new Intent(TransitionActivity.this,MainActivity.class);
                    TransitionActivity.this.startActivity(intent);
                    TransitionActivity.this.finish();
                } else {
                    intent = new Intent(TransitionActivity.this,MainActivity.class);
                    TransitionActivity.this.startActivity(intent);
                    TransitionActivity.this.finish();
                }
            }
        },2000);




    }

}
