package com.imooc.searchResult;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sunofbeaches.mainlooper.R;

public class ScanActivity extends Activity {
    private TextView result;
    private ImageButton back1;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanlayout);
        back1=(ImageButton)findViewById(R.id.back1);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScanActivity.this.finish();
            }
        });
        Intent intent=getIntent();
        String value=intent.getStringExtra("Result");
        result=(TextView)findViewById(R.id.result);
        result.setText(value);
        try{
        Uri uri = Uri.parse(value);
        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
        if(value.substring(0,4).equals("http")) {
            Toast.makeText(ScanActivity.this, "正在为您打开网页，请稍后...", Toast.LENGTH_SHORT).show();
        }
        startActivity(intent2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
