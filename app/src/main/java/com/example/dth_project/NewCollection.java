package com.example.dth_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.example.dth_project.PojoPack.DetailsPojo;
import com.example.dth_project.helper.SessionManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

public class NewCollection extends Fragment implements AdapterView.OnItemSelectedListener {

    List<String> adnewnameres=new ArrayList<>();

    AutoCompleteTextView adnewName;
    SessionManager sessionManager;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.activity_new_collection, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        sessionManager=new SessionManager(getActivity());
        adnewName=(AutoCompleteTextView) view.findViewById(R.id.new_col_name);
        getcustvalue();
        return view;

    }

    private void getcustvalue() { if(sessionManager!=null){

        if(sessionManager.getValue("LoginDetails")!=null){
            final DetailsPojo detailsPojo= (DetailsPojo) new Gson().fromJson(sessionManager.getValue("LoginDetails"),new TypeToken<DetailsPojo>(){}.getRawType());
            if(detailsPojo!=null){
                if(detailsPojo.getData()!=null) {
                    if (detailsPojo.getData().getClist()!=null){
                        if (detailsPojo.getData().getClist().size()>0){
                            System.out.println("getClistSize"+detailsPojo.getData().getClist().size());
                            System.out.println("san11"+detailsPojo.getData().getClist().get(0).getName());
                            adnewnameres.clear();
                            System.out.println("san55"+adnewnameres);
                            for(int i =0;i<detailsPojo.getData().getClist().size();i++){
                              adnewnameres.add(detailsPojo.getData().getClist().get(i).getName());
                                System.out.println("san41"+adnewnameres);
//                                    arr=new String[detailsPojo.getData().getClist().get(i).getName()];

                            }
                            System.out.println("san41111112121212"+adnewnameres);
//
//                            ArrayAdapter<String>adapter_name=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,nameresponse);
//                            adnewName.setAdapter(adapter_name);
                            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item, adnewnameres);
                            adnewName.setAdapter(adapter1);
                            adnewName.setOnTouchListener(new View.OnTouchListener() {
                                @SuppressLint("ClickableViewAccessibility")
                                @Override
                                public boolean onTouch(View view, MotionEvent motionEvent) {
                                    if(detailsPojo.getData().getClist().size()>0){

//                                               adapter1.getFilter().filter(null);
                                        System.out.println("dropdown inside new collection"+adnewName);
                                        adnewName.showDropDown();

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
//                                arr=detailsPojo.getData().getClist().toArray(new String[detailsPojo.getData().getClist().size()]);
//                                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), layout.custom_list_item,R.id.text_view_list_item, responseList);
//                                nameSpinner.setAdapter(adapter1);


                        }

                    }
                }
            }
        }

    }
//        System.out.println("ARRAY_VALUE"+new Gson().toJson(arr));
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
