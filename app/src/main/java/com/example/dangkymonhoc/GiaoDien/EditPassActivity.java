package com.example.dangkymonhoc.GiaoDien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditPassActivity extends AppCompatActivity {
    String maSV;
    ImageButton img_Back;
    Button btn_EditPass;
    EditText et_Pass, et_newPass, et_newPassConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass);

        btn_EditPass = findViewById(R.id.btnEditPass);
        img_Back = findViewById(R.id.btnBackEditPass);


        img_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        Intent intent = getIntent();
        maSV = intent.getStringExtra("maSV");
        Log.d("maSvUser", maSV);
        anhxa();
        btn_EditPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePass();
            }
        });

    }


    private void anhxa() {
        et_Pass = findViewById(R.id.edtPass1);
        et_newPass = findViewById(R.id.edtPass2);
        et_newPassConfirm = findViewById(R.id.edtPass3);
    }

    private void changePass() {
        final String password = this.et_Pass.getText().toString().trim();
        final String newpass = this.et_newPass.getText().toString().trim();
        final String newPassconfirm = this.et_newPassConfirm.getText().toString().trim();

        String url = "https://dangkymonhoc.000webhostapp.com/API/resetPassword.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAGr",response);
                        String message ="";
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("resultCode") == 1){
                                if (newpass.equals(newPassconfirm)){
                               // message = jsonObject.getString("message");
                                Toast.makeText(EditPassActivity.this, "doi mat khau thanh cong", Toast.LENGTH_SHORT).show();}
                                else{
                                    Toast.makeText(EditPassActivity.this,"Mat khau khong khop",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                message = jsonObject.getString("message");
                                Toast.makeText(EditPassActivity.this, message, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("masv", maSV);
                params.put("password", password);
                params.put("newpass",newpass);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}