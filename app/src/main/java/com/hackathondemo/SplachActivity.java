package com.hackathondemo;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class SplachActivity extends AppCompatActivity {

    private ImageView ivQRCode;
    private DatabaseReference myRef;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        ivQRCode = (ImageView) findViewById(R.id.ivQrCode);
        myRef = FirebaseDatabase.getInstance().getReference().child("QR");
        final Handler handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                // Insert custom code here
                String key = myRef.push().getKey();
                Writer writer = new QRCodeWriter();
                try {
                    BitMatrix bm = writer.encode(key, BarcodeFormat.QR_CODE, 512, 512);
                    Bitmap imageBitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
                    for (int i = 0; i < 512; i++) {//width
                        for (int j = 0; j < 512; j++) {//height
                            imageBitmap.setPixel(i, j, bm.get(i, j) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    if(imageBitmap != null) {
                        ivQRCode.setImageBitmap(imageBitmap);
                        MyModel myModel = new MyModel();
                        MyModel mLang = new MyModel();
                        mLang.setAreaName("Area 1");
                        myModel.setEn(mLang);
                        mLang = new MyModel();
                        mLang.setAreaName("المنطقة 1");
                        myModel.setAr(mLang);
                        myModel.setQrCode(key);
                        myRef.child("area1").setValue(myModel);
                    }
                } catch (WriterException e) {

                }
                // Repeat every 2 seconds
                handler.postDelayed(runnable, 5000);
            }
        };

        handler.post(runnable);

    }
}
