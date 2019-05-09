package com.sunofbeaches.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.Tools.AddAddressActivity;
import com.Tools.callback.SelectAddressInterface;
import com.Tools.view.AddressDialog;
import com.Tools.view.AddressSelectorDialog;
import com.Tools.view.SettingItemView;
import com.sunofbeaches.Encode.encrypt.RSAUtils;
import com.sunofbeaches.entity.Address;
import com.sunofbeaches.entity.MyUser;
import com.sunofbeaches.mainlooper.MainActivity;
import com.sunofbeaches.mainlooper.QRcode;
import com.sunofbeaches.mainlooper.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Sendpackage extends AppCompatActivity implements View.OnClickListener,SelectAddressInterface {

    private PopupWindow popupWindow;
    private View zhezhao;   //底下半透明背景，实现矩形进入效果
    private View company;
    private View object;
    private TextView choose_company_edit;
    private TextView object_jijian_text;
    private SearchAdapter companyAdapter;
    private SearchAdapter objectAdapter;
    private DropDownMenu dropDownMenu;
    private LinearLayout layout;
    private View listItem;
    private View listView;
    TextView mobject;
    TextView mcompany;
    EditText mConsignee;
    SettingItemView mSivAddress;
    EditText mDetailsAddress;
    EditText mPostcode;
    EditText mPhoneNum;

    public String CurrentProvinceName;
    public String CurrentCityName;
    public String CurrentDistrictName;
    private boolean active = true;
//    public String expnumber;

    private AddressSelectorDialog addressSelectorDialog;

    Random random = new Random();
    int num = random.nextInt(1000000000)+1000000000;
    String expnumber = String.format("HW"+num);


    private static String content = null;
    /* 密钥内容 base64 code */
    private static String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+iRXVDOsZikPvaIwzTDhcKx3r\n" +
            "SZNbB/H/MrdUj/GkiSgDL7bTXjyb0cAwefD+/JxXBy6EMuPzBMt7flTWNXGBUNvw\n" +
            "HpaUPicdVAH4h8V0PvUiQKG/pS6oynMvARjZIHWBg8VEqaTcBdpuq+1jhtDxhuBM\n" +
            "SFpt7b8gpWo//BG0ZQIDAQAB";

    private static String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL6JFdUM6xmKQ+9o\n" +
            "jDNMOFwrHetJk1sH8f8yt1SP8aSJKAMvttNePJvRwDB58P78nFcHLoQy4/MEy3t+\n" +
            "VNY1cYFQ2/AelpQ+Jx1UAfiHxXQ+9SJAob+lLqjKcy8BGNkgdYGDxUSppNwF2m6r\n" +
            "7WOG0PGG4ExIWm3tvyClaj/8EbRlAgMBAAECgYAX3y8IEWVHPuaCEVQ3fR42lgRa\n" +
            "nU5EAnvUYHNNufcpiTGlLI44bz8iuqXcrPp/yACCetjeIU4j/X7NCyfv6qQ8ux/0\n" +
            "WdGY4WZtc9EV38vgxzlfOHWrtJ1qVBX6vbs8TZabaz9XSaE+u+akhGACf5dHm4HN\n" +
            "uhwDIvtu0AwBzwMIBQJBAN5BI8q0S5EI3nu4Bi3ZzssFRwh9TD8Fa91TPntFGi0J\n" +
            "q3iGTq2qb2j3TKOn0lZBVg82yicNlxklOemwWEqxDlcCQQDbdw2+2y9MNVJSxOvO\n" +
            "wEKdzcvimB1m7896GcWRpOp6/BBZyj8A20QztpEmJ5v9V8sFIjiVqdWlWWar7Lqr\n" +
            "9SWjAkBRcQ87hSu3nsdgEIP7IzgavvlTjA53fXYUKR/ZLe40mLmDtbt4+d5PWWd1\n" +
            "BNcXkmOFua8D9n/qz/BTyLHh1NWLAkEAl6MA6lhDq+JDyVCqpaYN4T7qmtwDpLYZ\n" +
            "owHfkqxiHyu+mGu3cH4P97MzQyunCjr42ck1U6OPLLpCyJO+v0WZBQJASReT45oU\n" +
            "Xvp/eK/JEdMu68GFzDp9gbsKpRRNv2/fL+ZCRzEWzkElfDWmJy5g/FhkEatgfAuZ\n" +
            "Cxl0w8M0aLiBQw==";

    private static String PUCLIC_KEY1 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDwR6ryDNjir9aYGPTiji5Lp1on\n" +
            "Ged6YRzgO6Cr6eRrK+oDSG2s9LGLvRLmMTzcq+Z4k9IdEAonvSKwR8G+CsMfCT+6\n" +
            "90NKE5g2y4JMXQKeVGGAqdHeKLncB3PNGcEw7xCS2Z2jHzwnsDEn/CVHAnFBq/xT\n" +
            "6gqF3m4iaNMnuvjNswIDAQAB";

    private static String PRIVATE_KEY1 = "MIICXAIBAAKBgQDwR6ryDNjir9aYGPTiji5Lp1onGed6YRzgO6Cr6eRrK+oDSG2s\n" +
            "9LGLvRLmMTzcq+Z4k9IdEAonvSKwR8G+CsMfCT+690NKE5g2y4JMXQKeVGGAqdHe\n" +
            "KLncB3PNGcEw7xCS2Z2jHzwnsDEn/CVHAnFBq/xT6gqF3m4iaNMnuvjNswIDAQAB\n" +
            "AoGBAMuBt8xoiy18BoIt6QMVrypOZLHnY8GoDL/yYMQaRmdq+zmql5G8cb/L9PzY\n" +
            "SRcR1RLdCEGrOx6rN30cWYCvNtAnX/jN2jOq8YNVw6GeoE/LhRlsFEwfrkUdi1eA\n" +
            "2UqSVmStSuQXnMbhERCusm0qamsb3GYqgqSU4TA85GxkLgkJAkEA/LDETEcYKFFr\n" +
            "FlB/rYDlMdFteP4HubWlsCAn7ZIhdkLPVwziGdUrHxZTXaH371t5R9VekS+tvuUi\n" +
            "AyeyBJnClwJBAPNtSlAA6lI65mfSwMfeJ9MUXLN5See5rstdAxPIU1SV7QBVOYf5\n" +
            "XsRThwN+KsdSEHBnbNvrobCIqz/cpVQt3UUCQDvPOycyzKz+WHJ3q0DN+XHJODgj\n" +
            "KID0VbtSYSECLZloRts72Whz5Dj/d8v5wk2NQS+XeUK0HlPIuDw28RPVsRECQDwr\n" +
            "LG4f+8s9gcvx3PLKJ5/V7Z/y/+CGFf1wTR1rq6VBtaYaarEAY3s1hi7H1b0UvLl7\n" +
            "5v1o/btL3MBZQohPGd0CQEAwXTaHSDx30C/ll35o4B8JtdlxyCKzXNGvTSwtA+iz\n" +
            "pSNYqKuoPFaDhdwiPKN2WcE+4xsd7wVwhRVyNJPLEH0=";

    private static String PUCLIC_KEY2 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwo+3KcKLQnz7BOMFFYJMygUDJ\n" +
            "jTarS27FWexkY/KUiWCi1V6gtuYIu/PTU2qlFau6WnGF+mua9K7YzAjKjF4cezoa\n" +
            "rphjjpXD4MCUuxZt4jRfkd6V21Bq8oW/lRo3l7kf32db/C7oBvhqvQty6Y/awLj+\n" +
            "8PbtupBOtkcwuALujwIDAQAB";

    private static String PRIVATE_KEY2 = "MIICXAIBAAKBgQCwo+3KcKLQnz7BOMFFYJMygUDJjTarS27FWexkY/KUiWCi1V6g\n" +
            "tuYIu/PTU2qlFau6WnGF+mua9K7YzAjKjF4cezoarphjjpXD4MCUuxZt4jRfkd6V\n" +
            "21Bq8oW/lRo3l7kf32db/C7oBvhqvQty6Y/awLj+8PbtupBOtkcwuALujwIDAQAB\n" +
            "AoGBAI9pCC5LtGRcZb7KLV/+QIPInKkGlVQNMinYECyRWOlvf520Tdd9ptWAgz0X\n" +
            "hIXzSjFKLNDxt1tfPHWXcavAusZar5/T1N1z58Ebe3saCuNr+WljLdoQ5LQGCbim\n" +
            "XbEaFxRftOlk5Hr1FyuXpzXrmXaIJ+1Y+oh2ndrBAqQDq1LBAkEA5LM9+BGElchL\n" +
            "2eNXbqFQEi9G7UNssdkQ1aHUfSWE/AUdGJ4WILwKzbylYAZUP61e0MVaEesiRjdH\n" +
            "ZIo2i/80wwJBAMW5z7mfOrDgnOxwDIEy9qxawA3rbTgZOBzS/SUqgojhZgK/h40/\n" +
            "DoW0KnHoJey+3sXjmwNrjVNK5WBM1JMsEkUCQE+ffAa/CpsfVhExDoKs1PTP0vDO\n" +
            "FazBtxO5uQItKuSXXy7v8yJIdbZ1ZFpPOcvFr4od+6Lba15o5olBeYY6PwMCQF0z\n" +
            "lQL+V4AAoHei6YINL9FWhakKDFWhLZ4IpmLaguVauecZuR+PVZ0Yq9k9gIL0BbZp\n" +
            "7yvRirB0YWLfVGR7JbECQHZde8IZegdkYgX2tcJNCF/RHEAk4264AdsgXz+i8CeO\n" +
            "bFr/eOtYNgshirj1e5oaQ2jODy7sNQL2wu/jhiUaFvk=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendpackage);
        Bmob.initialize(this, "27a2563509e7fbb280ee0bab60c998ce");


        mcompany = (TextView) findViewById(R.id.choose_company_edit);
        mConsignee = (EditText) findViewById(R.id.consignee);
        mobject = (TextView) findViewById(R.id.object_jijian_text) ;
        mSivAddress = (SettingItemView) findViewById(R.id.siv_address);
        mDetailsAddress = (EditText) findViewById(R.id.details_address);
        mPostcode = (EditText) findViewById(R.id.postcode);
        mPhoneNum = (EditText) findViewById(R.id.phoneNum);
        company = findViewById(R.id.choose_company);
        object =  findViewById(R.id.object_jijian);
        object_jijian_text = (TextView) findViewById(R.id.object_jijian_text);
        choose_company_edit = (TextView) findViewById(R.id.choose_company_edit);
        layout = (LinearLayout) getLayoutInflater().inflate(R.layout.pup_selectlist, null, false);


        addAddressAreaData();
        company.setOnClickListener(this);
        object.setOnClickListener(this);

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


    private void addAddressAreaData() {
        mSivAddress.setItemTip("请选择  ");
        mSivAddress.setItemText("所在地区");
        mSivAddress.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCitySelectorDialog();  //三级联动
            }
        });
    }

    private void setCitySelectorDialog() {
        if (addressSelectorDialog == null) {
            addressSelectorDialog = new AddressSelectorDialog(this);
        }
        addressSelectorDialog.show();
        addressSelectorDialog.setOnAddressChangedListener(new AddressDialog.OnAddressChangedListener() {
            @Override
            public void onCanceled() {
                addressSelectorDialog.dismiss();
            }

            @Override
            public void onConfirmed(String currentProvinceName, String currentCityName, String currentDistrictName, String currentZipCode) {
                mSivAddress.setItemTip(currentProvinceName + currentCityName + currentDistrictName);
                CurrentProvinceName = currentProvinceName;
                CurrentCityName = currentCityName;
                CurrentDistrictName = currentDistrictName;
                mPostcode.setText(currentZipCode);
                addressSelectorDialog.dismiss();
            }
        });
    }

    public static void startAction(Context context) {
        Intent i = new Intent(context, AddAddressActivity.class);
        context.startActivity(i);
    }

    @Override
    public void setAreaString(String area) {

    }

    @Override
    public void setAreaString(String currentProvinceName, String currentCityName, String currentDistrictName, String currentZipCode) {
        mSivAddress.setItemTip(currentProvinceName + currentCityName + currentDistrictName);
        mPostcode.setText(currentZipCode);
    }

    @Override
    public void setAreaString(String currentProvinceName, String currentCityName) {

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


        objectAdapter = new SearchAdapter(this);
        List<Dic> objectResult = new ArrayList<>();
        objectResult.add(new Dic("000","全部"));
        objectResult.add(new Dic("001","文件"));
        objectResult.add(new Dic("002","食品"));
        objectResult.add(new Dic("003","电子"));  //objectAdapter
        objectResult.add(new Dic("004","液体"));
        objectResult.add(new Dic("005","书籍"));
        objectResult.add(new Dic("006","衣服"));
        objectResult.add(new Dic("007","金属"));
        objectResult.add(new Dic("008","药品"));

        objectAdapter.setItems(objectResult);




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
            case R.id.object_jijian:
                dropDownMenu.showSelectList(ScreenUtils.getScreenWidth(this),
                        ScreenUtils.getScreenHeight(this), objectAdapter,
                        listView, listItem,object,object_jijian_text,"cyry.whcd",true);

                break;
            case R.id.getback:
                startActivity(new Intent(this, MainActivity.class));
                break;
//
            case R.id.ack_information:

                //获取输入框中的信息
                String spread;
                String expresscompany = mcompany.getText().toString().trim();
                String recv_name = mConsignee.getText().toString().trim();
                String phonenuber = mPhoneNum.getText().toString().trim();
                String currentZipCode = mPostcode.getText().toString().trim();
                String project = object_jijian_text.getText().toString().trim();
                String detailsaddress = mDetailsAddress.getText().toString().trim();

                //Integer.parseInt
                //上传地址信息

                Address address = new Address();

                MyUser user = BmobUser.getCurrentUser(MyUser.class);

                final BmobRelation relation = new BmobRelation();
                relation.add(user);


                address.setSendname(user.getUsername());
                address.setCurrentCityName(CurrentCityName);
                address.setCurrentProvinceName(CurrentProvinceName);
                address.setCurrentDistrictName(CurrentDistrictName);
                address.setCurrentZipCode(Integer.parseInt(currentZipCode));
                address.setExpresscompany(expresscompany);
                address.setPhonenuber(Long.parseLong(phonenuber));
                address.setProject(project);
                address.setRecv_name(recv_name);
                address.setDetailsaddress(detailsaddress);
                address.setActive(active);
                address.setExpressnumber(expnumber);

                spread =
                        "快递单号：" + expnumber + '\n' +
                                "收件人："+recv_name+'\n'+
                        "省份：" + CurrentProvinceName + '\n' +
                        "城市：" + CurrentCityName + '\n' +
                        "县级：" + CurrentDistrictName + '\n' +
                        "具体地址：" + detailsaddress + '\n' +
                        "电话号码：" + phonenuber + '\n';


//                 String  encryspread = RSAUtils.encrypt(PUCLIC_KEY,spread);
                 String  encry1 = RSAUtils.encrypt(PUCLIC_KEY,"省份："+CurrentProvinceName);
                 String  encry2 = RSAUtils.encrypt(PUCLIC_KEY1,"县级："+CurrentDistrictName);
                 String  encry3 = RSAUtils.encrypt(PUCLIC_KEY2,"收件人："+ recv_name + '\n' + "具体地址：" + detailsaddress + '\n' + "联系电话：" + phonenuber);
                 String  encryspread = encry1 + "###" + encry2 + "###" + encry3;
//                address.save(new SaveListener<String>() {
//                    @Override
//                    public void done(String s, BmobException e) {
//                        if (e == null) {
//                            Toast.makeText(Sendpackage.this, "添加数据成功，：", Toast.LENGTH_SHORT).show();
//                            System.out.println("添加数据成功");
//                        } else {
//                            Toast.makeText(Sendpackage.this, "添加数据失败", Toast.LENGTH_SHORT).show();
//                            System.out.println("添加数据失败");
//                        }
//                    }
//                });

                address.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if (e == null) {
                            toast("添加数据成功，返回objectId为：" + objectId);
                        } else {
                            toast("创建数据失败：" + e.getMessage());
                        }
                    }

                    private void toast(String s) {
                        Toast.makeText(Sendpackage.this,s,Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent();
                intent.putExtra("string",encryspread);
                intent.setClass(Sendpackage.this, QRcode.class);
                Sendpackage.this.startActivity(intent);
                finish();

        }
    }

}
