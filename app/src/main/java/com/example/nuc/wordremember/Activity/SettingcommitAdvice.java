package com.example.nuc.wordremember.Activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nuc.wordremember.R;

import java.lang.reflect.Field;

public class SettingcommitAdvice extends AppCompatActivity {

    private Button settingcommitBtn;
    Mylistener mylistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingcommit_advice);

        bindviews();
    }

    //UI组件初始化与事件绑定
    private void bindviews(){

        settingcommitBtn = (Button)findViewById(R.id.settingcommitBtn);


        mylistener = new Mylistener();
        settingcommitBtn.setOnClickListener(mylistener);
    }

    //弹出菜单的dialog自定义
    private void dialogselfDesign(){
        AlertDialog dialog = new AlertDialog.Builder(SettingcommitAdvice.this,R.style.DialogStyle)
                .setTitle("提示")
                .setMessage("提交成功")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(20);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object mAlertController = mAlert.get(dialog);
            //通过反射修改title字体大小和颜色
            Field mTitle = mAlertController.getClass().getDeclaredField("mTitleView");
            mTitle.setAccessible(true);
            TextView mTitleView = (TextView) mTitle.get(mAlertController);
            mTitleView.setTextSize(22);
            mTitleView.setTextColor(Color.BLACK);
            //通过反射修改message字体大小和颜色
            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
            mMessage.setAccessible(true);
            TextView mMessageView = (TextView) mMessage.get(mAlertController);
            mMessageView.setTextSize(20);
            mMessageView.setTextColor(Color.BLACK);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    //监听器类
    private class Mylistener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.settingcommitBtn:
                    dialogselfDesign();
                    break;
            }
        }
    }
}
