package com.example.nuc.wordremember.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ViewItem.ExploreChooseItem;
import com.example.nuc.wordremember.ViewItem.ExploreItem;

import java.util.List;

public class ExploreChooseAdapter extends RecyclerView.Adapter<ExploreChooseAdapter.MyviewHolder>{

    private List<ExploreChooseItem>exploreChooseItems = null;

    public ExploreChooseAdapter(List<ExploreChooseItem>exploreChooseItems){

        this.exploreChooseItems = exploreChooseItems;
    }

    static class MyviewHolder extends RecyclerView.ViewHolder {

        View explorechooseview;
        private ImageView explore_choose_imag_item;
        private TextView explore_choose_TV_item;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            explorechooseview = itemView;
            explore_choose_imag_item = (ImageView)itemView.findViewById(R.id.explore_choose_imag_item);
            explore_choose_TV_item = (TextView)itemView.findViewById(R.id.explore_choose_TV_item);
        }
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.explore_choose_context_item,viewGroup,false);
        final MyviewHolder myviewHolder = new MyviewHolder(view);

        myviewHolder.explorechooseview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myviewHolder.getAdapterPosition();
                ExploreChooseItem exploreChooseItem= exploreChooseItems.get(position);
                Toast.makeText(v.getContext(),exploreChooseItem.getText(),Toast.LENGTH_LONG).show();
            }
        });


        myviewHolder.explore_choose_imag_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myviewHolder.getAdapterPosition();
                ExploreChooseItem exploreChooseItem= exploreChooseItems.get(position);
                Toast.makeText(v.getContext(),"你点击的图片是："+exploreChooseItem.getImag_id(),Toast.LENGTH_LONG).show();
            }
        });


        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {

        ExploreChooseItem exploreChooseItem = exploreChooseItems.get(i);
        myviewHolder.explore_choose_imag_item.setImageResource(exploreChooseItem.getImag_id());
        myviewHolder.explore_choose_TV_item.setText(exploreChooseItem.getText());

    }

    @Override
    public int getItemCount() {
        return exploreChooseItems.size();
    }



}
