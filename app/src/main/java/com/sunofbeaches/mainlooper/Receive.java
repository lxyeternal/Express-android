package com.sunofbeaches.mainlooper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sunofbeaches.List.Sendpackage;

import cn.bmob.v3.Bmob;

public class Receive extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivelayout);
        Bmob.initialize(this, "27a2563509e7fbb280ee0bab60c998ce");
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendpage:
                startActivity(new Intent(this, Sendpackage.class));
                break;

        }
    }
}
