package com.example.nuc.wordremember.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nuc.wordremember.R;

public class SetPassword extends AppCompatActivity {

    private Button nextsteploginBtn;
    Mylistrener mylistrener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        bindviews();
    }

    private  void bindviews(){

        nextsteploginBtn = (Button)findViewById(R.id.nextsteploginBtn);

        mylistrener = new Mylistrener();
        nextsteploginBtn.setOnClickListener(mylistrener);
    }

    private class Mylistrener implements View.OnClickListener{

        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.nextsteploginBtn:
                    intent = new Intent(SetPassword.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;

            }
        }
    }
}
