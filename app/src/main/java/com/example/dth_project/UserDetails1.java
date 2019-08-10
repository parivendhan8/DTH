package com.example.dth_project;

import android.app.Activity;
//MainActivity for UserDetails.xml


        import android.app.ProgressDialog;
        import android.os.AsyncTask;
        import android.os.Bundle;

        import android.widget.ListView;
        import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;

public class UserDetails1 extends AppCompatActivity {
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_EMPLOYEE_ID = "employee_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DOB = "dob";
    private static final String KEY_DESIGNATION = "designation";
    private static final String KEY_CONTACT_NUMBER = "contact_number";

    private String url = "http://dthsoft.in/api/mobile/";
    private ProgressDialog pDialog;
    private int success;
    private UserDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details);
        //Call the AsyncTask
        new FetchEmployeeDetails().execute();

    }

    private class FetchEmployeeDetails extends AsyncTask<String, String, String> {
        JSONObject response;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(UserDetails1.this);
            pDialog.setMessage("Loading Data.. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser jsonParser = new HttpJsonParser();
            response = jsonParser.makeHttpRequest(url,"GET",null);

            return null;
        }

        protected void onPostExecute(String result) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {

                    ListView listView =(ListView)findViewById(R.id.employeeList);
                    if (success == 1) {
                        try {
                            JSONArray employeeArray =  response.getJSONArray(KEY_DATA);
                            List<UserDetails> employeeList = new ArrayList<>();
                            //Populate the EmployeeDetails list from response
                            for (int i = 0; i<employeeArray.length();i++){
                                UserDetails employeeDetails = new UserDetails();
                                JSONObject employeeObj = employeeArray.getJSONObject(i);
                                employeeDetails.setUid(employeeObj.getInt(KEY_EMPLOYEE_ID));
                                employeeDetails.setUname(employeeObj.getString(KEY_NAME));
                                employeeDetails.setUqid(employeeObj.getString(KEY_DOB));
                                employeeDetails.setCid(employeeObj.getString(KEY_DESIGNATION));
                                employeeDetails.setRid(employeeObj.getString(KEY_CONTACT_NUMBER));

                                employeeList.add(employeeDetails);
                            }
                            //Create an adapter with the EmployeeDetails List and set it to the LstView
                            adapter = new UserDetailsAdapter(employeeList,getApplicationContext());
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Toast.makeText(UserDetails1.this,
                                "Some error occurred while loading data",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

}