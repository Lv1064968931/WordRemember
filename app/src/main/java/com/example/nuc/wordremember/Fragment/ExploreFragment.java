package com.example.nuc.wordremember.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuc.wordremember.Activity.ExploreContextChooseActivity;
import com.example.nuc.wordremember.Adapter.ExploreAdapter;
import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ServerTools.JSONTOOL;
import com.example.nuc.wordremember.ServerTools.MyTextListener;
import com.example.nuc.wordremember.ViewItem.ExploreItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExploreFragment extends Fragment {

    private TextView txt_toptime;
    private RecyclerView explore_recyclerView;

    public static List<ExploreItem>exploreItemList = new ArrayList<>();
    ToptimeMylitener toptimeMylitener;
    //一个判断按钮是否被选中的标志
    boolean flag = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_content, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        requsetdata();
        Log.i("fgsdsaf3265f",exploreItemList.toString());
        bindviews();
    }

    //UI组件初始化与事件绑定
    private  void bindviews(){

        //绑定事件
        txt_toptime = (TextView)getActivity().findViewById(R.id.txt_toptime);
        explore_recyclerView = (RecyclerView)getActivity().findViewById(R.id.explore_recyclerView);

        Spannable txtSpan = ChangeWord();
        txt_toptime.setText(txtSpan);

        //监听器
        toptimeMylitener = new ToptimeMylitener();
        txt_toptime.setOnClickListener(toptimeMylitener);

//        initItem()
        //recyclerview的适配器
        StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager( 1,StaggeredGridLayoutManager.VERTICAL );
        explore_recyclerView.setLayoutManager(staggeredGridLayoutManager);
        ExploreAdapter exploreAdapter =new ExploreAdapter(getContext(),exploreItemList);
        explore_recyclerView.setAdapter(exploreAdapter);


    }

    //动态改变字体大小
    private Spannable ChangeWord(){
        String text = "22 Jun.2019";
        Spannable textSpan = new SpannableStringBuilder(text);
        textSpan.setSpan(new AbsoluteSizeSpan(65), 0, text.indexOf("J") - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(35), text.indexOf("J") - 1, text.length()-0, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return textSpan;
    }

//    //初始化显示的item
//    private void initItem(){
//        ExploreItem item1 = new ExploreItem(R.drawable.explore_firstimag,"   I have been a selfish being all my life, in practice,\n" +
//                "   though not in principle.\n" +
//                "   在实际生活中我是个自私的人，但在原则上不是。\n" +
//                "    ——《Pride and Prejudice》\n" +
//                "  （《傲慢与偏见》）");
//        exploreItemList.add(item1);
//        ExploreItem item2 = new ExploreItem(R.drawable.explore_secondimag,"   For you, a thousand times over.“为你,千千万万遍”\n" +
//                "\n" +
//                "   ——《the kite runner》（《追风筝的人》）");
//        exploreItemList.add(item2);
//        ExploreItem item3 = new ExploreItem(R.drawable.explore_thirdimag,"  Land is the only thing in the world worth working for,\n" +
//                "  worth fighting for, worth dying for.\n" +
//                "  Because it’s the only thing that lasts.\n" +
//                "  (土地是世界上唯一值得你去为之工作, 为之战斗,\n" +
//                "  为之牺牲的东西,因为它是唯一永恒的东西)\n" +
//                "                ——《Gone with the Wind》《乱世佳人》");
//        exploreItemList.add(item3);
//        ExploreItem item4 = new ExploreItem(R.drawable.explore_fourthimag,"  Life was like a box of chocolates, you never know\n" +
//                "  what you're gonna get.\n" +
//                "  生命就像一盒巧克力，结果往往出人意料\n" +
//                "              —-《Forrest Gump 阿甘正传》");
//        exploreItemList.add(item4);
//        ExploreItem item5 = new ExploreItem(R.drawable.explore_fifthimag,"  The furthest distance in the world Is not between \n" +
//                "  life and death But when I stand in front of you Yet \n" +
//                "   you don't know that I love you\n" +
//                "  世界上最遥远的距离,不是生与死 而是我就站在你\n" +
//                "  的面前,你却不知道我爱你"+
//                "  --泰戈尔《Tagore`spoetry泰戈尔诗集》");
//        exploreItemList.add(item5);
//
//    }

    //向服务器请求数据
    private void requsetdata(){

        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case 3:
                        List<HashMap<String,String>> mapList = JSONTOOL.analyze_some_json(msg.obj.toString());
                        for(int i = 0;i<mapList.size();i++){
                        ExploreItem exploreItem = new ExploreItem("http://10.130.165.187:8070"+(String)mapList.get(i).get("imag"),mapList.get(i).get("senten"));
                        exploreItemList.add(exploreItem);
                        Log.i("orync","http://10.0.116.143:8070"+(String)mapList.get(i).get("imag"));
                        }
                        bindviews();
                        Log.i("fgtyghgf",exploreItemList.toString());
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

        params.put("random_sentencecount", 5);
        client.post("http://10.130.165.187:8070/fetchsent/", params, new MyTextListener(handler, 3, 30));
    }



    //监听器类
    private class ToptimeMylitener implements View.OnClickListener{

        Intent intent;
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.txt_toptime:
                    if (flag == false) {
                        flag = true;

                    } else {
                        flag = false;
                    }
                    txt_toptime.setSelected(flag);
                    intent = new Intent(getActivity(), ExploreContextChooseActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}