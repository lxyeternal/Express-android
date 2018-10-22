package com.sunofbeaches.mainlooper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Sendpackage extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendpackage);
        initView();
    }

    private void initView() {

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.getback:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.id_leavemessage:
                AlertDialog.Builder dialog = new AlertDialog.Builder(Sendpackage.this);
                dialog.setTitle("标题");
                dialog.setMessage("具体信息");
                dialog.setCancelable(false);
                dialog.setPositiveButton("继续", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();

        }

    }
}
