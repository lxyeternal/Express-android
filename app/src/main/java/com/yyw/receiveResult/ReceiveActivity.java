package com.yyw.receiveResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.sunofbeaches.mainlooper.CenterActivity;
import com.sunofbeaches.mainlooper.MainActivity;
import com.sunofbeaches.mainlooper.R;


public class ReceiveActivity extends Activity {
    private ImageButton imageButton2;
    private  ImageButton center2;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivelayout);
        imageButton2=(ImageButton)findViewById(R.id.imagebtn2);
        center2=(ImageButton)findViewById(R.id.center2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ReceiveActivity.this,MainActivity.class);
                startActivity(intent);
                ReceiveActivity.this.finish();
            }
        });
        center2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ReceiveActivity.this, CenterActivity.class);
                startActivity(intent);
                ReceiveActivity.this.finish();
            }
        });
    }
}
