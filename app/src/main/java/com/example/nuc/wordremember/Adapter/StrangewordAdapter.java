package com.example.nuc.wordremember.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ViewItem.StrangeWord;

import java.util.List;

//生词本Recyclerview的适配器
public class StrangewordAdapter extends RecyclerView.Adapter<StrangewordAdapter.MyviewHolder> {


    private List<StrangeWord>strangeWords = null;

    public StrangewordAdapter(Context context,List<StrangeWord>strangeWords) {
        this.strangeWords = strangeWords;
    }

    static class MyviewHolder extends RecyclerView.ViewHolder {

        View Strangewordsview;
        private TextView wordTV;
        private TextView word_alphaptTV;
        private TextView wordexpTV;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            Strangewordsview = itemView;
            wordTV = (TextView)itemView.findViewById(R.id.wordTV);
            word_alphaptTV = (TextView)itemView.findViewById(R.id.word_alphaptTV);
            wordexpTV = (TextView)itemView.findViewById(R.id.wordexpTV);
        }
    }
    @NonNull
    @Override
    public StrangewordAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.word_item,viewGroup,false);
        final MyviewHolder myviewHolder = new MyviewHolder(view);

        myviewHolder.Strangewordsview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myviewHolder.getAdapterPosition();
                StrangeWord strangeWord= strangeWords.get(position);
                Toast.makeText(v.getContext(),strangeWord.getStrangeWord(),Toast.LENGTH_LONG).show();
            }
        });

        myviewHolder.wordexpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myviewHolder.getAdapterPosition();
                StrangeWord strangeWord= strangeWords.get(position);
                Toast.makeText(v.getContext(),"释义："+strangeWord.getStrangewordExplanation(),Toast.LENGTH_LONG).show();
            }
        });
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StrangewordAdapter.MyviewHolder myviewHolder, int i) {

        StrangeWord strangeWord= strangeWords.get(i);
        myviewHolder.wordTV.setText(strangeWord.getStrangeWord());
        myviewHolder.word_alphaptTV.setText(strangeWord.getStrangewordalphapt());
        myviewHolder.wordexpTV.setText(strangeWord.getStrangewordExplanation());
    }

    @Override
    public int getItemCount() {
        return strangeWords.size();
    }
}
