package com.example.dth_project;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.toolbox.Volley;
import com.example.dth_project.PojoPack.Clist;
import com.example.dth_project.PojoPack.Data;
import com.example.dth_project.PojoPack.DetailsPojo;
import com.example.dth_project.helper.DatabaseHandler;
import com.example.dth_project.helper.Functions;
import com.example.dth_project.helper.SessionManager;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.cert.Extension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewReceipt extends androidx.fragment.app.Fragment {

    public static final String TAG = "AddNewReceipt";

    AutoCompleteTextView adnewname;
    SessionManager sessionManager;
    DatabaseHandler db;
    EditText paidamount,Referenceid;
    Button savebtn;
    TextView addnewDate;
    ProgressDialog progressDialog;
    int i;
    String abc,idvalue;
    String value,datevalue,cidvalue;
    String data;
    public static String nameval,dateval,paidamntval,refval;
    public int mYear, mMonth, mDay, mHour, mMinute;
//
    List<String> adnewNameres = new ArrayList<String>();
    public List<Clist> mCist = new ArrayList<Clist>();
    public List<Data> mDatalist=new ArrayList<Data>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addnew_receipt, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        db = new DatabaseHandler(getActivity());
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.frameLayout);
        final Fragment fragment = new ViewList();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        sessionManager=new SessionManager(getActivity());
        addnewDate=(TextView)view.findViewById(R.id.AN_date_Et);
        paidamount=(EditText)view.findViewById(R.id.paidamntid);
        Referenceid=(EditText)view.findViewById(R.id.AN_reference_Et);

      String Cid;
        Calendar cal = Calendar.getInstance();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String date_str = df.format(cal.getTime());
        addnewDate.setText(date_str);



//        addnewDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // Get Current Date
//                final Calendar c = Calendar.getInstance();
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
//                        new DatePickerDialog.OnDateSetListener() {
//
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//
//                                addnewDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//
//                            }
//                        }, mYear, mMonth, mDay);
//                datePickerDialog.show();
//            }
//        });
        adnewname = (AutoCompleteTextView) view.findViewById(R.id.AN_name_Et);
        savebtn=(Button)view.findViewById(R.id.savebtn_AD);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println("saverrsultname="+adnewname+"date="+addnewDate.getText()+"Paid Amount="+paidamount.getText()+"Reference="+Referenceid.getText());
//                Toast.makeText(getContext(),"name="+adnewname.getText()+"id"+"positon id==="+value+"date="+addnewDate.getText()+"Paid Amount="+paidamount.getText()+"Reference="+Referenceid.getText(),Toast.LENGTH_LONG).show();





                    saveValues();
//                   Toast.makeText(getActivity(),"Saved SuccessFully",Toast.LENGTH_LONG).show();
                androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage("Saved SuccessFully");

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();





            }



//            private void finish() {
//                Intent intent1 = new Intent(getActivity(),ViewList.class);
//                startActivity(intent1);
//            }
        });


        getAdnewVal();
         abc = adnewname.getText().toString();
         datevalue=addnewDate.getText().toString();
