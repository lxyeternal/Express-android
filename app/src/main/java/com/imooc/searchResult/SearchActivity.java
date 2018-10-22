package com.imooc.searchResult;

import android.app.ListActivity;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.sunofbeaches.mainlooper.MainActivity;
import com.sunofbeaches.mainlooper.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends ListActivity {

    private Button btn;
    private EditText postId;
    private ListView lv;
    private Spinner spinner;
    private ImageButton back;

    String[] from = {"time","context"};
    int[] to = {R.id.time,R.id.context};

    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    HashMap<String,String> map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlayout);

        postId = (EditText)findViewById(R.id.postid);
        btn = (Button)findViewById(R.id.btn);
        lv = (ListView)findViewById(android.R.id.list);
        spinner = (Spinner)findViewById(R.id.spinner);
        back=(ImageButton)findViewById(R.id.back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchActivity.this.finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchActivity.this,"结果正在查询中，请稍后....",Toast.LENGTH_SHORT).show();
                sendRequest();
            }
        });
    }

    //发送Http请求
    private void sendRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    //处理快递单号
                    String queryString = checkPostid();
                    HttpGet httpGet = new HttpGet(queryString);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if(httpResponse.getStatusLine().getStatusCode() == 200){
                        //请求和响应都成功了
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity,"utf-8");
                        parseJSON(response);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //解析JSON数据
    private void parseJSON(String jsonData){
        try{
            //我们需要解析数组data的数据，其中time和context就是需要的字段
            JSONArray jsonArray = new JSONObject(jsonData).getJSONArray("data");
            for (int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String time = jsonObject.getString("time");
                String context = jsonObject.getString("context");
//                Log.d("data","time is " + time);
//                Log.d("context","context is " + context);

                map = new HashMap<String,String>();
                map.put("time",time);
                map.put("context",context);
                list.add(map);
            }

            //在子线程向ListView里添加数据
            new Thread(){
                public void run(){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SimpleAdapter simpleAdapter = new SimpleAdapter(SearchActivity.this,list,
                                    R.layout.item_list,from,to);
                            setListAdapter(simpleAdapter);
                            //假如没有数据，提醒用户
                            if(lv.getCount() == 0){
                                Toast.makeText(SearchActivity.this,"还没有数据",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }.start();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //识别快递单号是哪家的快递，给服务器传递响应的请求
    private String checkPostid(){
        //基础链接
        String baseStr = "http://www.kuaidi100.com/query?type=";
        String type= "";
        String postidNumber = postId.getText().toString().trim();
        String kuaidiCompany = spinner.getSelectedItem().toString().trim();
        Log.d("post id",postidNumber);
        Log.d("kuaidiCompany id",kuaidiCompany);

        if(kuaidiCompany.equals("申通")){
            type = "shentong";
        }else if(kuaidiCompany.equals("EMS")){
            type = "ems";
        }else if(kuaidiCompany.equals("顺丰")){
            type = "shunfeng";
        }else if(kuaidiCompany.equals("圆通")){
            type = "yuantong";
        }else if(kuaidiCompany.equals("中通")){
            type = "zhongtong";
        }else if (kuaidiCompany.equals("韵达")){
            type = "yunda";
        }else if (kuaidiCompany.equals("天天")){
            type = "tiantian";
        }else if (kuaidiCompany.equals("汇通")){
            type = "huitongkuaidi";
        }else if (kuaidiCompany.equals("全峰")){
            type = "quanfengkuaidi";
        }else if (kuaidiCompany.equals("德邦")){
            type = "debangwuliu";
        }else if(kuaidiCompany.equals("宅急送")){
            type = "zhaijisong";
        }
        Log.d("url",baseStr + type + "&postid=" + postidNumber);
        return baseStr + type + "&postid=" + postidNumber;
    }

}

