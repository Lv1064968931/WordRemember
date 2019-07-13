package com.example.nuc.wordremember.Activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nuc.wordremember.Application.ChangewordApp;
import com.example.nuc.wordremember.Fragment.HomepageFragment;
import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ViewItem.WordItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WordremeberActivity extends AppCompatActivity {

    private Button wordremeberknowBtn;
    private Button wordremembertipbtn;
    private TextView word_TV;
    private TextView wordalp_TV;
    private TextView wordexa_sen_TV;
    private TextView wordexa_seninte_TV;
    private TextView today_reviewTV;
    private TextView today_newsTV;
    private TextView today_allsTV;
    private ImageView play_pron;
    private LinearLayout example_sen_LinLayout;


    private Mylistener mylistener;
    private boolean flag = false;
    Intent intent;

    ChangewordApp changewordApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordremeber);

        bindviews();

    }

   //UI组建初始化与事件绑定
    private void bindviews(){
        wordremembertipbtn = (Button)findViewById(R.id.wordremembertipbtn);
        wordremeberknowBtn = (Button)findViewById(R.id.wordremeberknowBtn);
        word_TV = (TextView)findViewById(R.id.word_TV);
        wordalp_TV = (TextView)findViewById(R.id.wordalp_TV);
        wordexa_sen_TV = (TextView) findViewById(R.id.wordexa_sen_TV);
        wordexa_seninte_TV = (TextView)findViewById(R.id.wordexa_seninte_TV);
        today_reviewTV = (TextView)findViewById(R.id.today_reviewTV);
        today_newsTV = (TextView)findViewById(R.id.today_newsTV);
        today_allsTV = (TextView)findViewById(R.id.today_allsTV);
        example_sen_LinLayout = (LinearLayout)findViewById(R.id.example_sen_LinLayout);
        play_pron = (ImageView)findViewById(R.id.play_pron);


        //监听事件
        mylistener = new Mylistener();
        wordremeberknowBtn.setOnClickListener(mylistener);
        wordremembertipbtn.setOnClickListener(mylistener);
        play_pron.setOnClickListener(mylistener);

    }



    //点击按钮时更换单词及相关内容
    private void changeword(int position){

        word_TV.setText(HomepageFragment.wordItemList.get(position).getWord());
        wordalp_TV.setText(HomepageFragment.wordItemList.get(position).getWord_alphabet());
        wordexa_sen_TV.setText(HomepageFragment.wordItemList.get(position).getWord_example_sentence());
        wordexa_seninte_TV.setText(HomepageFragment.wordItemList.get(position).getWord_example_sentence_exp());
    }

    //改变上部单词数量
    private void changetopNum(int position,int totalcount){
        switch(Integer.parseInt(HomepageFragment.book_plan_count)){

            case 18:
                if(position<=totalcount) {
                    String positionallsNum = String.valueOf(position);
                    today_allsTV.setText(positionallsNum + "/" +HomepageFragment.book_plan_count);
                    String reviewnum = "10";
                    if (position < Integer.parseInt(reviewnum)) {
                        String positoinreviewNum = String.valueOf(position);
                        today_reviewTV.setText(positoinreviewNum + "/"+reviewnum);
                    } else {
                        today_reviewTV.setText(reviewnum + "/"+reviewnum);
                        String newnum = "8";
                        String positionnewsNum = String.valueOf(position - Integer.parseInt(reviewnum));
                        today_newsTV.setText(positionnewsNum + "/"+newnum);
                    }
                }
                break;
            case 28:
                if(position<=totalcount) {
                    String positionallsNum = String.valueOf(position);
                    today_allsTV.setText(positionallsNum + "/" +HomepageFragment.book_plan_count);
                    String reviewnum = "18";
                    if (position < Integer.parseInt(reviewnum)) {
                        String positoinreviewNum = String.valueOf(position);
                        today_reviewTV.setText(positoinreviewNum + "/"+reviewnum);
                    } else {
                        today_reviewTV.setText(reviewnum + "/"+reviewnum);
                        String newnum = "10";
                        String positionnewsNum = String.valueOf(position - Integer.parseInt(reviewnum));
                        today_newsTV.setText(positionnewsNum + "/"+newnum);
                    }
                }
                break;
            case 38:
                if(position<=totalcount) {
                    String positionallsNum = String.valueOf(position);
                    today_allsTV.setText(positionallsNum + "/" +HomepageFragment.book_plan_count);
                    String reviewnum = "23";
                    if (position < Integer.parseInt(reviewnum)) {
                        String positoinreviewNum = String.valueOf(position);
                        today_reviewTV.setText(positoinreviewNum + "/"+reviewnum);
                    } else {
                        today_reviewTV.setText(reviewnum + "/"+reviewnum);
                        String newnum = "15";
                        String positionnewsNum = String.valueOf(position - Integer.parseInt(reviewnum));
                        today_newsTV.setText(positionnewsNum + "/"+newnum);
                    }
                }
                break;
            case 48:
                if(position<=totalcount) {
                    String positionallsNum = String.valueOf(position);
                    today_allsTV.setText(positionallsNum + "/" +HomepageFragment.book_plan_count);
                    String reviewnum = "28";
                    if (position < Integer.parseInt(reviewnum)) {
                        String positoinreviewNum = String.valueOf(position);
                        today_reviewTV.setText(positoinreviewNum + "/"+reviewnum);
                    } else {
                        today_reviewTV.setText(reviewnum + "/"+reviewnum);
                        String newnum = "20";
                        String positionnewsNum = String.valueOf(position - Integer.parseInt(reviewnum));
                        today_newsTV.setText(positionnewsNum + "/"+newnum);
                    }
                }
                break;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        changewordApp = new ChangewordApp();
        Log.i("xxxssdddasdx", String.valueOf(HomepageFragment.wordItemList.size()));
        changeword(changewordApp.ChangewordCount);
        changetopNum(changewordApp.ChangewordCount,HomepageFragment.wordItemList.size());
        Log.i("TAG","changewordcount"+changewordApp.ChangewordCount);

    }


    //监听器类
    private class Mylistener implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.wordremeberknowBtn:

                    intent = new Intent(WordremeberActivity.this, WordexplanationActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                case R.id.wordremembertipbtn:

                    if (flag == false){
                        wordremembertipbtn.setText("没想起来");
                        example_sen_LinLayout.setVisibility(View.VISIBLE);
                        flag = true;
                    }else{
                    intent = new Intent(WordremeberActivity.this, WordexplanationActivity.class);
                    startActivity(intent);
                    finish();
                    }
                    break;
                case R.id.play_pron:
//                    String pron = HomepageFragment.wordItemList.get(changewordApp.ChangewordCount).getWord_example_sentence();
//                    Log.i("xxss","http://word.iciba.com/"+pron);
                    Uri uri = Uri.parse("http://res.iciba.com/resource/amp3/oxford/0/bf/10/bf1008729c93bc9c77f35fba2cd91f3d.mp3");
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(WordremeberActivity.this, uri);
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                // TODO Auto-generated method stub
                                Log.e("MusicReceiver", "a");
                                mp.start();
                            }
                        });
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }

}
