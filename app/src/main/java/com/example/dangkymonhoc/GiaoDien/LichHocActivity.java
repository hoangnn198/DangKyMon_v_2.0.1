package com.example.dangkymonhoc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.Adapter.LichHocAdapter;
import com.example.dangkymonhoc.Adapter.LopHocAdapter;
import com.example.dangkymonhoc.GiaoDienDangKy.LopHocActivity;
import com.example.dangkymonhoc.GiaoDienDangKy.TrangthaiduyetActivity;
import com.example.dangkymonhoc.Model.LichHoc;
import com.example.dangkymonhoc.Model.LopHoc;
import com.example.dangkymonhoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LichHocActivity extends AppCompatActivity {
    ListView listView;
    LichHocAdapter lichHocAdapter;
    ArrayList<LichHoc> listLichHoc;
    String idSV;
    TextView tvNgay;
    String strToday;
    ImageButton imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichhoc);
        imgBack=findViewById(R.id.btnBackLichHoc);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LichHocActivity.this, HomeActivity.class);
                finish();
            }

        });
        Intent intent = getIntent();
        idSV = intent.getStringExtra("idSV");
        listView = findViewById(R.id.lvLichHoc);
//        tvNgay = findViewById(R.id.textclock);
//        TextClock textClock = findViewById(R.id.textclock);
//        String formatdate = "EE, dd-MM-yyyy";
//        textClock.setFormat12Hour(formatdate);
//        textClock.setFormat24Hour(formatdate);
        getLichHoc();
        DateFormat df = new SimpleDateFormat("EE, dd-MM-YYYY");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        strToday = df.format(c.getTime());
        Log.d("StartDate: ",strToday);
//
}
    private  void getLichHoc(){
        String url = "https://dangkymonhoc.000webhostapp.com/API/getLichHocDemo.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG: ",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            if (jsonObject.getInt("resultCode") == 1){
                                Toast.makeText(LichHocActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                listLichHoc = new ArrayList<LichHoc>();
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject data = jsonArray.getJSONObject(i);

                                    if(data.getString("Ngay").equalsIgnoreCase(strToday)){
                                        LichHoc lichHoc = new LichHoc();
                                        lichHoc.setNgayHoc(data.getString("Ngay"));

                                        JSONArray dataNgay = data.getJSONArray("dataNgay");
                                        for (int j=0;j<dataNgay.length();j++){
                                            JSONObject jsonObject1 = dataNgay.getJSONObject(j);
                                            lichHoc.setLopHoc(jsonObject1.getString("LopHoc"));
                                            lichHoc.setMonHoc(jsonObject1.getString("MonHoc"));
                                            lichHoc.setGiangVien(jsonObject1.getString("GiangVien"));
                                            lichHoc.setCaHoc(jsonObject1.getString("CaHoc"));
                                            listLichHoc.add(lichHoc);
                                        }
                                    }



                                }
                                Log.d("vvv",listLichHoc.toString());
                                lichHocAdapter = new LichHocAdapter(LichHocActivity.this,R.layout.items_lichhoc,listLichHoc);
                                listView.setAdapter(lichHocAdapter);

                            }else{
                                Toast.makeText(LichHocActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
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
                params.put("idSV",idSV);
//                params.put("ngay","28-07-2020");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
