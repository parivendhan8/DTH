package com.example.dth_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class CheckDataBase extends AppCompatActivity implements CheckDataBase1 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_data_base);
    }
    private Map<String, String> putParams() {
        // Posting parameters to login url
        Map<String, String> params = new HashMap<String, String>();
        String email = "check1";
        params.put("email", email);
        String password = "check2";
        params.put("password", password);
        params.put("type", "2");
        params.get("clist");
        System.out.println("san6111"+params.get("rec"));
        System.out.println("san71"+params.get("clist"));
        return params;
    }
}
