package com.example.nuc.wordremember.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ViewItem.LoveEssay;

import java.util.List;

public class LoveessayAdapter extends BaseAdapter {


    private List<LoveEssay> loveEssays = null;
    private LayoutInflater inflater;
    public LoveessayAdapter() {}

    public LoveessayAdapter(List<LoveEssay> loveEssays,Context context) {
        this.loveEssays = loveEssays;
        this.inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return loveEssays==null?0:loveEssays.size();
    }

    @Override
    public Object getItem(int position) {
        return loveEssays.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.loveessay_item,null);
        LoveEssay loveEssay = (LoveEssay) getItem(position);
        TextView essaytitleTV = (TextView)view.findViewById(R.id.essaytitleTV);
        essaytitleTV.setText(loveEssay.getEssayTitle());
        return view;
    }
}
