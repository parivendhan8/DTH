package com.example.dth_project;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;

import java.util.List;



public class UserDetailsAdapter extends ArrayAdapter<UserDetails> {
    private static final String KEY_EMPLOYEE_ID = "uid: ";
    private static final String KEY_NAME = "uname: ";
    private static final String KEY_DOB = "cid: ";
    private static final String KEY_DESIGNATION = "rid: ";
//    private static final String KEY_CONTACT_NUMBER = "rip: ";
//    private static final String KEY_EMAIL = "Email: ";
//    private static final String KEY_SALARY = "Salary: ";
    private List<UserDetails> dataSet;

    public UserDetailsAdapter(List<UserDetails> dataSet, Context mContext) {
        super(mContext, R.layout.user_details_row, dataSet);
        this.dataSet = dataSet;
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.user_details_row, null);
        }
        UserDetails employee = dataSet.get(position);
        if (employee != null) {
            //Text View references
            TextView employeeId = (TextView) v.findViewById(R.id.employeeId);
            TextView name = (TextView) v.findViewById(R.id.name);
            TextView dob = (TextView) v.findViewById(R.id.dob);
            TextView designation = (TextView) v.findViewById(R.id.designation);
            TextView contactNumber = (TextView) v.findViewById(R.id.contact_number);
            TextView email = (TextView) v.findViewById(R.id.email);
            TextView salary = (TextView) v.findViewById(R.id.salary);

            //Updating the text views
            employeeId.setText(KEY_EMPLOYEE_ID + employee.getUid());
            name.setText(KEY_NAME + employee.getUname());
            dob.setText(KEY_DOB + employee.getUqid());
            designation.setText(KEY_DESIGNATION + employee.getCid());

//            email.setText(KEY_EMAIL + employee.getEmail());
//            salary.setText(KEY_SALARY + employee.getSalary().toString());
        }

        return v;
    }
}