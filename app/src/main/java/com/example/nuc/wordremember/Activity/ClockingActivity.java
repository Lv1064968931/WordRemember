package com.example.nuc.wordremember.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nuc.wordremember.Application.ChangewordApp;
import com.example.nuc.wordremember.Fragment.HomepageFragment;
import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ServerTools.MyTextListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import static com.example.nuc.wordremember.Activity.WordexplanationActivity.strangewordCount;

public class ClockingActivity extends AppCompatActivity {

    private Button clockingBtn;

    private Mylistener mylistener;

    ChangewordApp changewordApp,wordapp,clockApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clocking);

        bindviews();
    }

    //UI组件初始化与事件绑定
    private void bindviews(){

        clockingBtn = (Button)findViewById(R.id.clockingBtn);


        //监听事件
        mylistener = new Mylistener();
        clockingBtn.setOnClickListener(mylistener);
    }


    //上传信息的方法

    private void uploadClockingdata(){

        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        if (msg.obj.toString()=="上传成功"){
                            clockApp.ClockingCount = 0;
                            Toast.makeText(ClockingActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(ClockingActivity.this,msg.obj.toString(), Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 30:
                        Toast.makeText(ClockingActivity.this, "网络错误", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("phone_num",LoginActivity.USER_PHONE_NUM);
        params.put("clock_day_count",clockApp.ClockingCount);
        params.put("word_total_count", String.valueOf(HomepageFragment.wordItemList.size()));
        client.post("http://10.130.165.187:8070/uploadclockingdata/",params,new MyTextListener(handler,3,30));

    }
    //监听器类
    private class Mylistener implements View.OnClickListener{
        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.clockingBtn:
                    changewordApp = new ChangewordApp();
                    changewordApp.ChangewordCount = 0;
//                    wordapp = new ChangewordApp();
//                    wordapp.strangewordList.clear();
//                    WordexplanationActivity.strangewordCount = 0;
                    clockApp = new ChangewordApp();
                    clockApp.ClockingCount +=1;
                    uploadClockingdata();
                    intent = new Intent(ClockingActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                    break;

            }
        }
    }
}
