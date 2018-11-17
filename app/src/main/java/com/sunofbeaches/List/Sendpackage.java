package com.sunofbeaches.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.Tools.AddAddressActivity;
import com.Tools.callback.SelectAddressInterface;
import com.Tools.view.AddressDialog;
import com.Tools.view.AddressSelectorDialog;
import com.Tools.view.SettingItemView;
import com.sunofbeaches.entity.Address;
import com.sunofbeaches.mainlooper.ForgetPasswordActivity;
import com.sunofbeaches.mainlooper.MainActivity;
import com.sunofbeaches.mainlooper.R;


import java.util.ArrayList;
import java.util.List;

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

    private AddressSelectorDialog addressSelectorDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendpackage);


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
                mPostcode.setText(currentZipCode);
                addressSelectorDialog.dismiss();
                CurrentProvinceName = currentProvinceName;
                CurrentCityName = currentCityName;
                CurrentDistrictName = currentDistrictName;
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
//            case R.id.id_leavemessage:
//                AlertDialog.Builder dialog = new AlertDialog.Builder(Sendpackage.this);
//                dialog.setTitle("标题");
//                dialog.setMessage("具体信息");
//                dialog.setCancelable(false);
//                dialog.setPositiveButton("继续", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                });
//                dialog.setNegativeButton("退出", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                });
//                dialog.show();
//                break;
            case R.id.ack_information:

                //获取输入框中的信息

                String expresscompany = mcompany.getText().toString().trim();
                String recv_name = mConsignee.getText().toString().trim();
                String phonenuber = mPhoneNum.getText().toString().trim();
                String currentZipCode = mPostcode.getText().toString().trim();
                String project = object_jijian_text.getText().toString().trim();
                String detailsaddress = mDetailsAddress.getText().toString().trim();

                //Integer.parseInt

                //上传地址信息

                Address address = new Address();

                address.setCurrentCityName(CurrentCityName);
                address.setCurrentProvinceName(CurrentProvinceName);
                address.setCurrentDistrictName(CurrentDistrictName);
                address.setCurrentZipCode(Integer.parseInt(currentZipCode));
                address.setExpresscompany(expresscompany);
                address.setPhonenuber(Integer.parseInt(phonenuber));
                address.setProject(project);
                address.setRecv_name(recv_name);
                address.setDetailsaddress(detailsaddress);

                address.save(new SaveListener<String>() {

                    @Override
                    public void done(String objectId, BmobException e) {
                        if(e==null){
                            toast("创建数据成功：" + objectId);
                        }else{
                            Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                        }
                    }

                    private void toast(String s) {
                    }
                });

                break;

        }
    }

}
