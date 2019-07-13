package com.example.nuc.wordremember.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ViewItem.AccountnumItem;

import java.util.List;

public class AccountnummanagerAdapter extends BaseAdapter {

    private List<AccountnumItem> accountnumItems = null;
    private LayoutInflater inflater;

    public AccountnummanagerAdapter() {}

    public AccountnummanagerAdapter(List<AccountnumItem> accountnumItems, Context context) {
        this.accountnumItems = accountnumItems;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return accountnumItems ==null?0: accountnumItems.size();
    }

    @Override
    public Object getItem(int position) {
        return accountnumItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.accountnumber_item,null);
        AccountnumItem accountnumItem = (AccountnumItem)getItem(position);

        TextView draweraccountNumber_TV = (TextView)view.findViewById(R.id.draweraccountNumber_TV);
        ImageView draweraccountImag_IV = (ImageView)view.findViewById(R.id.draweraccountImag_IV);
        draweraccountNumber_TV.setText(accountnumItem.getAccountNumber());
        draweraccountImag_IV.setImageResource(accountnumItem.getAccountImag_id());
        return view;
    }
}
