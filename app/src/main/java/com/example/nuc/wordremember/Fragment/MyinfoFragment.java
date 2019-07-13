package com.example.nuc.wordremember.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuc.wordremember.Activity.AccountManagerActivity;
import com.example.nuc.wordremember.Activity.LoginActivity;
import com.example.nuc.wordremember.Activity.LoveessayActivity;
import com.example.nuc.wordremember.Activity.TaskSettingActivity;
import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.Activity.SettingcommitAdvice;
import com.example.nuc.wordremember.Activity.StrangewordNote;

public class MyinfoFragment extends Fragment {

    private Button logoutBtn;
    private Button strangenoteBtn;
    private Button loveessayBtn;
    private Button settingBtn;
    private PopupWindow popupWindow;

    Mylistener mylistener;
    Popmylistener popmylistener;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.myinfo_content,container,false);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bindviews();

    }


    //UI组件初始化与事件绑定
    private void bindviews(){

        logoutBtn = (Button)getActivity().findViewById(R.id.logoutBtn);
        strangenoteBtn = (Button)getActivity().findViewById(R.id.strangenoteBtn);
        loveessayBtn = (Button)getActivity().findViewById(R.id.loveessayBtn);
        settingBtn = (Button)getActivity().findViewById(R.id.settingBtn);

        //监听事件
        mylistener = new Mylistener();
        logoutBtn.setOnClickListener(mylistener);
        loveessayBtn.setOnClickListener(mylistener);
        strangenoteBtn.setOnClickListener(mylistener);
        settingBtn.setOnClickListener(mylistener);
    }

    //弹出菜单
    private void Popwindow(){

        View PopwindowView = getLayoutInflater().inflate(R.layout.myinfo_popwindow_menu,null,false);
        View parent = View.inflate(getContext(), R.layout.myinfo_content, null);
        popupWindow=new PopupWindow(PopwindowView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,true);
                popupWindow.showAtLocation(parent, Gravity.TOP + Gravity.RIGHT, 20, 50);//在布局的右上角弹出
        popupWindow.setFocusable(true);
        popOutShadow(popupWindow);

        //弹出菜单组建初始化与事件绑定
        TextView popwindow_account_TV = (TextView)PopwindowView.findViewById(R.id.popwindow_account_TV);
        TextView popwindow_task_TV = (TextView)PopwindowView.findViewById(R.id.popwindow_task_TV);
        TextView popwindow_about_TV = (TextView)PopwindowView.findViewById(R.id.popwindow_about_TV);
        TextView popwindow_advice_TV = (TextView)PopwindowView.findViewById(R.id.popwindow_advice_TV);

        popmylistener = new Popmylistener();
        popwindow_account_TV.setOnClickListener(popmylistener);
        popwindow_task_TV.setOnClickListener(popmylistener);
        popwindow_about_TV.setOnClickListener(popmylistener);
        popwindow_advice_TV.setOnClickListener(popmylistener);


    }

    //弹出菜单的监听器类
    private class Popmylistener implements View.OnClickListener{

        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.popwindow_account_TV:
                    popupWindow.dismiss();
                    intent = new Intent(getActivity(),AccountManagerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.popwindow_task_TV:
                    popupWindow.dismiss();
                    intent = new Intent(getActivity(),TaskSettingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.popwindow_about_TV:
                    popupWindow.dismiss();
                    dialogselfDesign();
                    break;
                case R.id.popwindow_advice_TV:
                    popupWindow.dismiss();
                    intent = new Intent(getActivity(),SettingcommitAdvice.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    //弹出菜单的dialog自定义
    private void dialogselfDesign(){
        AlertDialog dialog = new AlertDialog.Builder(getContext(),R.style.DialogStyle)
                .setTitle("关于")
                .setMessage("版本号 1.0.0")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
        //修改“确认”、“取消”按钮的字体大小
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(20);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);

    }

    /**
     * 让popupwindow以外区域阴影显示
     * @param popupWindow
     */
    private void popOutShadow(PopupWindow popupWindow) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;//设置阴影透明度
        getActivity().getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
    }

    //监听器类
    private class Mylistener implements View.OnClickListener{
        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.logoutBtn:
                    intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    break;
                case R.id.strangenoteBtn:
                    intent = new Intent(getActivity(),StrangewordNote.class);
                    startActivity(intent);
                    break;
                case R.id.loveessayBtn:
                    intent = new Intent(getActivity(),LoveessayActivity.class);
                    startActivity(intent);
                    break;
                case R.id.settingBtn:
                    Popwindow();
                    break;
            }
        }
    }


}
