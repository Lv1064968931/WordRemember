package com.example.nuc.wordremember.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nuc.wordremember.Application.ChangewordApp;
import com.example.nuc.wordremember.Fragment.ExploreFragment;
import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ViewItem.ExploreItem;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.MyviewHolder> {

    private List<ExploreItem> exploreItems = null;
    private Context context;

    public static int loveessayCount = 0;


    public ExploreAdapter(Context context,List<ExploreItem> exploreItems) {
        this.context = context;
        this.exploreItems = exploreItems;
    }

    static class MyviewHolder extends RecyclerView.ViewHolder {

        View exploreview;
        private ImageView explore_imag_item;
        private TextView explore_TV_item;
        private  TextView explore_TV_love;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            exploreview = itemView;
            explore_imag_item = (ImageView)itemView.findViewById(R.id.explore_imag_item);
            explore_TV_item = (TextView)itemView.findViewById(R.id.explore_TV_item);
            explore_TV_love =(TextView)itemView.findViewById(R.id.explore_TV_love);
        }
    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.explore_item,viewGroup,false);
        final MyviewHolder myviewHolder = new MyviewHolder(view);

        myviewHolder.exploreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myviewHolder.getAdapterPosition();
                ExploreItem exploreItem= exploreItems.get(position);
                Toast.makeText(v.getContext(),exploreItem.getText(),Toast.LENGTH_LONG).show();
            }
        });

        myviewHolder.explore_imag_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myviewHolder.getAdapterPosition();
                ExploreItem exploreItem= exploreItems.get(position);
                Toast.makeText(v.getContext(),"你点击的图片是："+exploreItem.getImag_id(),Toast.LENGTH_LONG).show();
            }
        });

        myviewHolder.explore_TV_love.setOnClickListener(new View.OnClickListener() {

            private boolean flag = false;
            @Override
            public void onClick(View v) {
                int position = myviewHolder.getAdapterPosition();
                ExploreItem exploreItem= exploreItems.get(position);

                if(flag == false){
                    flag = true;
                    ChangewordApp loveessayapp = new ChangewordApp();
                    ExploreItem exploreItem1 = ExploreFragment.exploreItemList.get(position);
                    loveessayCount += 1;
                    for(int i =loveessayCount - 1;i<loveessayCount;i++){
                        loveessayapp.loveessayList.add(exploreItem1);
                    }
                    Log.i("o52871ybct", String.valueOf(loveessayapp.loveessayList));
                    Toast.makeText(v.getContext(),"收藏成功",Toast.LENGTH_LONG).show();
                }else {
                    flag = false;
                    ChangewordApp loveessayapp = new ChangewordApp();
                    ExploreItem exploreItem1 = ExploreFragment.exploreItemList.get(position);
                    for(int i =loveessayCount - 1;i<loveessayCount;i++){
                        loveessayapp.loveessayList.remove(exploreItem1);
                    }
                    loveessayCount -= 1;
                    Log.i("okuybct", String.valueOf(loveessayapp.loveessayList));
                    Toast.makeText(v.getContext(),"取消收藏成功",Toast.LENGTH_LONG).show();
                }
                myviewHolder.explore_TV_love.setSelected(flag);

            }
        });


        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
        ExploreItem exploreItem = exploreItems.get(i);
//        myviewHolder.explore_imag_item.setImageResource(exploreItem.getImag_id());
        myviewHolder.explore_TV_item.setText(exploreItem.getText());
        Glide.with(context).load(exploreItems.get(i).getImag_id()).into(myviewHolder.explore_imag_item);
    }

    @Override
    public int getItemCount() {
        return exploreItems.size();
    }
}
