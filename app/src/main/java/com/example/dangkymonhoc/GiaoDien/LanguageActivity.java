package com.example.dangkymonhoc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.dangkymonhoc.R;

public class LanguageActivity extends AppCompatActivity {
    String maSV;
    ImageButton img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        img_back= findViewById(R.id.btnBackNgonNgu);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LanguageActivity.this, SettingActivity.class);
                i.putExtra("maSV",maSV);
                startActivity(i);


            }
        });
        Intent intent = getIntent();
        maSV = intent.getStringExtra("maSV");
        Log.d("maSvUser",maSV);
    }
}