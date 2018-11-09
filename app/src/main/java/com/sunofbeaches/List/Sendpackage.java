package com.sunofbeaches.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sunofbeaches.mainlooper.MainActivity;
import com.sunofbeaches.mainlooper.R;

import java.util.ArrayList;
import java.util.List;

public class Sendpackage extends AppCompatActivity implements View.OnClickListener{

    private PopupWindow popupWindow;
    private View zhezhao;   //底下半透明背景，实现矩形进入效果
    private View company;
    private TextView choose_company_edit;
    private SearchAdapter companyAdapter;
    private DropDownMenu dropDownMenu;
    private LinearLayout layout;
    private View listItem;
    private View listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendpackage);

        company = findViewById(R.id.choose_company);
        choose_company_edit = (TextView) findViewById(R.id.choose_company_edit);
        layout = (LinearLayout) getLayoutInflater().inflate(R.layout.pup_selectlist, null, false);


        company.setOnClickListener(this);

        dropDownMenu = DropDownMenu.getInstance(this, new DropDownMenu.OnListCkickListence() {
            @Override
            public void search(String code, String type) {
                System.out.println("======"+code+"========="+type);
            }

            @Override
            public void changeSelectPanel(Madapter madapter, View view) {

            }
        });
        dropDownMenu.setIndexColor(R.color.colorAccent);
        dropDownMenu.setShowShadow(true);
        dropDownMenu.setShowName("name");
        dropDownMenu.setSelectName("code");

        initData();

    }


    private void initData(){

        companyAdapter = new SearchAdapter(this);
        List<Dic> nationResult = new ArrayList<>();
        nationResult.add(new Dic("000","全部"));
        nationResult.add(new Dic("001","顺丰"));
        nationResult.add(new Dic("002","天天"));
        nationResult.add(new Dic("003","圆通"));
        nationResult.add(new Dic("004","申通"));
        nationResult.add(new Dic("005","韵达"));
        nationResult.add(new Dic("006","百世"));
        nationResult.add(new Dic("007","中通"));
        nationResult.add(new Dic("008","国通"));
        nationResult.add(new Dic("008","宅急送"));

        companyAdapter.setItems(nationResult);

        listItem = getLayoutInflater().inflate(R.layout.item_listview, null, false);
        listView = getLayoutInflater().inflate(R.layout.pup_selectlist, null, false);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_company:
                dropDownMenu.showSelectList(ScreenUtils.getScreenWidth(this),
                        ScreenUtils.getScreenHeight(this), companyAdapter,
                        listView, listItem,company,choose_company_edit,"cyry.whcd",true);
                break;
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
