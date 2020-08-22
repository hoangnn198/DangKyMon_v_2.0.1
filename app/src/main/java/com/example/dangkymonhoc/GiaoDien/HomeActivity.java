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
import com.example.dangkymonhoc.GiaoDienDangKy.DangKyActivity;
import com.example.dangkymonhoc.GiaoDienDangKy.NganhHocActivity;

import com.example.dangkymonhoc.GiaoDienDangKy.TrangthaiduyetActivity;
import com.example.dangkymonhoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_nganhHoc, img_monHoc, img_lichHoc, img_thongTin;
    TextView tvMssv,tvTen;
    String idSV, maSV,TenSinhVien;

    private RequestQueue tQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        maSV = intent.getStringExtra("maSV");
//        Log.d("AA",maSV);
        AnhXa();
        getHome();
        Click();
        tvTen = findViewById(R.id.tv_name);
        tvMssv = findViewById(R.id.tv_mssv);



    }

    private void getHome() {
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
                                    idSV = data.getString("IdSinhVien");
                                    Log.d("idSV: ",idSV);
                                    maSV = data.getString("MaSinhVien");
                                    TenSinhVien = data.getString("TenSinhVien");

                                    tvMssv.setText(maSV);
                                    tvTen.setText(TenSinhVien);
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
                Log.d("AA2",maSV);
                params.put("maSV",maSV);
                return params;

            }
        };
        tQueue.add(stringRequest);

    }


    private void AnhXa(){
        img_nganhHoc = findViewById(R.id.img_nganhHoc);
        img_monHoc = findViewById(R.id.img_monHoc);
        img_lichHoc = findViewById(R.id.img_lichHoc);
        img_thongTin = findViewById(R.id.img_caiDat);
    }

    private void Click(){

        img_nganhHoc.setOnClickListener(this);
        img_monHoc.setOnClickListener(this);
        img_lichHoc.setOnClickListener(this);
        img_thongTin.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_nganhHoc:
                Intent nganhhoc = new Intent(this, DangKyActivity.class);
                nganhhoc.putExtra("idSV",idSV);
                startActivity(nganhhoc);
                break;
            case R.id.img_monHoc:
                Intent monhoc = new Intent(this, TrangthaiduyetActivity.class);
                monhoc.putExtra("idSV",idSV);
                startActivity(monhoc);
                break;
            case R.id.img_lichHoc:
                Intent lichhoc = new Intent(this, LichHocActivity.class);
                lichhoc.putExtra("idSV",idSV);
                startActivity(lichhoc);
                break;
            case R.id.img_caiDat:
                Intent thongtin = new Intent(this, SettingActivity.class);
                thongtin.putExtra("maSV",maSV);
                startActivity(thongtin);
                break;

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
