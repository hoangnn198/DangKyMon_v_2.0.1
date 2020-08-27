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

import com.example.dangkymonhoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_dangkymonhoc, img_trangthaimonhoc, img_lichHoc, img_caidat,img_lichsuhoc;
    TextView tv_dangkymonhoc,tv_trangthaimonhoc,tv_lichhoc,tv_caidat,tv_lichsuhoc;
    TextView tvMssv,tvTen;
    String idSV, maSV,TenSinhVien,idNganh;

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

                                    idNganh=data.getString("Id_NganhHoc");

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
        img_lichsuhoc = findViewById(R.id.img_lichsumonhoc);
        img_dangkymonhoc = findViewById(R.id.img_dangkymonhoc);
        img_trangthaimonhoc = findViewById(R.id.img_trangthaidangky);
        img_lichHoc = findViewById(R.id.img_lichHoc);
        img_caidat = findViewById(R.id.img_caidat);

        tv_lichsuhoc = findViewById(R.id.tv_lichsumonhoc);
        tv_dangkymonhoc = findViewById(R.id.tv_dangkymonhoc);
        tv_trangthaimonhoc = findViewById(R.id.tv_trangthaidangky);
        tv_lichhoc = findViewById(R.id.tv_lichhoc);
        tv_caidat = findViewById(R.id.tv_caidat);

    }

    private void Click(){

        img_lichsuhoc.setOnClickListener(this);
        img_dangkymonhoc.setOnClickListener(this);
        img_trangthaimonhoc.setOnClickListener(this);
        img_lichHoc.setOnClickListener(this);
        img_caidat.setOnClickListener(this);

        tv_lichsuhoc.setOnClickListener(this);
        tv_dangkymonhoc.setOnClickListener(this);
        tv_trangthaimonhoc.setOnClickListener(this);
        tv_lichhoc.setOnClickListener(this);
        tv_caidat.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_lichsumonhoc:
                Intent lichsuhoc = new Intent(this, LichSuHocActivity.class);
                lichsuhoc.putExtra("idSV",idSV);
                lichsuhoc.putExtra("idNganh",idNganh);
                startActivity(lichsuhoc);
                break;
            case R.id.img_dangkymonhoc:
                Intent dangkymonhoc = new Intent(this, DangKyActivity.class);
                dangkymonhoc.putExtra("idSV",idSV);
                startActivity(dangkymonhoc);
                break;
            case R.id.img_trangthaidangky:
                Intent trangthaidangky = new Intent(this, TrangthaiduyetActivity.class);
                trangthaidangky.putExtra("idSV",idSV);
                startActivity(trangthaidangky);
                break;
            case R.id.img_lichHoc:
                Intent lichhoc = new Intent(this, LichHocActivity.class);
                lichhoc.putExtra("idSV",idSV);
                startActivity(lichhoc);
                break;
            case R.id.img_caidat:
                Intent caidat = new Intent(this, SettingActivity.class);
                caidat.putExtra("maSV",maSV);
                startActivity(caidat);
                break;

            case R.id.tv_lichsumonhoc:
                Intent lichsuhoc1 = new Intent(this, LichSuHocActivity.class);
                lichsuhoc1.putExtra("idSV",idSV);
                lichsuhoc1.putExtra("idNganh",idNganh);
                startActivity(lichsuhoc1);
                break;
            case R.id.tv_dangkymonhoc:
                Intent dangkymonhoc1 = new Intent(this, DangKyActivity.class);
                dangkymonhoc1.putExtra("idSV",idSV);
                startActivity(dangkymonhoc1);
                break;
            case R.id.tv_trangthaidangky:
                Intent trangthaidangky1 = new Intent(this, TrangthaiduyetActivity.class);
                trangthaidangky1.putExtra("idSV",idSV);
                startActivity(trangthaidangky1);
                break;
            case R.id.tv_lichhoc:
                Intent lichhoc1 = new Intent(this, LichHocActivity.class);
                lichhoc1.putExtra("idSV",idSV);
                startActivity(lichhoc1);
                break;
            case R.id.tv_caidat:
                Intent caidat1 = new Intent(this, SettingActivity.class);
                caidat1.putExtra("maSV",maSV);
                startActivity(caidat1);
                break;

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
