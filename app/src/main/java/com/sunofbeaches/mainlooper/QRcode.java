package com.sunofbeaches.mainlooper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sunofbeaches.Encode.encrypt.RSAUtils;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QRcode extends Activity {


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

    private ImageView im1,im2;  //imageview图片
    private int w,h;        //图片宽度w,高度h
    public String string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);



        Button bt1=(Button)findViewById(R.id.button1);
        Button bt2=(Button)findViewById(R.id.button2);
        Button bt3=(Button)findViewById(R.id.button3);
        Button bt4=(Button)findViewById(R.id.button4);
        Button bt5=(Button)findViewById(R.id.button5);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                string = intent.getStringExtra("string");
                TextView editText=(TextView)findViewById(R.id.editText);
                editText.setText(string);
                createQRcodeImage(string);//自定义转换内容
            }
        });



        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im1=(ImageView)findViewById(R.id.imageView);
                Bitmap QRbmp = ((BitmapDrawable) (im1).getDrawable()).getBitmap();   //将图片bitmap化
                //将drawable里面的图片bitmap化
                Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.head);
                im1.setImageBitmap(addLogo(QRbmp,logo));
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String destring1 = string.split("###")[0];
                String decryptStr1 =RSAUtils.decrypt(PRIVATE_KEY,destring1);
                TextView editText=(TextView)findViewById(R.id.editText);
                editText.setText(decryptStr1);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String destring2 = string.split("###")[1];
                String decryptStr2 =RSAUtils.decrypt(PRIVATE_KEY1,destring2);
                TextView editText=(TextView)findViewById(R.id.editText);
                editText.setText(decryptStr2);
            }
        });


        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String destring3 = string.split("###")[2];
                String decryptStr3 =RSAUtils.decrypt(PRIVATE_KEY2,destring3);
                TextView editText=(TextView)findViewById(R.id.editText);
                editText.setText(decryptStr3);
            }
        });

    }

    //转换成二维码QRcode的函数。参数为一个字符串
    public void createQRcodeImage(String url)
    {
        im1=(ImageView)findViewById(R.id.imageView);
        w=im1.getWidth();
        h=im1.getHeight();
        try
        {
            //判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1)
            {
                return;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, w, h, hints);
            int[] pixels = new int[w * h];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < h; y++)
            {
                for (int x = 0; x < w; x++)
                {
                    if (bitMatrix.get(x, y))
                    {
                        pixels[y * w + x] = 0xff000000;
                    }
                    else
                    {
                        pixels[y * w + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
            //显示到我们的ImageView上面
            im1.setImageBitmap(bitmap);
        }
        catch (WriterException e)
        {
            e.printStackTrace();
        }
    }

    //识别二维码的函数
    public void recogQRcode(ImageView imageView){
        Bitmap QRbmp = ((BitmapDrawable) (imageView).getDrawable()).getBitmap();   //将图片bitmap化
        int width = QRbmp.getWidth();
        int height = QRbmp.getHeight();
        int[] data = new int[width * height];
        QRbmp.getPixels(data, 0, width, 0, 0, width, height);    //得到像素
        RGBLuminanceSource source = new RGBLuminanceSource(QRbmp);   //RGBLuminanceSource对象
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result re = null;
        try {
            //得到结果
            re = reader.decode(bitmap1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        //Toast出内容
        Toast.makeText(QRcode.this,re.getText(),Toast.LENGTH_SHORT).show();

        //利用正则表达式判断内容是否是URL，是的话则打开网页
        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";//设置正则表达式

        Pattern pat = Pattern.compile(regex.trim());//比对
        Matcher mat = pat.matcher(re.getText().trim());
        if (mat.matches()){
            Uri uri = Uri.parse(re.getText());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);//打开浏览器
            startActivity(intent);
        }

    }

    //给二维码添加图片
    //第一个参数为原二维码，第二个参数为添加的logo
    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        //如果原二维码为空，返回空
        if (src ==null ) {
            return null;
        }
        //如果logo为空，返回原二维码
        if (src ==null ||logo ==null) {
            return src;
        }

        //这里得到原二维码bitmap的数据
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        //logo的Width和Height
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        //同样如果为空，返回空
        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }
        //同样logo大小为0，返回原二维码
        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        //logo大小为二维码整体大小的1/9，可以自定义多大，越小越好
        //二维码有一定的纠错功能，中间图片越小，越容易纠错
        float scaleFactor = srcWidth * 1.0f / 9/ logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2,null );

            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }


    //识别图片所需要的RGBLuminanceSource类
    public class RGBLuminanceSource extends LuminanceSource {

        private byte bitmapPixels[];

        protected RGBLuminanceSource(Bitmap bitmap) {
            super(bitmap.getWidth(), bitmap.getHeight());

            // 首先，要取得该图片的像素数组内容
            int[] data = new int[bitmap.getWidth() * bitmap.getHeight()];
            this.bitmapPixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
            bitmap.getPixels(data, 0, getWidth(), 0, 0, getWidth(), getHeight());

            // 将int数组转换为byte数组，也就是取像素值中蓝色值部分作为辨析内容
            for (int i = 0; i < data.length; i++) {
                this.bitmapPixels[i] = (byte) data[i];
            }
        }

        @Override
        public byte[] getMatrix() {
            // 返回我们生成好的像素数据
            return bitmapPixels;
        }

        @Override
        public byte[] getRow(int y, byte[] row) {
            // 这里要得到指定行的像素数据
            System.arraycopy(bitmapPixels, y * getWidth(), row, 0, getWidth());
            return row;
        }
    }

}

