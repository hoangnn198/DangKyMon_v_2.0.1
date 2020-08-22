package com.example.dangkymonhoc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.MainActivity;
import com.example.dangkymonhoc.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText edtUser,edtPass;
    Button btnLogin;
    TextView tvResetPass;
    CheckBox cbRembemberPass;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String USERNAME = "userNameKey";
    public static final String PASS = "passKey";
    public static final String REMEMBER = "remember";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        loadData();



    }

    private void clearData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    private void saveData(String username, String Pass) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(PASS, Pass);
        editor.putBoolean(REMEMBER,cbRembemberPass.isChecked());
        editor.commit();
    }

    private void loadData() {
        if(sharedPreferences.getBoolean(REMEMBER,false)) {
            edtUser.setText(sharedPreferences.getString(USERNAME, ""));
            edtPass.setText(sharedPreferences.getString(PASS, ""));
            cbRembemberPass.setChecked(true);
        }
        else
            cbRembemberPass.setChecked(false);

    }

    private void anhxa() {
        tvResetPass = findViewById(R.id.tvResetPass);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        cbRembemberPass = findViewById(R.id.cb_rememberPass);

        tvResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ResetPassActivity.class);
                startActivity(i);
                finish();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userLogin = edtUser.getText().toString().trim();
                String passLogin = edtPass.getText().toString().trim();
                if (cbRembemberPass.isChecked())
                    //lưu lại thông tin đăng nhập
                    saveData(edtUser.getText().toString(), edtPass.getText().toString());
                else
                    clearData();//xóa thông tin đã lưu
                //nếu thông tin đăng nhập đúng thì đến màng hình home
                if (edtUser.getText().toString().equals(userLogin) && edtPass.getText().toString().equals(passLogin)) {
                    FunctionLogin();
                } else
                    Toast.makeText(LoginActivity.this, "Thông tin đăng nhập không đúng", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void FunctionLogin(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://dangkymonhoc.000webhostapp.com/API/loginUser.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG",response);
                        String message = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (jsonObject.getInt("resultCode") == 1){
                                message = jsonObject.getString("message");
                                Log.d("bbb",jsonObject.getString("MaSinhVien"));
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                intent.putExtra("maSV",jsonObject.getString("MaSinhVien"));
                                startActivity(intent);

                            }else {
                                message = jsonObject.getString("message");
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
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
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username",edtUser.getText().toString().trim());
                params.put("password",edtPass.getText().toString().trim());
                Log.d("user: ",edtUser.getText().toString().trim());
                Log.d("user2: ",edtPass.getText().toString().trim());
                return params;

            }
        };
        requestQueue.add(stringRequest);
//



    }
}