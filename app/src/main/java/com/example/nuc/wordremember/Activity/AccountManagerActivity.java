package com.example.nuc.wordremember.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.nuc.wordremember.Adapter.AccountnummanagerAdapter;
import com.example.nuc.wordremember.R;
import com.example.nuc.wordremember.ViewItem.AccountnumItem;

import java.util.ArrayList;
import java.util.List;

public class AccountManagerActivity extends AppCompatActivity {

    private AccountnummanagerAdapter accountnummanagerAdapter;
    private ListView accountmanager_Listview;
    private List<AccountnumItem>accountnumItemList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountmanager);

        accountnumItemList = new ArrayList<>();
        initaccountnum();
        bindviews();
    }

    //UI组件初始化与事件绑定
    private void bindviews(){

        accountmanager_Listview = (ListView)findViewById(R.id.accountmanager_Listview);

        accountnummanagerAdapter = new AccountnummanagerAdapter(accountnumItemList,AccountManagerActivity.this);
        accountmanager_Listview.setAdapter(accountnummanagerAdapter);

    }

    //初始化账户信息
    private void initaccountnum(){


            AccountnumItem accountnumItem = new AccountnumItem(R.drawable.myinfo_personimag,LoginActivity.USER_PHONE_NUM);
            accountnumItemList.add(accountnumItem);
    }
}
