package com.example.dth_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dth_project.helper.Functions;
import com.google.android.material.textfield.TextInputLayout;


public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();

    private Button btnRegister, btnLinkToLogin;
    private TextInputLayout inputName, inputEmail, inputPassword;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = findViewById(R.id.rTextName);
        inputEmail = findViewById(R.id.rTextEmail);
        inputPassword = findViewById(R.id.rTextPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLinkToLogin = findViewById(R.id.btnLinkToLoginScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Hide Keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        init();
    }

    private void init() {
        // Login button Click Event
        btnRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                // Hide Keyboard
//                Functions.hideSoftKeyboard(RegisterActivity.this);

                String name = inputName.getEditText().getText().toString().trim();
                String username = inputEmail.getEditText().getText().toString().trim();
                String pass = inputPassword.getEditText().getText().toString().trim();

                // Check for empty data in the form
                if (!name.isEmpty() && !username.isEmpty() && !pass.isEmpty()) {
                    if (Functions.isValidEmailAddress(username)) {
                        registerUser(name, username, pass);
                    } else {
                        Toast.makeText(getApplicationContext(), "Email is not valid!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
                }
            }

        });

        // Link to Register Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, Login.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void registerUser(final String name, final String username, final String pass) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
//        showDialog();

//        StringRequest strReq = new StringRequest(Request.Method.POST,
//                Functions.REGISTER_URL, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, "Register Response: " + response);
//                hideDialog();
//
//                try {
//                    JSONObject jObj = new JSONObject(response);
//                    boolean error = jObj.getBoolean("error");
//                    if (!error) {
//                        Functions logout = new Functions();
//                        logout.logoutUser(getApplicationContext());
//
//                        Bundle b = new Bundle();
//                        b.putString("email", email);
//                        Intent i = new Intent(RegisterActivity.this, EmailVerify.class);
//                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        i.putExtras(b);
//                        startActivity(i);
//                        pDialog.dismiss();
//                        finish();
//
//                    } else {
//                        // Error occurred in registration. Get the error
//                        // message
//                        String errorMsg = jObj.getString("message");
//                        Toast.makeText(getApplicationContext(),errorMsg, Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Registration Error: " + error.getMessage(), error);
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                hideDialog();
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() {
//                // Posting params to register url
//                Map<String, String> params = new HashMap<>();
//                params.put("name", name);
//                params.put("email", email);
//                params.put("password", password);
//
//                return params;
//            }
//
//        };
//
//        // Adding request to request queue
//        MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
//    }
//
//    private void showDialog() {
//            if (!pDialog.isShowing())
//                pDialog.show();
//        }
//
//    private void hideDialog() {
//        if (pDialog.isShowing())
//            pDialog.dismiss();
    }
}
