package com.example.dth_project.PojoPack;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewListdetails {

    @SerializedName("clistt")
    @Expose
    private List<Clistt> clistt = null;

    public List<Clistt> getClistt() {
        return clistt;
    }

    public void setClistt(List<Clistt> clistt) {
        this.clistt = clistt;
    }

}