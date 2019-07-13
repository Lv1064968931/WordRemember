package com.example.nuc.wordremember.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuc.wordremember.Application.ChangewordApp;
import com.example.nuc.wordremember.Fragment.HomepageFragment;
import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ServerTools.MyTextListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class TaskSettingActivity extends AppCompatActivity {


    private RelativeLayout neep_Rlay;
    private RelativeLayout cet4_Rlay;
    private RelativeLayout cet6_Rlay;
    private TextView neep_TV,cet4_TV,cet6_TV;

    private PopupWindow popupWindow;
    public static String wordAccount;
    public static String wordbookTitle;
    Popwindowlistener popwindowlistener;
    Mylistener mylistener;
    ChangewordApp clockApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_setting);

        bindviews();
    }


    private void  bindviews(){

        neep_Rlay = (RelativeLayout)findViewById(R.id.neep_Rlay);
        cet4_Rlay = (RelativeLayout)findViewById(R.id.cet4_Rlay);
        cet6_Rlay = (RelativeLayout)findViewById(R.id.cet6_Rlay);
        neep_TV = (TextView)findViewById(R.id.neep_TV);
        cet4_TV = (TextView)findViewById(R.id.cet4_TV);
        cet6_TV = (TextView)findViewById(R.id.cet6_TV);


        mylistener = new Mylistener();
        neep_Rlay.setOnClickListener(mylistener);
        cet4_Rlay.setOnClickListener(mylistener);
        cet6_Rlay.setOnClickListener(mylistener);
    }



    /**
     * 让popupwindow以外区域阴影显示
     * @param popupWindow
     */
    private void popOutShadow(PopupWindow popupWindow) {
        WindowManager.LayoutParams lp = TaskSettingActivity.this.getWindow().getAttributes();
        lp.alpha = 0.7f;//设置阴影透明度
        TaskSettingActivity.this.getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = TaskSettingActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                TaskSettingActivity.this.getWindow().setAttributes(lp);
            }
        });
    }

    //弹出菜单
    private void Popwindow(){

        View PopwindowView = getLayoutInflater().inflate(R.layout.tasksetting_popwindow_menu,null,false);
        View parent = View.inflate(TaskSettingActivity.this, R.layout.activity_task_setting, null);
        popupWindow=new PopupWindow(PopwindowView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupWindow.showAtLocation(parent, Gravity.TOP, 20, 800);//屏幕中间弹出
        popupWindow.setFocusable(true);
        popOutShadow(popupWindow);

        //弹出菜单组建初始化与事件绑定
        RadioButton total_18 = (RadioButton)PopwindowView.findViewById(R.id.total_18);
        RadioButton total_28 = (RadioButton)PopwindowView.findViewById(R.id.total_28);
        RadioButton total_38 = (RadioButton)PopwindowView.findViewById(R.id.total_38);
        RadioButton total_48 = (RadioButton)PopwindowView.findViewById(R.id.total_48);

        popwindowlistener = new Popwindowlistener();
        total_18.setOnClickListener(popwindowlistener);
        total_28.setOnClickListener(popwindowlistener);
        total_38.setOnClickListener(popwindowlistener);
        total_48.setOnClickListener(popwindowlistener);
    }

    //弹出菜单的dialog自定义
    private void dialogselfDesign(){
        final Intent[] intent = new Intent[1];

        //上传用户相关数据
        //注册handler
        @SuppressLint("HandlerLeak")
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        if (msg.obj.toString()=="上传成功"){
                            Toast.makeText(TaskSettingActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(TaskSettingActivity.this,msg.obj.toString(), Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 30:
                        Toast.makeText(TaskSettingActivity.this, "网络错误", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        final AsyncHttpClient client = new AsyncHttpClient();
        final RequestParams params = new RequestParams();

        AlertDialog dialog = new AlertDialog.Builder(TaskSettingActivity.this,R.style.DialogStyle)
                .setTitle("信息")
                .setMessage("确定更改学习计划吗？")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        params.put("phone_num",LoginActivity.USER_PHONE_NUM);
                        params.put("book_plan_title",TaskSettingActivity.wordbookTitle);
                        params.put("book_plan_count",TaskSettingActivity.wordAccount);
                        client.post("http://10.130.165.187:8070/upload/",params,new MyTextListener(handler,3,30));
                    intent[0] = new Intent(TaskSettingActivity.this,MainActivity.class);
                    startActivity(intent[0]);
                    }
                })
                .create();
        dialog.show();
        //修改“确认”、“取消”按钮的字体大小
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(20);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);

    }


    //弹出菜单中的每个项目的监听事件
    private class Popwindowlistener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.total_18:
                    wordAccount = "18";
                    popupWindow.dismiss();
                    dialogselfDesign();

                    Toast.makeText(TaskSettingActivity.this,wordAccount,Toast.LENGTH_LONG).show();
                    break;
                case R.id.total_28:
                    wordAccount = "28";
                    popupWindow.dismiss();
                    dialogselfDesign();
                    Toast.makeText(TaskSettingActivity.this,wordAccount,Toast.LENGTH_LONG).show();
                    break;
                case R.id.total_38:
                    wordAccount = "38";
                    popupWindow.dismiss();
                    dialogselfDesign();
                    Toast.makeText(TaskSettingActivity.this,wordAccount,Toast.LENGTH_LONG).show();
                    break;
                case R.id.total_48:
                    wordAccount = "48";
                    popupWindow.dismiss();
                    dialogselfDesign();
                    Toast.makeText(TaskSettingActivity.this,wordAccount,Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    //监听器类
    private class Mylistener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){

                case R.id.neep_Rlay:
                    wordbookTitle = neep_TV.getText().toString();
                    Toast.makeText(TaskSettingActivity.this,wordbookTitle,Toast.LENGTH_LONG).show();
                    Popwindow();
                    break;
                case R.id.cet4_Rlay:
                    wordbookTitle = cet4_TV.getText().toString();
                    Toast.makeText(TaskSettingActivity.this,wordbookTitle,Toast.LENGTH_LONG).show();
                    Popwindow();
                    break;
                case R.id.cet6_Rlay:
                    wordbookTitle = cet6_TV.getText().toString();
                    Toast.makeText(TaskSettingActivity.this,wordbookTitle,Toast.LENGTH_LONG).show();
                    Popwindow();
                    break;
            }
        }
    }


}
