package com.example.dth_project;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;



public class Home extends Fragment implements View.OnClickListener {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton tt = (ImageButton) getView().findViewById(R.id.btnTimeTable);
        ImageButton atn = (ImageButton) getView().findViewById(R.id.btnAttenence);
        ImageButton exmSc = (ImageButton) getView().findViewById(R.id.btnExamScheule);
        ImageButton res = (ImageButton) getView().findViewById(R.id.btnResults);

        atn.setOnClickListener(this);
        exmSc.setOnClickListener(this);
        res.setOnClickListener(this);
        tt.setOnClickListener(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_home, container, false);

    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.btnTimeTable:
                fragment = new Receipt();
                break;
            case R.id.btnExamScheule:
                fragment = new Customerbal();
                break;
            case R.id.btnResults:
                fragment = new Statement();
                break;
            case R.id.btnAttenence:
                fragment = new Receipt();
                break;
        }
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();
    }
}