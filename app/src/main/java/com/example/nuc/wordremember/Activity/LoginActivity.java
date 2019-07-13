package com.example.nuc.wordremember.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ServerTools.MyTextListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class LoginActivity extends AppCompatActivity {

    private TextView forgetpassTV;
    private TextView registerTV;
    private EditText phone_ET;
    private EditText password_ET;
    private Button loginBtn;

    public static String USER_PHONE_NUM;
    MyListener myListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindviews();
    }


    //UI组件初始化与事件绑定

    private void bindviews(){
        forgetpassTV = (TextView)findViewById(R.id.forgetpassTV);
        registerTV = (TextView)findViewById(R.id.regiserTV);
        phone_ET = (EditText)findViewById(R.id.phone_ET);
        password_ET = (EditText)findViewById(R.id.password_ET);
        loginBtn = (Button)findViewById(R.id.loginBtn);

        //事件监听
        myListener = new MyListener();
        forgetpassTV.setOnClickListener(myListener);
        registerTV.setOnClickListener(myListener);
        loginBtn.setOnClickListener(myListener);

    }



    private class MyListener implements View.OnClickListener{

        Intent intent;
        @Override
        public void onClick(View v) {

            @SuppressLint("HandlerLeak")
            Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case 3:
//                           HashMap<String,String> map = JSONTOOL.analyze_once_json(msg.obj.toString());
//                           String s = map.get("user_name");
                            if(msg.obj.toString().equals("登陆成功")){
                                Toast.makeText(LoginActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                                intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                            }
                            break;
                        case 30:
                            Toast.makeText(LoginActivity.this, "服务器错误", Toast.LENGTH_LONG).show();
                            break;
                    }
                    super.handleMessage(msg);
                }
            };
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            switch (v.getId()){
                case R.id.forgetpassTV:
                   intent = new Intent(LoginActivity.this,RegisterActivity.class);
                   startActivity(intent);
                    break;
                case R.id.loginBtn:
                    params.put("phone_num",phone_ET.getText().toString());
                    params.put("password",password_ET.getText().toString());
                    client.post("http://10.130.165.187:8070/login/",params,new MyTextListener(handler,3,30));
                    USER_PHONE_NUM = phone_ET.getText().toString();
                    break;
                case R.id.regiserTV:
                    intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;

            }
        }
    }


}
