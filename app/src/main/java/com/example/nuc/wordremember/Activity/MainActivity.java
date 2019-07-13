package com.example.nuc.wordremember.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.nuc.wordremember.Adapter.MyFramePagerAdapter;
import com.example.nuc.wordremember.Fragment.ExploreFragment;
import com.example.nuc.wordremember.Fragment.HomepageFragment;
import com.example.nuc.wordremember.Fragment.MyinfoFragment;
import com.example.nuc.wordremember.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    //UI Object
    private TextView txt_homepage;
    private TextView txt_explore;
    private TextView txt_myinfo;

    //Fragment Object
    private HomepageFragment homepageFragment;
    private MyinfoFragment myinfoFragment;
    private ExploreFragment exploreFragment;


    //viewpager+fragment
    private ViewPager myViewpager;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private List<Fragment> fragmentList;

    //底部导航栏选中的标志
    int position;

    //viewpager监听器
    PageChangeListener pageChangeListener;

    //底部导航栏按钮监听器
    TabChangeListener tabChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        bindViews();
        txt_homepage.performClick();   //模拟一次点击，既进去后选择第一项

        // 获取片段管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        addFragment();

        //对viewPager设置适配器
        myViewpager.setAdapter(new MyFramePagerAdapter(fragmentManager,fragmentList));

    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        txt_homepage = (TextView) findViewById(R.id.txt_homepage);
        txt_explore = (TextView) findViewById(R.id.txt_explore);
        txt_myinfo = (TextView) findViewById(R.id.txt_myinfo);
        myViewpager = (ViewPager)findViewById(R.id.myViewpager);

        //对底部导航栏三个按钮设置监听
        tabChangeListener = new TabChangeListener();
        txt_homepage.setOnClickListener(tabChangeListener);
        txt_explore.setOnClickListener(tabChangeListener);
        txt_myinfo.setOnClickListener(tabChangeListener);

        //viewPager 监听器
        pageChangeListener = new PageChangeListener();
        myViewpager.addOnPageChangeListener(pageChangeListener);

    }

    //重置所有文本的选中状态
    private void setSelected(){
        txt_homepage.setSelected(false);
        txt_explore.setSelected(false);
        txt_myinfo.setSelected(false);
    }


    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction transaction){
        if(homepageFragment != null)transaction.hide(homepageFragment);
        if(exploreFragment != null)transaction.hide(exploreFragment);
        if(myinfoFragment != null)transaction.hide(myinfoFragment);
    }

    //将fragment添加进集合
    private void addFragment(){

        //fragmentList用于存放fragment片段，将片段添加进集合设置适配器
        fragmentList = new ArrayList<Fragment>();
        homepageFragment = new HomepageFragment();
        exploreFragment = new ExploreFragment();
        myinfoFragment = new MyinfoFragment();

        fragmentList.add(homepageFragment);
        fragmentList.add(exploreFragment);
        fragmentList.add(myinfoFragment);

    }


    //viewPager的监听器类
   public class PageChangeListener implements ViewPager.OnPageChangeListener{

       @Override
       public void onPageScrolled(int i, float v, int i1) {
       }

       @Override
       public void onPageSelected(int position) {
           switch (position){
               case 0 :
                   txt_homepage.setSelected(true);
                   txt_explore.setSelected(false);
                   txt_myinfo.setSelected(false);
                   break;
               case 1 :
                   txt_homepage.setSelected(false);
                   txt_explore.setSelected(true);
                   txt_myinfo.setSelected(false);
                   break;
               case 2:
                   txt_homepage.setSelected(false);
                   txt_explore.setSelected(false);
                   txt_myinfo.setSelected(true);
           }

       }

       @Override
       public void onPageScrollStateChanged(int i) {

       }
   }
    //点击底部导航栏按钮的监听器类
    public class TabChangeListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.txt_homepage:
                    setSelected();
                    txt_homepage.setSelected(true);
                    position = 0;
                    break;
                case R.id.txt_explore:
                    setSelected();
                    txt_explore.setSelected(true);
                    position = 1;
                    break;
                case R.id.txt_myinfo:
                    setSelected();
                    txt_myinfo.setSelected(true);
                    position = 2;
            }
           myViewpager.setCurrentItem(position);
        }

    }


}
