package com.example.nuc.wordremember.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuc.wordremember.Application.ChangewordApp;
import com.example.nuc.wordremember.Fragment.HomepageFragment;
import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ServerTools.MyTextListener;
import com.example.nuc.wordremember.ViewItem.WordItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class WordexplanationActivity extends AppCompatActivity {

    private Button wordexp_nextstepBtn;
    private TextView word2_TV;
    private TextView word2alp_TV;
    private TextView word2exp_TV;
    private TextView word2exa_sen_TV;
    private TextView word2exa_seninte_TV;
    private TextView today2_reviewTV;
    private TextView today2_newsTV;
    private TextView today2_allsTV;
    private TextView addtoStrangeword_TV;
    private ImageView play2_pron;


    private Mylistener mylistener;
    private ChangewordApp wordApp = new ChangewordApp();
    static int strangewordCount = 0;
    Intent intent;

    ChangewordApp changewordApp;

    public static final String action = "worderp.broadcast.action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordexplanation);

        bindviews();
        changewordmessage(changewordApp.ChangewordCount);
        changetopNum(changewordApp.ChangewordCount,HomepageFragment.wordItemList.size());
    }

    //UI组建初始化与事件绑定
    private void bindviews(){

        wordexp_nextstepBtn = (Button)findViewById(R.id.wordexp_nextstepBtn);
        word2_TV = (TextView)findViewById(R.id.word2_TV);
        word2alp_TV = (TextView)findViewById(R.id.word2alp_TV);
        word2exp_TV = (TextView)findViewById(R.id.word2exp_TV);
        word2exa_sen_TV = (TextView)findViewById(R.id.word2exa_sen_TV);
        word2exa_seninte_TV = (TextView)findViewById(R.id.word2exa_seninte_TV);
        today2_reviewTV = (TextView)findViewById(R.id.today2_reviewTV);
        today2_newsTV = (TextView)findViewById(R.id.today2_newsTV);
        today2_allsTV = (TextView)findViewById(R.id.today2_allsTV);
        addtoStrangeword_TV = (TextView)findViewById(R.id.addtoStrangeword_TV);
        play2_pron = (ImageView)findViewById(R.id.play2_pron);

        //监听事件
        mylistener = new Mylistener();
        wordexp_nextstepBtn.setOnClickListener(mylistener);
        addtoStrangeword_TV.setOnClickListener(mylistener);
        play2_pron.setOnClickListener(mylistener);
    }


    //改变单词相关信息的函数
    private void changewordmessage(int position){

        word2_TV.setText(HomepageFragment.wordItemList.get(position).getWord());
        word2alp_TV.setText(HomepageFragment.wordItemList.get(position).getWord_alphabet());
        word2exp_TV.setText(HomepageFragment.wordItemList.get(position).getWord_exp());
        word2exa_sen_TV.setText(HomepageFragment.wordItemList.get(position).getWord_example_sentence());
        word2exa_seninte_TV.setText(HomepageFragment.wordItemList.get(position).getWord_example_sentence_exp());
    }

    //改变上部单词数量
    private void changetopNum(int position,int totalcount){
        switch(Integer.parseInt(HomepageFragment.book_plan_count)){

            case 18:
                if(position<=totalcount) {
                    String positionallsNum = String.valueOf(position);
                    today2_allsTV.setText(positionallsNum + "/" +HomepageFragment.book_plan_count);
                    String reviewnum = "10";
                    if (position < Integer.parseInt(reviewnum)) {
                        String positoinreviewNum = String.valueOf(position);
                        today2_reviewTV.setText(positoinreviewNum + "/"+reviewnum);
                    } else {
                        today2_reviewTV.setText(reviewnum + "/"+reviewnum);
                        String newnum = "8";
                        String positionnewsNum = String.valueOf(position - Integer.parseInt(reviewnum));
                        today2_newsTV.setText(positionnewsNum + "/"+newnum);
                    }
                }
                break;
            case 28:
                if(position<=totalcount) {
                    String positionallsNum = String.valueOf(position);
                    today2_allsTV.setText(positionallsNum + "/" +HomepageFragment.book_plan_count);
                    String reviewnum = "18";
                    if (position < Integer.parseInt(reviewnum)) {
                        String positoinreviewNum = String.valueOf(position);
                        today2_reviewTV.setText(positoinreviewNum + "/"+reviewnum);
                    } else {
                        today2_reviewTV.setText(reviewnum + "/"+reviewnum);
                        String newnum = "10";
                        String positionnewsNum = String.valueOf(position - Integer.parseInt(reviewnum));
                        today2_newsTV.setText(positionnewsNum + "/"+newnum);
                    }
                }
                break;
            case 38:
                if(position<=totalcount) {
                    String positionallsNum = String.valueOf(position);
                    today2_allsTV.setText(positionallsNum + "/" +HomepageFragment.book_plan_count);
                    String reviewnum = "23";
                    if (position < Integer.parseInt(reviewnum)) {
                        String positoinreviewNum = String.valueOf(position);
                        today2_reviewTV.setText(positoinreviewNum + "/"+reviewnum);
                    } else {
                        today2_reviewTV.setText(reviewnum + "/"+reviewnum);
                        String newnum = "15";
                        String positionnewsNum = String.valueOf(position - Integer.parseInt(reviewnum));
                        today2_newsTV.setText(positionnewsNum + "/"+newnum);
                    }
                }
                break;
            case 48:
                if(position<=totalcount) {
                    String positionallsNum = String.valueOf(position);
                    today2_allsTV.setText(positionallsNum + "/" +HomepageFragment.book_plan_count);
                    String reviewnum = "28";
                    if (position < Integer.parseInt(reviewnum)) {
                        String positoinreviewNum = String.valueOf(position);
                        today2_reviewTV.setText(positoinreviewNum + "/"+reviewnum);
                    } else {
                        today2_reviewTV.setText(reviewnum + "/"+reviewnum);
                        String newnum = "20";
                        String positionnewsNum = String.valueOf(position - Integer.parseInt(reviewnum));
                        today2_newsTV.setText(positionnewsNum + "/"+newnum);
                    }
                }
                break;
        }

    }


    // 使两次点击按钮之间的点击间隔不能少于800毫秒，防止多次点击事件
    public static class delayUtils {

        private static final int MIN_CLICK_DELAY_TIME = 800;
        private static long lastClickTime;

        public static boolean isFastClick() {
            boolean flag = false;
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                flag = true;
            }
            lastClickTime = curClickTime;
            return flag;
        }
    }

    //监听类
    private class Mylistener implements View.OnClickListener{

        //注册handler
        @SuppressLint("HandlerLeak")
        Handler mhandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        if (msg.obj.toString()=="上传成功"){
                            Toast.makeText(WordexplanationActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(WordexplanationActivity.this,msg.obj.toString(), Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 30:
                        Toast.makeText(WordexplanationActivity.this, "注册失败", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        private static final long delay = 600;
        private Handler handler = new Handler();
        @Override
        public void onClick(View v) {
            if(delayUtils.isFastClick()) {
                switch (v.getId()) {
                    case R.id.wordexp_nextstepBtn:
                        Log.i("xxx", String.valueOf(HomepageFragment.wordItemList.size()));
                        changewordApp = new ChangewordApp();
                        changewordApp.ChangewordCount += 1;
                        if (changewordApp.ChangewordCount < HomepageFragment.wordItemList.size()) {
                            changetopNum(changewordApp.ChangewordCount,HomepageFragment.wordItemList.size());
                            intent = new Intent(WordexplanationActivity.this, WordremeberActivity.class);
                            //让活动跳转延时600
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(intent);
                                    finish();
                                }
                            }, 600);

                        } else {
                            changetopNum(changewordApp.ChangewordCount,HomepageFragment.wordItemList.size());
                            changewordApp.ChangewordCount = 0;
                            intent = new Intent(WordexplanationActivity.this, ClockingActivity.class);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(intent);
                                    finish();
                                }
                            }, 600);
                        }

                        break;
                    case R.id.addtoStrangeword_TV:
                        strangewordCount += 1;
                        WordItem wordItem =HomepageFragment.wordItemList.get(changewordApp.ChangewordCount);
                        for(int i = strangewordCount-1;i<strangewordCount;i++){
                            wordApp.strangewordList.add(wordItem);
                        }
                        params.put("phone_num",LoginActivity.USER_PHONE_NUM);
                        params.put("word",HomepageFragment.wordItemList.get(changewordApp.ChangewordCount).getWord());
                        params.put("word_alphabet",HomepageFragment.wordItemList.get(changewordApp.ChangewordCount).getWord_alphabet());
                        params.put("word_exp",HomepageFragment.wordItemList.get(changewordApp.ChangewordCount).getWord_exp());
                        client.post("http://10.130.165.187:8070/uploadstrangeword/",params,new MyTextListener(handler,3,30));
                        Toast.makeText(WordexplanationActivity.this,"加入生词本成功",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.play2_pron:
                        Uri uri = Uri.parse("http://res.iciba.com/resource/amp3/oxford/0/bf/10/bf1008729c93bc9c77f35fba2cd91f3d.mp3");
                        MediaPlayer mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(WordexplanationActivity.this, uri);
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
                }
            }
        }
    }
}
