package com.ehappy.connect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by 俊翔 on 2017/1/11.
 */

public class test extends AppCompatActivity {
    private Button nextpage;
    private RadioButton n1_1;
    private RadioButton n1_2;
    private RadioButton n2_1;
    private RadioButton n2_2;
    private RadioButton n3_1;
    private RadioButton n3_2;
    private RadioButton n4_1;
    private RadioButton n4_2;
    private RadioButton n5_1;
    private RadioButton n5_2;
    private RadioButton n6_1;
    private RadioButton n6_2;
    private RadioButton n7_1;
    private RadioButton n7_2;
    private RadioButton n8_1;
    private RadioButton n8_2;
    private RadioButton n9_1;
    private RadioButton n9_2;
    private RadioButton n10_1;
    private RadioButton n10_2;
    private RadioGroup RadioGroup1;
    private RadioGroup RadioGroup2;
    private RadioGroup RadioGroup3;
    private RadioGroup RadioGroup4;
    private RadioGroup RadioGroup5;
    private RadioGroup RadioGroup6;
    private RadioGroup RadioGroup7;
    private RadioGroup RadioGroup8;
    private RadioGroup RadioGroup9;
    private RadioGroup RadioGroup10;

    private int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        RadioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        n1_1 = (RadioButton) findViewById(R.id.a1_1);
        n1_2 = (RadioButton) findViewById(R.id.a1_2);
        RadioGroup1.setOnCheckedChangeListener(mChangeRadio);

        RadioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        n2_1 = (RadioButton) findViewById(R.id.a2_1);
        n2_2 = (RadioButton) findViewById(R.id.a2_2);
        RadioGroup2.setOnCheckedChangeListener(mChangeRadio);

        RadioGroup3 = (RadioGroup) findViewById(R.id.radioGroup3);
        n3_1 = (RadioButton) findViewById(R.id.a3_1);
        n3_2 = (RadioButton) findViewById(R.id.a3_2);
        RadioGroup3.setOnCheckedChangeListener(mChangeRadio);

        RadioGroup4 = (RadioGroup) findViewById(R.id.radioGroup4);
        n4_1 = (RadioButton) findViewById(R.id.a4_1);
        n4_2 = (RadioButton) findViewById(R.id.a4_2);
        RadioGroup4.setOnCheckedChangeListener(mChangeRadio);

        RadioGroup5 = (RadioGroup) findViewById(R.id.radioGroup5);
        n5_1 = (RadioButton) findViewById(R.id.a5_1);
        n5_2 = (RadioButton) findViewById(R.id.a5_2);
        RadioGroup5.setOnCheckedChangeListener(mChangeRadio);

        RadioGroup6 = (RadioGroup) findViewById(R.id.radioGroup6);
        n6_1 = (RadioButton) findViewById(R.id.a6_1);
        n6_2 = (RadioButton) findViewById(R.id.a6_2);
        RadioGroup6.setOnCheckedChangeListener(mChangeRadio);

        RadioGroup7 = (RadioGroup) findViewById(R.id.radioGroup7);
        n7_1 = (RadioButton) findViewById(R.id.a7_1);
        n7_2 = (RadioButton) findViewById(R.id.a7_2);
        RadioGroup7.setOnCheckedChangeListener(mChangeRadio);

        RadioGroup8 = (RadioGroup) findViewById(R.id.radioGroup8);
        n8_1 = (RadioButton) findViewById(R.id.a8_1);
        n8_2 = (RadioButton) findViewById(R.id.a8_2);
        RadioGroup8.setOnCheckedChangeListener(mChangeRadio);

        RadioGroup9 = (RadioGroup) findViewById(R.id.radioGroup9);
        n9_1 = (RadioButton) findViewById(R.id.a9_1);
        n9_2 = (RadioButton) findViewById(R.id.a9_2);
        RadioGroup9.setOnCheckedChangeListener(mChangeRadio);

        RadioGroup10 = (RadioGroup) findViewById(R.id.radioGroup10);
        n10_1 = (RadioButton) findViewById(R.id.a10_1);
        n10_2 = (RadioButton) findViewById(R.id.a10_2);
        RadioGroup10.setOnCheckedChangeListener(mChangeRadio);

        //換頁
        nextpage = (Button)findViewById(R.id.next);
        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("score",Integer.toString(score));
                intent.putExtras(bundle);
                setResult(8888, intent); //requestCode需跟A.class的一樣
                finish();

            }
        });
    }

    private RadioGroup.OnCheckedChangeListener mChangeRadio = new
            RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId)
                {
                    // TODO Auto-generated method stub
                    //計算分數
                    if(checkedId==n1_1.getId()) { score+=1;}
                    else if(checkedId==n1_2.getId()) { score-=1;}
                    else if(checkedId==n2_1.getId()) { score+=1;}
                    else if(checkedId==n2_2.getId()) { score-=1;}
                    else if(checkedId==n3_1.getId()) { score+=1;}
                    else if(checkedId==n3_2.getId()) { score-=1;}
                    else if(checkedId==n4_1.getId()) { score+=1;}
                    else if(checkedId==n4_2.getId()) { score-=1;}
                    else if(checkedId==n5_1.getId()) { score+=1;}
                    else if(checkedId==n5_2.getId()) { score-=1;}
                    else if(checkedId==n6_1.getId()) { score+=1;}
                    else if(checkedId==n6_2.getId()) { score-=1;}
                    else if(checkedId==n7_1.getId()) { score+=1;}
                    else if(checkedId==n7_2.getId()) { score-=1;}
                    else if(checkedId==n8_1.getId()) { score+=1;}
                    else if(checkedId==n8_2.getId()) { score-=1;}
                    else if(checkedId==n9_1.getId()) { score+=1;}
                    else if(checkedId==n9_2.getId()) { score-=1;}
                    else if(checkedId==n10_1.getId()) { score+=1;}
                    else if(checkedId==n10_2.getId()) { score-=1;}
                }
            };
}


