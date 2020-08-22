package com.example.dangkymonhoc.GiaoDienDangKy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.Adapter.LopHocAdapter;
import com.example.dangkymonhoc.Adapter.TrangthaiAdapter;
import com.example.dangkymonhoc.GiaoDien.HomeActivity;
import com.example.dangkymonhoc.Model.LopHoc;
import com.example.dangkymonhoc.Model.TrangThaiDuyet;
import com.example.dangkymonhoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrangthaiduyetActivity extends AppCompatActivity {
    ListView lvTrangthai;
    TrangthaiAdapter trangthaiAdapter;
    ArrayList<TrangThaiDuyet> list;
    String idSV;
    ImageButton imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangthaiduyet);
        imgBack=findViewById(R.id.btnBackMCT);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangthaiduyetActivity.this, HomeActivity.class);
                finish();
            }
        });
        Intent intent = getIntent();
        idSV = intent.getStringExtra("idSV");
        Log.d("idSVMon",idSV);
        lvTrangthai = findViewById(R.id.lvTrangThai);

        getTrangThai();
    }

    private void getTrangThai() {
        String url = "https://dangkymonhoc.000webhostapp.com/API/getMonHocTheoTrangThai.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("MonHoc",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            Log.d("TAG",String.valueOf(jsonArray.length()));
                            if (jsonObject.getInt("resultCode")==1){
                                Toast.makeText(TrangthaiduyetActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                list = new ArrayList<TrangThaiDuyet>();

                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject data = jsonArray.getJSONObject(i);
                                    TrangThaiDuyet trangThaiDuyet = new TrangThaiDuyet();

                                    trangThaiDuyet.setMaMonHoc(data.getString("MaMonHoc"));
                                    trangThaiDuyet.setTenMonHoc(data.getString("MonHoc"));
                                    trangThaiDuyet.setTenLopHoc(data.getString("LopHoc"));
                                    trangThaiDuyet.setIdTrangThai(data.getString("IdTrangThaiDuyet"));
                                    trangThaiDuyet.setTrangThaiDuyet(data.getString("TrangThaiDuyet"));

                                    list.add(trangThaiDuyet);

                                }

                                trangthaiAdapter = new TrangthaiAdapter(TrangthaiduyetActivity.this,R.layout.items_trangthaiduyet,list);
                                lvTrangthai.setAdapter(trangthaiAdapter);
                            }else{
                                Toast.makeText(TrangthaiduyetActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idSinhVien",idSV);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}