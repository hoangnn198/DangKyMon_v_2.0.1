package com.example.dangkymonhoc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SettingActivity extends AppCompatActivity {
    private RequestQueue tQueue;
    TextView tvMssv,tvTenSV,tvUserSV,tvEditPass,tvLanguage,tvThoat;

    String maSV,TenSinhVien;
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        tvMssv = findViewById(R.id.tvMSSV);
        tvTenSV = findViewById(R.id.tvTen);
        tvUserSV = findViewById(R.id.tvUser);
        tvThoat=findViewById(R.id.tvThoat);
        tvLanguage= findViewById(R.id.tvLanguage);
        tvEditPass = findViewById(R.id.tvEditPassword);
        imgBack=findViewById(R.id.btnBackCaidat);


        Intent intent = getIntent();
        maSV = intent.getStringExtra("maSV");
        Log.d("maSV",maSV);
        getUser();
        tvLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, LanguageActivity.class);
//                i.putExtra("maSV",maSV);

                startActivity(i);
            }
        });

        tvEditPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, EditPassActivity.class);
                i.putExtra("maSV",maSV);
                startActivity(i);
            }
        });


        tvUserSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, UserActivity.class);
                i.putExtra("maSV",maSV);
                startActivity(i);

            }
        });
        tvThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });


    }

    private void getUser() {
        tQueue = Volley.newRequestQueue(this);
        String url = "https://dangkymonhoc.000webhostapp.com/API/getSinhVien.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("user",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (jsonObject.getInt("resultCode") == 1){
                                for(int i = 0;i<jsonArray.length();i++){
                                    JSONObject data = jsonArray.getJSONObject(i);

//                                    maSV = data.getString("MaSinhVien");
                                    TenSinhVien = data.getString("TenSinhVien");

                                    tvMssv.setText(maSV);
                                    tvTenSV.setText(TenSinhVien);
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("maSV",maSV);
                return params;

            }
        };
        tQueue.add(stringRequest);

    }
}
