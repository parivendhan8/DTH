package com.example.dth_project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dth_project.PojoPack.Clist;
import com.example.dth_project.PojoPack.Clistt;
import com.example.dth_project.PojoPack.DetailsPojo;
import com.example.dth_project.PojoPack.ViewListdetails;
import com.example.dth_project.helper.Functions;
import com.example.dth_project.helper.SessionManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.dth_project.SuggestionAdapter.TAG;

public class ViewList extends androidx.fragment.app.Fragment {
    List<Clistt> objClistt=new ArrayList<>();
    RecyclerView recyclerView;
    SessionManager sessionManager;
    View view;


    public List<ViewListModal> getReceiptList() {
        List<ViewListModal> vieww = new ArrayList<>();

        for (int i = 0; i <= 20; i++) {
            vieww.add(new ViewListModal("abc", "12 1 98", 122, 38));
        }
        return vieww;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.viewlist, container, false);
        sessionManager = new SessionManager(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_viewlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        System.out.println("evecheck2");
        System.out.println("session Check" +
                ""+sessionManager.getValue("ViewListDetails"));
         String tag_string_req = "req_addnew";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Functions.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponseView List: " + response);
                        System.out.println("res check"+response);
                        try {
                        JSONObject jObj = new JSONObject(response);
                            sessionManager.SavePref_String("ViewListDetails",jObj.toString());

                            System.out.println("rescheck"+sessionManager.getValue("ViewListDetails"));
                            getValueofList();

//                                progressDialog.dismiss();
//                                Toast.makeText(getActivity(),"res"+ response, Toast.LENGTH_LONG).show();


//
//                            sessionManager.SavePref_String("ViewListDetails", jObj.toString());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("type", "3");

                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(stringRequest, tag_string_req);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

        return view;
    }
//    @SuppressLint("ClickableViewAccessibility")
    public void getValueofList() {
        if (sessionManager != null) {

            final ViewListdetails viewListdetails = (ViewListdetails) new Gson().fromJson(sessionManager.getValue("ViewListDetails"), new TypeToken<ViewListdetails>() {
            }.getRawType());
            if (viewListdetails != null) {
                System.out.println("evecheck1");
                if (viewListdetails.getClistt() != null) {
                    if (viewListdetails.getClistt().size() > 0) {
                        objClistt.addAll(viewListdetails.getClistt());
//            ViewListdetails viewListdetails = (ViewListdetails) new Gson().fromJson(sessionManager.getValue("ViewListDetails"), ViewListdetails.class);
//                if(sessionManager.getValue("ViewListDetails")!=null){
                        Log.d(TAG, "getCustBal: " + sessionManager.getValue("ViewListdetails"));

//               objview.addAll(viewListdetails);
                        System.out.println("eve2");
//                   objview.add(ViewListdetails)

                        ViewListTableAdapter adapter = new ViewListTableAdapter(objClistt);


                        recyclerView.setAdapter(adapter);
                    }
                }


            }
        }
    }

}
