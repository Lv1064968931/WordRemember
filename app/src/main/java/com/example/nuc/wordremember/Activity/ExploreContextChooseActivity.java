package com.example.nuc.wordremember.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.example.nuc.wordremember.Adapter.ExploreChooseAdapter;
import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ViewItem.ExploreChooseItem;

import java.util.ArrayList;
import java.util.List;

public class ExploreContextChooseActivity extends AppCompatActivity {

    private List<ExploreChooseItem>exploreChooseItemList = new ArrayList<>();
    private boolean flag = true;
    private Mylistener mylistener;


    private TextView txt_top1time;
    private RecyclerView explore_choose_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explore_choose_context);


        bindviews();
    }

    private void bindviews(){


        txt_top1time = (TextView)findViewById(R.id.txt_top1time);
        explore_choose_recyclerview = (RecyclerView)findViewById(R.id.explore_choose_recyclerview);



        //监听事件
        mylistener = new Mylistener();
        txt_top1time.setOnClickListener(mylistener);
        initItem();
        //recyclerview的适配器
        StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager( 2,StaggeredGridLayoutManager.VERTICAL );
        explore_choose_recyclerview.setLayoutManager(staggeredGridLayoutManager);
        ExploreChooseAdapter exploreChooseAdapter = new ExploreChooseAdapter(exploreChooseItemList);
        explore_choose_recyclerview.setAdapter(exploreChooseAdapter);

    }


    //监听类
    private class Mylistener implements View.OnClickListener{

        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.txt_top1time:
                    if (flag == false) {
                        flag = true;

                    } else {
                        flag = false;
                    }
                    txt_top1time.setSelected(flag);
                   finish();
            }
        }
    }

    //初始化recyclerview的项目
    private void initItem(){

        for(int i = 0;i<9;i++) {
            ExploreChooseItem exploreChooseItem0 = new ExploreChooseItem(R.drawable.explore_firstimag, "2019/06/22");
            exploreChooseItemList.add(exploreChooseItem0);
            ExploreChooseItem exploreChooseItem1 = new ExploreChooseItem(R.drawable.explore_thirdimag, "2019/06/21");
            exploreChooseItemList.add(exploreChooseItem1);
        }
    }
}
