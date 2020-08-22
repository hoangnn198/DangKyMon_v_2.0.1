package com.example.dangkymonhoc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class UserActivity extends AppCompatActivity {
    String maSV;
    ImageButton imgback;
    TextView TenSV,MaSV,Email,GioiTinh,Phone,DiaChi,Nganh,TrangThai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);
        imgback = findViewById(R.id.btnBackUser);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        maSV = intent.getStringExtra("maSV");
        Log.d("idSV",maSV);
        anhxa();
        getUserInfo();
    }

    private void anhxa() {
        TenSV = findViewById(R.id.tvTenSV);
        MaSV = findViewById(R.id.tvMSSV);
        Email = findViewById(R.id.tvEmail);
        GioiTinh = findViewById(R.id.tvGioiTinh);
        Phone = findViewById(R.id.tvSdt);
        DiaChi = findViewById(R.id.tvDiachi);
        Nganh = findViewById(R.id.tvNganh);
        TrangThai = findViewById(R.id.tvTrangThai);
    }

    private void getUserInfo() {
        String url = "https://dangkymonhoc.000webhostapp.com/API/getSinhVien.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG",response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++){
                                if (jsonObject.getInt("resultCode") == 1){
                                    JSONObject data = jsonArray.getJSONObject(i);
                                    Toast.makeText(UserActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                    TenSV.setText(data.getString("TenSinhVien"));
                                    MaSV.setText(data.getString("MaSinhVien"));
                                    Email.setText(data.getString("Email"));
                                    GioiTinh.setText(data.getString("GioiTinh"));
                                    Phone.setText(data.getString("Phone_Number"));
                                    DiaChi.setText(data.getString("DiaChi"));
                                    Nganh.setText(data.getString("TenNganh"));
                                    TrangThai.setText(data.getString("HocKy"));

//
                                }else {
                                    Toast.makeText(UserActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
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
                        Log.d("L",error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("maSV",maSV);
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
}