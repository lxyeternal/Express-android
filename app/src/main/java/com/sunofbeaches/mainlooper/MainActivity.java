package com.sunofbeaches.mainlooper;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.imooc.searchResult.ScanActivity;
import com.imooc.searchResult.SearchActivity;
import com.yyw.receiveResult.ReceiveActivity;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements MyViewPager.OnViewPagerTouchListener, ViewPager.OnPageChangeListener,View.OnClickListener{

    private static final String TAG = "MainActivity";
    private MyViewPager mLoopPager;
    private LooperPagerAdapter mLooperPagerAdapter;

    private static List<Integer> sPics = new ArrayList<>();
    static {
        sPics.add(R.mipmap.pic1);
        sPics.add(R.mipmap.pic2);
        sPics.add(R.mipmap.pic3);
    }

    private Handler mHandler;
    private boolean mIsTouch = false;
    private LinearLayout mPointContainer;
    private Button query1;
    private EditText edit;

    private ImageButton imageButton3,imageButton4,imageButton5,imageButton6,imageButton7;
    private ImageButton scanBtn;
    private int REQUEST_CODE_SCAN = 111;

    private ImageButton center;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //点击跳转到查询结果页面
        query1 = (Button)findViewById(R.id.query1);
        query1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"页面正在跳转，请稍后",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        edit=(EditText)findViewById(R.id.edit);
       imageButton3=(ImageButton)findViewById(R.id.imagebtn3);
       imageButton4=(ImageButton)findViewById(R.id.imagebtn4);
       imageButton3.setOnClickListener(new ImageButtonOnClick());
       imageButton4.setOnClickListener(new ImageButtonOnClick());
        imageButton5=(ImageButton)findViewById(R.id.imagebtn5);
        imageButton6=(ImageButton)findViewById(R.id.imagebtn6);
        imageButton5.setOnClickListener(new ImageButtonOnClick());
        imageButton6.setOnClickListener(new ImageButtonOnClick());
        imageButton7=(ImageButton)findViewById(R.id.imagebtn7);
        imageButton7.setOnClickListener(new ImageButtonOnClick());
       scanBtn = (ImageButton) findViewById(R.id.scan);
       scanBtn.setOnClickListener(this);
       center=(ImageButton)findViewById(R.id.center);
       center.setOnClickListener(new ImageButtonOnClick());
       edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View view, boolean b) {
               if(b){
                   Toast.makeText(MainActivity.this,"页面正在跳转，请稍后",Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent();
                   intent.setClass(MainActivity.this,SearchActivity.class);
                   startActivity(intent);
                   edit.clearFocus();
                   edit.setCursorVisible(false);
               }

           }
       });
//        Random random = new Random();
//        //准备数据
//        for (int i = 0; i < 5; i++) {
//            sColos.add(Color.argb(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255)));
//        }

        //给适配器设置数据
        //mLooperPagerAdapter.setData(sColos);
        //
        //mLooperPagerAdapter.notifyDataSetChanged();
        mHandler = new Handler();
    }
    @Override
    public void onClick(View v) {
        AndPermission.with(this)
                .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);

                        ZxingConfig config = new ZxingConfig();
                        config.setPlayBeep(true);
                        config.setShake(true);
                        intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);

                        startActivityForResult(intent,REQUEST_CODE_SCAN);

                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

                    }
                }).start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                Intent intent = new Intent();
                intent.putExtra("Result",content);
                intent.setClass(MainActivity.this,ScanActivity.class);
                startActivity(intent);
            }
        }
    }

    class ImageButtonOnClick implements View.OnClickListener{
       @Override
       public void onClick(View view) {
           switch (view.getId()){
               case R.id.imagebtn3:
                   Intent intent = new Intent();
                   intent.setClass(MainActivity.this, ReceiveActivity.class);
                   startActivity(intent);
                   MainActivity.this.finish();
                   break;
               case R.id.imagebtn4:
                   Intent intent2 = new Intent();
                   intent2.setClass(MainActivity.this, ReceiveActivity.class);
                   startActivity(intent2);
                   MainActivity.this.finish();
                   break;
               case R.id.center:
                   Intent intent3 = new Intent();
                   intent3.setClass(MainActivity.this, CenterActivity.class);
                   startActivity(intent3);
                   MainActivity.this.finish();
                   break;
               case R.id.imagebtn5:
                   Intent intent4 = new Intent();
                   intent4.setClass(MainActivity.this,Sendpackage.class);
                   startActivity(intent4);
                   MainActivity.this.finish();
                   break;
               case R.id.imagebtn6:
                   Intent intent5 = new Intent();
                   intent5.setClass(MainActivity.this,Sendpackage.class);
                   startActivity(intent5);
                   MainActivity.this.finish();
                   break;
               case R.id.imagebtn7:
                   Intent intent7 = new Intent();
                   intent7.setClass(MainActivity.this,Prohibit_page.class);
                   startActivity(intent7);
                   MainActivity.this.finish();
                   break;
       }
       }
   }
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        //当我这个界面绑定到窗口的时候
        mHandler.post(mLooperTask);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
        mHandler.removeCallbacks(mLooperTask);
    }

    private Runnable mLooperTask = new Runnable() {
        @Override
        public void run() {
            if (!mIsTouch) {
                //切换viewPager里的图片到下一个
                int currentItem = mLoopPager.getCurrentItem();
                mLoopPager.setCurrentItem(++currentItem, true);
            }
            mHandler.postDelayed(this, 3000);
        }
    };

    private void initView() {
        //就是找到这个viewPager控件
        mLoopPager = (MyViewPager) this.findViewById(R.id.looper_pager);
        //设置适配器
        mLooperPagerAdapter = new LooperPagerAdapter();
        mLooperPagerAdapter.setData(sPics);
        mLoopPager.setAdapter(mLooperPagerAdapter);
        mLoopPager.setOnViewPagerTouchListener(this);
        mLoopPager.addOnPageChangeListener(this);
        mPointContainer = (LinearLayout) this.findViewById(R.id.points_container);
        //根据图片的个数,去添加点的个数
        insertPoint();
        mLoopPager.setCurrentItem(mLooperPagerAdapter.getDataRealSize() * 100, false);
    }

    private void insertPoint() {
        for (int i = 0; i < sPics.size(); i++) {
            View point = new View(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40, 40);
            point.setBackground(getResources().getDrawable(R.drawable.shape_point_normal));
            layoutParams.leftMargin = 20;
            point.setLayoutParams(layoutParams);
            mPointContainer.addView(point);
        }
    }

    @Override
    public void onPagerTouch(boolean isTouch) {
        mIsTouch = isTouch;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //这个方法的调用,其实是viewPager停下来以后选中的位置
        int realPosition;
        if (mLooperPagerAdapter.getDataRealSize() != 0) {
            realPosition = position % mLooperPagerAdapter.getDataRealSize();
        } else {
            realPosition = 0;
        }
        setSelectPoint(realPosition);
    }

    private void setSelectPoint(int realPosition) {
        for (int i = 0; i < mPointContainer.getChildCount(); i++) {
            View point = mPointContainer.getChildAt(i);
            if (i != realPosition) {
                //那就是白色
                point.setBackgroundResource(R.drawable.shape_point_normal);
            } else {
                //选中的颜色
                point.setBackgroundResource(R.drawable.shape_point_selected);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }}
