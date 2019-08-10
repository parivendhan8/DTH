package com.example.dth_project;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.dth_project.PojoPack.DetailsPojo;
import com.example.dth_project.helper.DatabaseHandler;
import com.example.dth_project.helper.Functions;
import com.example.dth_project.helper.SessionManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends Activity {
    private Object data;
    //    DetailsPojo detailsPojo= (DetailsPojo) new Gson().fromJson((JsonElement) data,new TypeToken<DetailsPojo>(){}.getRawType());
//    romjson(data, new TypeToken< DetailsPojo > {}.getRawType());
    private static final String TAG = Login.class.getSimpleName();
    private Button btnLogin;
    final String type = "1";
    private EditText inputEmail;
    private Gson gson = new Gson();
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private DatabaseHandler db;
    String jsonString = "{\"main\":[{\"id\":\"1\",\"menu_id\":\"ic_action_email\",\"display_text\":\"Masters\"},{\"id\":\"5\",\"menu_id\":\"ic_action_good\",\"display_text\":\"Receipt\"},{\"id\":\"6\",\"menu_id\":\"ic_action_good\",\"display_text\":\"payment\"},{\"id\":\"7\",\"menu_id\":\"ic_action_good\",\"display_text\":\"Auction\"},{\"id\":\"8\",\"menu_id\":\"ic_action_good\",\"display_text\":\"Reports\"},{\"id\":\"12\",\"menu_id\":\"ic_action_good\",\"display_text\":\"Banking\"},{\"id\":\"15\",\"menu_id\":\"ic_action_good\",\"display_text\":\"SMS\"},{\"id\":\"18\",\"menu_id\":\"ic_action_good\",\"display_text\":\"Logout\"}],\"sub\":{\"1\":[{\"id\":\"2\",\"menu_id\":\"1\",\"display_text\":\"subscribers\"},{\"id\":\"3\",\"menu_id\":\"1\",\"display_text\":\"Agents\"},{\"id\":\"4\",\"menu_id\":\"1\",\"display_text\":\"Employees\"},{\"id\":\"14\",\"menu_id\":\"1\",\"display_text\":\"Group\"}],\"8\":[{\"id\":\"9\",\"menu_id\":\"1\",\"display_text\":\"Collection\"},{\"id\":\"10\",\"menu_id\":\"1\",\"display_text\":\"Pending\"},{\"id\":\"11\",\"menu_id\":\"1\",\"display_text\":\"Auction\"}],\"15\":[{\"id\":\"13\",\"menu_id\":\"1\",\"display_text\":\"Delivery Reports\"},{\"id\":\"16\",\"menu_id\":\"1\",\"display_text\":\"Send Sms\"},{\"id\":\"17\",\"menu_id\":\"1\",\"display_text\":\"Template\"}]}}}";
    int versionCode = BuildConfig.VERSION_CODE;
    String versionName = BuildConfig.VERSION_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        System.out.println("san8" + versionCode + "versionname" + versionName);
        if (!CheckNetwork.isInternetAvailable(Login.this)) //returns true if internet available
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
            alertDialogBuilder.setMessage("No Internet Connection; Check Your Network Connection");

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }


        inputEmail = (EditText) findViewById(R.id.lEditEmail);
        inputPassword = (EditText) findViewById(R.id.lEditPassword);

//        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        // SQLite database handler
        db = new DatabaseHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Login button Click Event

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();


                // Check for empty data in the form

                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    System.out.println("input not null");
//                    Toast.makeText(getApplicationContext(),"1st chck",Toast.LENGTH_LONG).show();

                    checkLogin(email, password);
                    //   newLogin(email, password,typeid);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });


    }


    /**
     * function to verify login details in mysql db
     */
    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        pDialog.setMessage("Logging in ...");
        showDialog();


        final StringRequest strReq = new StringRequest(Request.Method.POST, Functions.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response);
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    session.SavePref_String("LoginDetails", jObj.toString());




                    /*DetailsPojo detailsPojo= (DetailsPojo) new Gson().fromJson((JsonElement) data,new TypeToken<DetailsPojo>(){}.getRawType());
                    if(detailsPojo!=null){
                        if(detailsPojo.getData()!=null) {

                        }
                    }*/
                    JSONObject jObj2 = jObj.getJSONObject("data");
                    JSONArray jObj3 = jObj2.getJSONArray("clist");