//        refvalue=Referenceid.getText().toString();


        return view;

    }
    public void saveValues() {

//                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
//                progressDialog.show();
        String tag_string_req = "req_addnew";
        final String name=adnewname.getText().toString().trim();
        final String date=addnewDate.getText().toString().trim();
        final String paidamnt=paidamount.getText().toString().trim();
        final String reference=Referenceid.getText().toString().trim();
//                final  int cid=
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Functions.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: "+response);

//                                progressDialog.dismiss();
//                                Toast.makeText(getActivity(),"res"+ response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jObj = new JSONObject(response);

                            parseData(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
            }
        }){
            @Override
            protected Map<String,String>getParams(){
                Map<String,String> params=new HashMap<String,String>();
                params.put("type","2");
                params.put("cid",cidvalue);
                params.put("name",name);
                params.put("date",date);
                params.put("total",paidamnt);
                params.put("ref",reference);
                return  params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(stringRequest, tag_string_req);

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }



    public void parseData(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);

        if (jsonObject.getString("error").equals("false")) {
            JSONArray dataArray = jsonObject.getJSONArray("clist");
            for (int i = 0; i < dataArray.length(); i++) {

                JSONObject dataobj = dataArray.getJSONObject(i);
                nameval = dataobj.getString("name");
                dateval = dataobj.getString("date");
                paidamntval = dataobj.getString("paidamount");
                refval = dataobj.getString("reference");


            }

//                    Toast.makeText(getContext(), "saved successfully", Toast.LENGTH_LONG).show();
//                    if(jsonObject.getString("error").equals("false")){
//                        ViewPager viewPager = (ViewPager) getActivity().findViewById(
//                                R.id.viewpager);
//                        viewPager.setCurrentItem(1);
//                    }



//                    Fragment someFragment = new ViewList();
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
//                    transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                    transaction.commit();

        }

//                else if(jsonObject.getString("error").equals("true")){
//                    Toast.makeText(getContext(),"check for error",Toast.LENGTH_LONG).show();
//                }

    }

    public void getAdnewVal() {

        if (sessionManager != null) {

            if (sessionManager.getValue("LoginDetails") != null) {
                final DetailsPojo detailsPojo = (DetailsPojo) new Gson().fromJson(sessionManager.getValue("LoginDetails"), new TypeToken<DetailsPojo>() {
                }.getRawType());
                if (detailsPojo != null) {
                    if (detailsPojo.getData() != null) {

                        if (detailsPojo.getData().getClist() != null) {
                            if (detailsPojo.getData().getClist().size() > 0) {
                                System.out.println("getClistSize" + detailsPojo.getData().getClist().size());
                                System.out.println("san11" + detailsPojo.getData().getClist().get(0).getName());

                                adnewNameres.clear();
                                System.out.println("san55" + adnewNameres);

                                mCist = detailsPojo.getData().getClist();


                                for ( i = 0; i < detailsPojo.getData().getClist().size(); i++) {
                                    adnewNameres.add(detailsPojo.getData().getClist().get(i).getName());
//                                    adnewNameres.add(detailsPojo.getData().getClist().get(i).getId());

//                                   String idfordata= String.valueOf(adnewNameres.add(detailsPojo.getData().getClist().get(i).getId()));
//                                   System.out.println("checkpos"+idfordata);
                                    System.out.println("san41" + adnewNameres);
//                                    arr=new String[detailsPojo.getData().getClist().get(i).getName()];

                                }
                                System.out.println("position of names" +i);
                                System.out.println("san41111112121212" + adnewNameres);
//
//                            ArrayAdapter<String>adapter_name=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,nameresponse);
//                            adnewName.setAdapter(adapter_name);
                                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, adnewNameres);
                                adnewname.setAdapter(adapter1);

//                                adnewname.setOnTouchListener(new View.OnTouchListener() {
//                                    @SuppressLint("ClickableViewAccessibility")
//                                    @Override
//                                    public boolean onTouch(View view, MotionEvent motionEvent) {
//                                        if (detailsPojo.getData().getClist().size() > 0) {
//
////                                               adapter1.getFilter().filter(null);
//                                            System.out.println("dropdown inside new collection" + adnewname);
//                                            adnewname.showDropDown();
//
//                                        }
//                                        return false;
//                                    }
//
//
//
//
//
//                                });

                                adnewname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                                        String txt = adapterView.getItemAtPosition(i).toString();
                                        for (Clist clist :mCist){

                                            if (clist.getName().contains(txt))
                                            {
                                                value=clist.getId();
                                                cidvalue=clist.getCid();
                                                System.out.println("cidvalue check"+idvalue);

                                                Toast.makeText(getActivity(), " Data" +
                                                                clist.getName() + " -" +
                                                                clist.getId() + " -" +
                                                                clist.getCid() + " -"

                                                        , Toast.LENGTH_SHORT).show();


                                                Log.d(TAG, "onItemClick: "+

                                                                clist.getName() + " -" +
                                                                clist.getId() + " -" +
                                                                clist.getAddress() + " -" +
                                                                clist.getCid() + " -"

                                                );
                                            }

//


                                        }

                                    }
                                });



//                                arr=detailsPojo.getData().getClist().toArray(new String[detailsPojo.getData().getClist().size()]);
//                                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), layout.custom_list_item,R.id.text_view_list_item, responseList);
//                                nameSpinner.setAdapter(adapter1);


                            }

                        }
                    }
                }
            }

        }


    }
}
