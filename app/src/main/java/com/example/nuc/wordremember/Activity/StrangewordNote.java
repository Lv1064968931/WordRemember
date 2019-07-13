package com.example.nuc.wordremember.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nuc.wordremember.Adapter.StrangewordAdapter;
import com.example.nuc.wordremember.Application.ChangewordApp;
import com.example.nuc.wordremember.Fragment.MyinfoFragment;
import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ServerTools.JSONTOOL;
import com.example.nuc.wordremember.ServerTools.MyTextListener;
import com.example.nuc.wordremember.ViewItem.StrangeWord;
import com.example.nuc.wordremember.ViewItem.WordItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.nuc.wordremember.Activity.WordexplanationActivity.strangewordCount;

public class StrangewordNote extends AppCompatActivity {

    private RecyclerView recycler_view;
    private MyinfoFragment myinfoFragment;
    private Mylistener mylistener;

    private StrangeWord strangeWord;
    private ChangewordApp wordapp = new ChangewordApp();
    private static List<StrangeWord>strangeWordList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strangeword_note);
//        initStrangewords();
        requestStrangeword();
        bindviews();
    }

    //UI组件初始化与事件绑定
    private void bindviews(){

        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);

        mylistener = new Mylistener();

        StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager( 1,StaggeredGridLayoutManager.VERTICAL );
        recycler_view.setLayoutManager(staggeredGridLayoutManager);

        Log.i("xxxx",strangeWordList.toString());
        StrangewordAdapter strangewordAdapter =new StrangewordAdapter(StrangewordNote.this,strangeWordList);
        recycler_view.setAdapter(strangewordAdapter);
    }

//    //初始化生词本内容
//    public void initStrangewords(){
//        if(strangewordCount != 0) {
//            Log.i("ojuhc", String.valueOf(strangewordCount));
//            Log.i("ujyh", String.valueOf(wordapp.strangewordList.size()));
//            for (int i = 0; i < strangewordCount; i++) {
//                Log.i("zasfr", String.valueOf(i));
//                strangeWord = new StrangeWord(wordapp.strangewordList.get(i).getWord(), wordapp.strangewordList.get(i).getWord_alphabet(),wordapp.strangewordList.get(i).getWord_exp());
//                strangeWordList.add(strangeWord);
//                Log.i("ssssswws", String.valueOf(strangeWordList));
//            }
//
//        }else{
//            strangeWordList.clear();
//        }
//    }

    //下载用户生词数据
    private void requestStrangeword(){

        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case 3:
                        List<HashMap<String,String>> mapList = JSONTOOL.analyze_some_json(msg.obj.toString());
                        //使用数组之前清空数组防止显示重复数据
                        strangeWordList.clear();
                        for(int i = 0;i<mapList.size();i++){
                            StrangeWord strangeWorditem = new StrangeWord(mapList.get(i).get("word"),mapList.get(i).get("word_alphabet"),mapList.get(i).get("word_exp"));
                            strangeWordList.add(strangeWorditem);
                        }
                        Log.i("testetr", String.valueOf(strangeWordList));
                        bindviews();
                        break;
                    case 30:
                        Toast.makeText(StrangewordNote.this, "服务器错误", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("phone_num", LoginActivity.USER_PHONE_NUM);
        client.post("http://10.130.165.187:8070/downloadstrangeword/", params, new MyTextListener(handler, 3, 30));
    }

    //监听器类
    private  class Mylistener implements View.OnClickListener{

        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){

            }
        }
    }

}
