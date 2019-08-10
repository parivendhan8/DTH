package com.example.dth_project.PojoPack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clistt {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("aid")
    @Expose
    private Object aid;
    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("ref")
    @Expose
    private String ref;
    @SerializedName("del")
    @Expose
    private Object del;
    @SerializedName("act")
    @Expose
    private Object act;
    @SerializedName("dtime")
    @Expose
    private String dtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getAid() {
        return aid;
    }

    public void setAid(Object aid) {
        this.aid = aid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Object getDel() {
        return del;
    }

    public void setDel(Object del) {
        this.del = del;
    }

    public Object getAct() {
        return act;
    }

    public void setAct(Object act) {
        this.act = act;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }


}
