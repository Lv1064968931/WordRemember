package com.example.nuc.wordremember.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.nuc.wordremember.Adapter.ExploreAdapter;
import com.example.nuc.wordremember.Adapter.LoveessayAdapter;
import com.example.nuc.wordremember.Application.ChangewordApp;
import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ViewItem.LoveEssay;

import java.util.ArrayList;
import java.util.List;

public class LoveessayActivity extends AppCompatActivity {

    private ListView loveessayListview;
    private LoveessayAdapter loveessayAdapter;
    private List<LoveEssay>loveEssayList = null;
    private ChangewordApp loveessayApp = new ChangewordApp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loveessay);

        loveEssayList = new ArrayList<>();
        initloveEssay();
        bindviews();

    }

    //UI组件初始化与事件绑定
    private void bindviews(){

        loveessayListview = (ListView)findViewById(R.id.loveessayListview);

        loveessayAdapter = new LoveessayAdapter(loveEssayList,LoveessayActivity.this);
        loveessayListview.setAdapter(loveessayAdapter);
    }

    private void initloveEssay(){

        if(ExploreAdapter.loveessayCount != 0) {

            for (int i = 0; i < ExploreAdapter.loveessayCount; i++) {
               LoveEssay loveEssayitem = new LoveEssay(loveessayApp.loveessayList.get(i).getText());
               loveEssayList.add(loveEssayitem);
            }

        }else{
            loveEssayList.clear();
        }

//        for(int i = 0; i<=10;i++){
//            LoveEssay loveEssay = new LoveEssay();
//            loveEssay.setEssayTitle("Jane Eyre");
//            loveEssayList.add(loveEssay);
    }
}
