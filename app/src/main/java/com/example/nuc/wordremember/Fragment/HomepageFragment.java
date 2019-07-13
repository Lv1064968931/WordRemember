package com.example.nuc.wordremember.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuc.wordremember.Activity.LoginActivity;
import com.example.nuc.wordremember.Activity.LoveessayActivity;
import com.example.nuc.wordremember.Activity.MainActivity;
import com.example.nuc.wordremember.Activity.StrangewordNote;
import com.example.nuc.wordremember.Activity.TaskSettingActivity;
import com.example.nuc.wordremember.Activity.WordexplanationActivity;
import com.example.nuc.wordremember.Activity.WordremeberActivity;
import com.example.nuc.wordremember.Application.ChangewordApp;
import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ServerTools.JSONTOOL;
import com.example.nuc.wordremember.ServerTools.MyTextListener;
import com.example.nuc.wordremember.ViewItem.WordItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SuppressLint("ValidFragment")
public class HomepageFragment extends Fragment {

    private Button homepage_startlearningBtn;
    private TextView homepage_booktitle;
    private TextView clock_count;
    private TextView word_total_countTV;
    private TextView newword_count,reviewword_count,notlearningword_count;
    private ProgressBar progressbar;


    public static List<WordItem> wordItemList = new ArrayList<>();
    private Mylistener mylistener;
    String book_plan_title;
    public static String book_plan_count;
    public static String clock_day_count;
    public String word_total_count;
    ChangewordApp clockApp = new ChangewordApp();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_content, container, false);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init_userdata();
        bindviews();
    }


    //下载用户数据
    private void init_userdata(){

        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case 3:
                        HashMap<String,String> map = JSONTOOL.analyze_once_json(msg.obj.toString());
                        for(int i=0;i<map.size();i++){
                            book_plan_title = map.get("book_plan_title");
                            book_plan_count = map.get("book_plan_count");
                        }
                        Log.i("testue",book_plan_count);
                        homepage_booktitle.setText(book_plan_title);

                        switch(Integer.parseInt(book_plan_count)){

                            case 18:
                                newword_count.setText("8");
                                reviewword_count.setText("10");
                                notlearningword_count.setText(book_plan_count);
                                break;
                            case 28:
                                newword_count.setText("10");
                                reviewword_count.setText("18");
                                notlearningword_count.setText(book_plan_count);
                                break;
                            case 38:
                                newword_count.setText("15");
                                reviewword_count.setText("23");
                                notlearningword_count.setText(book_plan_count);
                                break;
                            case 48:
                                newword_count.setText("20");
                                reviewword_count.setText("28");
                                notlearningword_count.setText(book_plan_count);
                                break;
                    }

                        break;
                    case 30:
                        Toast.makeText(getActivity(), "服务器错误", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("phone_num", LoginActivity.USER_PHONE_NUM);
        client.post("http://10.130.165.187:8070/download/", params, new MyTextListener(handler, 3, 30));


        @SuppressLint("HandlerLeak")
        Handler mhandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        HashMap<String,String> map = JSONTOOL.analyze_once_json(msg.obj.toString());
                        for(int i=0;i<map.size();i++){
                            clock_day_count = map.get("clock_day_count");
                            word_total_count = map.get("word_total_count");
                        }
                        clock_count.setText(clock_day_count);
                        word_total_countTV.setText(word_total_count+"/4806");
                        progressbar.setProgress((((Integer.parseInt(word_total_count))*100)/4806));
                        Log.i("prods", String.valueOf((((Integer.parseInt(word_total_count))*100)/4806)));
                        break;
                    case 30:
                        Toast.makeText(getActivity(), "服务器错误", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient mclient = new AsyncHttpClient();
        RequestParams mparams = new RequestParams();

        mparams.put("phone_num", LoginActivity.USER_PHONE_NUM);
        mclient.post("http://10.130.165.187:8070/downloadclockingdata/", mparams, new MyTextListener(mhandler, 3, 30));

    }



    //UI组件初始化与事件绑定
    private void bindviews() {
        homepage_startlearningBtn = (Button) getActivity().findViewById(R.id.homepage_startlearningBtn);
        homepage_booktitle = (TextView)getActivity().findViewById(R.id.homepage_booktitle);
        clock_count = (TextView)getActivity().findViewById(R.id.clock_count);
        newword_count = (TextView)getActivity().findViewById(R.id.newword_count);
        reviewword_count = (TextView)getActivity().findViewById(R.id.reviewword_count);
        word_total_countTV = (TextView)getActivity().findViewById(R.id.word_total_countTV);
        notlearningword_count = (TextView)getActivity().findViewById(R.id.notlearningword_count);
        progressbar = (ProgressBar)getActivity().findViewById(R.id.progressbar);

        //监听事件
        mylistener = new Mylistener();
        homepage_startlearningBtn.setOnClickListener(mylistener);
    }


    //监听器类
    private class Mylistener implements View.OnClickListener{
        Intent intent;
        Bundle bundle;
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case 3:
                            List<HashMap<String,String>> mapList = JSONTOOL.analyze_some_json(msg.obj.toString());
                            for(int i = 0;i<mapList.size();i++){
                                WordItem item = new WordItem(mapList.get(i).get("id"),mapList.get(i).get("alphabet"),mapList.get(i).get("translation"),mapList.get(i).get("voice"),mapList.get(i).get("word_type"),1);
                                wordItemList.add(item);
                            }
                            Log.i("xxxx",wordItemList.toString());
                            intent = new Intent(getActivity(), WordremeberActivity.class);
                            startActivity(intent);
                        break;
                    case 30:
                        Toast.makeText(getActivity(), "服务器错误", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        @Override
        public void onClick(View v) {

            if(WordexplanationActivity.delayUtils.isFastClick()) {
                switch (v.getId()) {

                    case R.id.homepage_startlearningBtn:

                        if(wordItemList.size()<Integer.parseInt(book_plan_count)){
                        params.put("randomcount", book_plan_count);
                        client.post("http://10.130.165.187:8070/fetch/", params, new MyTextListener(handler, 3, 30));}
                        else {
                            intent = new Intent(getActivity(), WordremeberActivity.class);
                            startActivity(intent);
                        }
                        break;

                    }
                }
            }
        }
}