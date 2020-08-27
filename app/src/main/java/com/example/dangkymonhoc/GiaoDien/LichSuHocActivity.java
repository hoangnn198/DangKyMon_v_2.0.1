package com.example.dangkymonhoc.GiaoDien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.Adapter.LichSuAdapter;
import com.example.dangkymonhoc.Adapter.MonHocAdapter;
import com.example.dangkymonhoc.GiaoDienDangKy.MonHocActivity;
import com.example.dangkymonhoc.Model.LichSu;
import com.example.dangkymonhoc.Model.MonHoc;
import com.example.dangkymonhoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LichSuHocActivity extends AppCompatActivity {
    ImageButton imgBack;
    String idNganh,idSV;
    ListView lvLichSu;
    ArrayList<LichSu> list;
    LichSuAdapter lichSuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_hoc);
        imgBack = findViewById(R.id.btnBacklichsuhoc);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });
        Intent intent = getIntent();
        idNganh = intent.getStringExtra("idNganh");
        idSV = intent.getStringExtra("idSV");
        Log.d("idLichSuHoc",idSV);
        lvLichSu = findViewById(R.id.lvLichSuHoc);
        getLichSu();
    }

    private void getLichSu() {
        String url = "https://dangkymonhoc.000webhostapp.com/API/getlichsuhoc.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("gggg",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (jsonObject.getInt("resultCode") == 1){
                                Toast.makeText(LichSuHocActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                list = new ArrayList<LichSu>();
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject data = jsonArray.getJSONObject(i);

                                   LichSu lichSu = new LichSu();
//                                    monHoc.setIdSV(idSV);

                                    lichSu.setMonHoc(data.getString("MonHoc"));
                                    lichSu.setIdTrangThai(data.getString("Id_TrangThai"));
                                    lichSu.setTrangThai(data.getString("TrangThaiMon"));
                                    list.add(lichSu);
                                }
                               lichSuAdapter = new LichSuAdapter(LichSuHocActivity.this,R.layout.items_lichsuhoc,list);
                                lvLichSu.setAdapter(lichSuAdapter);
                            }else{
                                Toast.makeText(LichSuHocActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
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
                Log.d("AAAA111",idNganh+" - " +idSV);
                params.put("idNganh",idNganh);
                params.put("idSV",idSV);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