//               Toast.makeText(getApplicationContext(),"Loadingggg", Toast.LENGTH_LONG).show();
//               JSONArray weather = jObj.getJSONArray("error");
                    //   Boolean error = jObj.getBoolean("error");
                    boolean errorid = jObj.getBoolean("error");
                    Log.d(TAG, "Error Response: " + errorid);

                    // Check for error node in json
                    if (!errorid) {
                        // user successfully logged in
                        // Create login session
                        Toast.makeText(getApplicationContext(), "Logged in Successfully" + email + password, Toast.LENGTH_LONG).show();
                        System.out.println("pass crct");
//
                        String test = jObj2.getString("clist");
                        System.out.println(test);
//                        Toast.makeText(getApplicationContext(), (CharSequence) test,Toast.LENGTH_LONG).show();
                        session.setLogin(true);
                        // Now store the user in SQLite

                        JSONObject user = jObj.getJSONObject("data");
                        String id = user.getString("uid");
                        String name = user.getString("uname");
//                        String email = user.getString("email");
//                        String created_at = user.getString("created_at");

//                        Intent intent = new Intent(Login.this,
//                                MainActivity.class);
//                        startActivity(intent);
                        // Inserting row in users table
                        db.addUser(name, id, email);
//
                        // Launch main activity
                        Intent intent = new Intent(Login.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        session.setLogin(false);
//                        Toast.makeText(getApplicationContext(),
//                                errorMsg, Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),
                                "Login Credentials are Wrong", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error

                    e.printStackTrace();
                    session.setLogin(false);
                    Log.e("response", "" + response);

//                    Toast.makeText(getApplicationContext(),"Login Credentials are wrong. Please Try Again!",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                params.put("type", "1");


                return params;
            }

        };

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}


//package com.example.tpk_project;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Login extends AppCompatActivity {
//    private static final String TAG = Login.class.getSimpleName();
//    EditText inputEmail,inputPassword;
//TextView tv1;
//    String jsonString = "{\"main\":[{\"id\":\"1\",\"menu_id\":\"ic_action_email\",\"display_text\":\"Masters\"},{\"id\":\"5\",\"menu_id\":\"ic_action_good\",\"display_text\":\"Receipt\"},{\"id\":\"6\",\"menu_id\":\"ic_action_good\",\"display_text\":\"payment\"},{\"id\":\"7\",\"menu_id\":\"ic_action_good\",\"display_text\":\"Auction\"},{\"id\":\"8\",\"menu_id\":\"ic_action_good\",\"display_text\":\"Reports\"},{\"id\":\"12\",\"menu_id\":\"ic_action_good\",\"display_text\":\"Banking\"},{\"id\":\"15\",\"menu_id\":\"ic_action_good\",\"display_text\":\"SMS\"},{\"id\":\"18\",\"menu_id\":\"ic_action_good\",\"display_text\":\"Logout\"}],\"sub\":{\"1\":[{\"id\":\"2\",\"menu_id\":\"1\",\"display_text\":\"subscribers\"},{\"id\":\"3\",\"menu_id\":\"1\",\"display_text\":\"Agents\"},{\"id\":\"4\",\"menu_id\":\"1\",\"display_text\":\"Employees\"},{\"id\":\"14\",\"menu_id\":\"1\",\"display_text\":\"Group\"}],\"8\":[{\"id\":\"9\",\"menu_id\":\"1\",\"display_text\":\"Collection\"},{\"id\":\"10\",\"menu_id\":\"1\",\"display_text\":\"Pending\"},{\"id\":\"11\",\"menu_id\":\"1\",\"display_text\":\"Auction\"}],\"15\":[{\"id\":\"13\",\"menu_id\":\"1\",\"display_text\":\"Delivery Reports\"},{\"id\":\"16\",\"menu_id\":\"1\",\"display_text\":\"Send Sms\"},{\"id\":\"17\",\"menu_id\":\"1\",\"display_text\":\"Template\"}]}}}";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        inputEmail = (EditText) findViewById(R.id.email);
//        inputPassword = (EditText) findViewById(R.id.password);
//        tv1=(TextView)findViewById(R.id.title_top);
//        final ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setCancelable(false);
//
//        Button loginbtnnn=(Button)findViewById(R.id.btn_login_admin);
//
//        loginbtnnn.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceType")
//            @Override
//            public void onClick(View view) {
//                if(inputEmail.getText().toString().equals(getString(R.string.email1))&& inputPassword.getText().toString().equals(getString(R.string.pass1)))
//                {
//                    Intent intent = new Intent(Login.this, MainActivity.class);
//                    startActivity(intent);
//
//
//                }
//                else
//                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//    }
//    }
//
//Intent intent = new Intent(Login.this,
///                                MainActivity.class);
//                        startActivity(intent);
//                        JSONArray movies=new JSONArray(response);
//                        jObj.getJSONArray(response);
//                        for(int i=0; i < movies.length() ; i++) {
//                            JSONObject json_data = movies.getJSONObject(i);
//                            int id=json_data.getInt("id");
//                            String name=json_data.getString("name");
//                            Log.d(name,"Output");
//                            Toast.makeText(getApplicationContext(),json_data+name ,Toast.LENGTH_LONG).show();
//                        }

