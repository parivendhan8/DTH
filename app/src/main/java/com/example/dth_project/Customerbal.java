package com.example.dth_project;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dth_project.PojoPack.Clist;
import com.example.dth_project.PojoPack.DetailsPojo;

import com.example.dth_project.helper.SessionManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import static com.example.dth_project.R.*;

public class Customerbal extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "Customerbal";
AutoCompleteTextView nameSpinner;
    RecyclerView recyclerView;
    AutoCompleteTextView areaspinner;
    Button searchbtn;
    ScrollView scrollView;
    SessionManager sessionManager;
    List<Clist> objClist=new ArrayList<>();
    List<String> area_response = new ArrayList<String>();
String[] arr;
    List<String> responseList = new ArrayList<String>();
//    String main_data = "";
//    String[] arr={"hi","hekjfkhsa","as"};
    private JSONObject JsonObject;

//    private static final String[] arr = Clist.toArray(new String[Clist().size()]);
    @SuppressLint("ClickableViewAccessibility")
    private void getCustBal() {
        if(sessionManager!=null){

            if(sessionManager.getValue("LoginDetails")!=null){
                final DetailsPojo detailsPojo= (DetailsPojo) new Gson().fromJson(sessionManager.getValue("LoginDetails"),new TypeToken<DetailsPojo>(){}.getRawType());
                if(detailsPojo!=null){
                    if(detailsPojo.getData()!=null) {
                        if (detailsPojo.getData().getClist()!=null){
                            if (detailsPojo.getData().getClist().size()>0){
                                System.out.println("getClistSize"+detailsPojo.getData().getClist().size());
                                System.out.println("san"+detailsPojo.getData().getClist().get(0).getName());
                                for(int i =0;i<detailsPojo.getData().getClist().size();i++){
                                    System.out.println("san"+detailsPojo.getData().getClist().get(i).getName());

                                    responseList.add(detailsPojo.getData().getClist().get(i).getName());
                                    System.out.println("san4"+responseList);
//                                    arr=new String[detailsPojo.getData().getClist().get(i).getName()];
                                    System.out.println("san3");
                                }
                                System.out.println("san4"+responseList);
                                final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                                        android.R.layout.simple_dropdown_item_1line, responseList);
                                nameSpinner.setAdapter(adapter1);
                                nameSpinner.setOnTouchListener(new View.OnTouchListener() {
                                    @SuppressLint("ClickableViewAccessibility")
                                    @Override
                                    public boolean onTouch(View view, MotionEvent motionEvent) {
                                       if(detailsPojo.getData().getClist().size()>0){

//                                               adapter1.getFilter().filter(null);
                                           System.out.println("dropdown inside"+nameSpinner);
                                               nameSpinner.showDropDown();

                                       }



                                        return false;
                                    }


//                                    @Override
//                                    public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
//                                        if (usernameLists.size() > 0) {
//                                            // show all suggestions
//                                            if (!etUsername.getText().toString().equals(""))
//                                                adapter.getFilter().filter(null);
//                                            etUsername.showDropDown();
//                                        }
//                                        return false;
//                                    }
                                });

                              for(int i1=0;i1<detailsPojo.getData().getClist().size();i1++){
                                  System.out.println("san"+detailsPojo.getData().getClist().get(i1).getArea());
                                  area_response.add(detailsPojo.getData().getClist().get(i1).getArea());
                                  System.out.println("san5"+area_response);

                              }

                                ArrayAdapter<String>adapter_area=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,area_response);
                                areaspinner.setAdapter(adapter_area);
                                areaspinner.setOnTouchListener(new View.OnTouchListener() {
                                    @SuppressLint("ClickableViewAccessibility")
                                    @Override
                                    public boolean onTouch(View view, MotionEvent motionEvent) {
                                        if(detailsPojo.getData().getClist().size()>0){
//                                               adapter1.getFilter().filter(null);
                                            System.out.println("dropdown inside"+areaspinner);
                                            areaspinner.showDropDown();
                                        }
                                        return false;
                                    }
                                });

//                                arr=detailsPojo.getData().getClist().toArray(new String[detailsPojo.getData().getClist().size()]);
//                                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), layout.custom_list_item,R.id.text_view_list_item, responseList);
//                                nameSpinner.setAdapter(adapter1);
                                objClist.addAll(detailsPojo.getData().getClist());
                                TableViewAdapter adapter = new TableViewAdapter(objClist);
                                recyclerView.setAdapter(adapter);

                            }

                        }
                    }
                }
            }

        }
        System.out.println("ARRAY_VALUE"+new Gson().toJson(arr));
    }


    private List<MovieModal> getMovieList() {
        List<MovieModal> movieList = new ArrayList<>();
        // src Wikipedia
        for(int i=0;i<=20;i++) {
            movieList.add(new MovieModal("abc", "aaaaaaaaaaaaaaaaa", 1223345677, 378));

//            movieList.add(new MovieModal(9, "Star Wars: The Last Jedi", 2017, 263));
//            movieList.add(new MovieModal(9, "Star Wars: The Last Jedi", 2017, 263));
        }


        return movieList;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(layout.activity_customerbal, container, false);
        recyclerView = (RecyclerView)view.findViewById(id.recyclerViewDeliveryProductList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        sessionManager=new SessionManager(getActivity());
        Button printbtn=(Button)view.findViewById(id.printid);
        printbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"print button clicked",Toast.LENGTH_LONG).show();
            }
        });

        nameSpinner=(AutoCompleteTextView)view.findViewById(id.actv);

        areaspinner=(AutoCompleteTextView)view.findViewById(id.cust_area_spin);
        searchbtn=(Button)view.findViewById(id.searchidbalanc);
        getCustBal();
        return view;
    }

//        main_data = sessionManager.getValue("LoginDetails");
//    arr = new String[]{main_data};


//        main_data = sessionManager.getValue("UserDeta");
//        arr = new String[]{main_data};



//        DetailsPojo detailsPojo= (DetailsPojo) new Gson().fromJson(sessionManager.getValue("LoginDetails"),new TypeToken<DetailsPojo>(){}.getRawType());

//        String[] arr=detailsPojo.getData().getClist().toArray(new String[detailsPojo.getData().getClist().indexOf(2)]);








//String arr=detailspojo.getdata.h=getclisr.toarray(new String [detailspojo].getdata.getClist.size()]);








//        namespinner=(AutoCompleteTextView)view.findViewById(id.actv);
////        namespinner.setAdapter(new SuggestionAdapter(getActivity(),namespinner.getText().toString()));
//     ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), layout.custom_list_item, id.text_view_list_item, arr);
//        namespinner.setAdapter(adapter);
//        namespinner.setAdapter(NameAdapter);

    private Context setName() {
        return null;
    }


    private String getId(String name) {
        return name;
    }

    private void getvalue(String name) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
