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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ServerTools.MyTextListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class RegisterActivity extends AppCompatActivity {

    private TextView getvercodeTV;
    private EditText phone1_ET;
    private EditText password1_ET;
    private EditText ver_ET;
    private Button nextstepBtn;

    Mylistener mylistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindviews();
    }

    private void bindviews(){

        getvercodeTV = (TextView)findViewById(R.id.getvercodeTV);
        phone1_ET = (EditText)findViewById(R.id.phone1_ET);
        password1_ET = (EditText)findViewById(R.id.password1_ET);
        ver_ET = (EditText)findViewById(R.id.ver_ET);
        nextstepBtn = (Button)findViewById(R.id.nextstepBtn);

        mylistener = new Mylistener();
        getvercodeTV.setOnClickListener(mylistener);
        nextstepBtn.setOnClickListener(mylistener);
    }

    private class Mylistener implements View.OnClickListener{
        Intent intent;
        //注册handler
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        if (msg.obj.toString()=="注册成功"){
                            Toast.makeText(RegisterActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                            intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,msg.obj.toString(), Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 30:
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        //验证码handler
        @SuppressLint("HandlerLeak")
        Handler mhandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        if(msg.obj.toString() == "已发送验证码"){
                            Toast.makeText(RegisterActivity.this, "发送成功", Toast.LENGTH_LONG).show();}
                        else{
                            Toast.makeText(RegisterActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                        }

                        break;
                    case 30:
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client1 = new AsyncHttpClient();
        RequestParams params1 = new RequestParams();
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.getvercodeTV:
                    params1.put("phone",phone1_ET.getText().toString());
                    Log.i("xxx",phone1_ET.getText().toString());
                    client1.post("http://10.130.165.187:8070/verify/",params1,new MyTextListener(mhandler,3,30));
                    break;
                case  R.id.nextstepBtn:
                    params.put("phone",phone1_ET.getText().toString());
                    params.put("password",password1_ET.getText().toString());
                    params.put("code",ver_ET.getText().toString());
                    client.post("http://10.130.165.187:8070/register/",params,new MyTextListener(handler,3,30));
                    break;

                default:
                    break;

            }
        }
    }
}
