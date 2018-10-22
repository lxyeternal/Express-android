package com.sunofbeaches.mainlooper;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Prohibit_page extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prohabit_back);
        initView();
    }

    private void initView() {

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.prohabit_back:
                startActivity(new Intent(this,MainActivity.class));
        }
    }
}
