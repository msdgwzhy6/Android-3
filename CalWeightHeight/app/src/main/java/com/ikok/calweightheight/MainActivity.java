package com.ikok.calweightheight;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText weight;
    private RadioButton isMan;
    private RadioButton isWomen;
    private Button calBtn;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = (EditText) findViewById(R.id.weightText);
        isMan = (RadioButton) findViewById(R.id.isMan);
        isWomen = (RadioButton) findViewById(R.id.isWomen);
        calBtn = (Button) findViewById(R.id.calBtn);
        resultText = (TextView) findViewById(R.id.resultText);

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!weight.getText().toString().trim().equals("")){
                    double inputWeight = Double.parseDouble(weight.getText().toString());
                    double calResult = 0.0;
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("-----以下为评估结果：------\n");
                    if (isMan.isChecked() || isWomen.isChecked()){
                        if (isMan.isChecked()){
                            buffer.append("男性标准身高：");
                            calResult = calHeight(inputWeight,"男");
                            buffer.append((int)calResult + "cm");
                        } else if (isWomen.isChecked()){
                            buffer.append("女性标准身高：");
                            calResult = calHeight(inputWeight,"女");
                            buffer.append((int)calResult + "cm");
                        }
                        resultText.setText(buffer);
                    } else {
                        showMessage("请选择您的性别..");
                    }
                } else {
                    showMessage("请输入您的体重..");
                }


            }
        });
    }

    /**
     * 显示提示信息
     * @param message
     */
    private void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * 计算标准身高
     * @param weight
     * @param sex
     * @return
     */
    private double calHeight(double weight, String sex){
        double height = 0.0;
        if (sex.equals("男")){
            height = 170 - (62 - weight)/0.6;
        } else if (sex.equals("女")){
            height = 158 - (52 - weight)/0.5;
        }
        return height;
    }

    /**
     * 创建菜单按钮
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 菜单按钮事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
