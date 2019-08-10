package com.example.dth_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dth_project.PojoPack.DetailsPojo;
import com.example.dth_project.helper.SessionManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Statement extends Fragment implements AdapterView.OnItemSelectedListener {
EditText tv;
    EditText txtDate,txtToDate;
    Button search_btn;
    AutoCompleteTextView adnewNames;

    List<String> nameresponse12 = new ArrayList<String>();

//    String[] nameresponse12={"hi","oh","a"};
    SessionManager sessionManager;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("helllo");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("checkno1");
        View view= inflater.inflate(R.layout.activity_statement, container, false);
        System.out.println("check2");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        System.out.println("check3");
        SessionManager sessionManager = new SessionManager(getActivity());
        System.out.println("check4");
        adnewNames = (AutoCompleteTextView) view.findViewById(R.id.new_col_name_state);


//        Button t1=(Button)view.findViewById(R.id.search_id);
//        tv=(EditText) view.findViewById(R.id.todate);
//        t1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(getActivity(),Sample.class);
//                startActivity(i);
//            }
//        });
        search_btn=(Button)view.findViewById(R.id.search_id);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"From Date="+txtDate.getText()+"To Date"+txtToDate.getText(),Toast.LENGTH_LONG).show();
            }
        });
        System.out.println("here");
        txtDate=(EditText)view.findViewById(R.id.fromdate);
        txtToDate=(EditText)view.findViewById(R.id.todate);
//        txtDate.setOnClickListener((View.OnClickListener) getActivity());
        txtToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                System.out.println("helllel");
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtToDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
            }
        });
        System.out.println("method call");
        getstatementvalue();
        return view;

    }


    public void getstatementvalue() {
        if(sessionManager!=null) {
            System.out.println("checkforerror");
        if (sessionManager.getValue("LoginDetails") != null) {
            final DetailsPojo detailsPojo = (DetailsPojo) new Gson().fromJson(sessionManager.getValue("LoginDetails"), new TypeToken<DetailsPojo>() {}.getRawType());
            if (detailsPojo != null) {
                if (detailsPojo.getData() != null) {
                    if (detailsPojo.getData().getClist() != null) {
                        if (detailsPojo.getData().getClist().size() > 0) {
                            System.out.println("getClistSize" + detailsPojo.getData().getClist().size());
                            System.out.println("san112" + detailsPojo.getData().getClist().get(0).getName());
                            for (int i = 0; i < detailsPojo.getData().getClist().size(); i++) {
                                nameresponse12.add(detailsPojo.getData().getClist().get(i).getName());

                            }
                            System.out.println("san100" + nameresponse12);


                            final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, nameresponse12);
                            adnewNames.setAdapter(adapter1);
                            adnewNames.setThreshold(1);
                            adnewNames.setOnTouchListener(new View.OnTouchListener() {
                                @SuppressLint("ClickableViewAccessibility")
                                @Override
                                public boolean onTouch(View view, MotionEvent motionEvent) {
                                    if (detailsPojo.getData().getClist().size()> 0) {

                                               adapter1.getFilter().filter(null);
                                        System.out.println("ahteedropdown inside new collection1" + adnewNames);
                                        adnewNames.showDropDown();

                                    }
                                    return false;
                                }


//
                            });
                        }
                    }
                }
            }
        }
    }
                        }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


                    }