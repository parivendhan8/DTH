package com.example.dth_project.PojoPack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsPojo {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private Data data;



    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
//

}
//package com.example.dth_project;
//
//import com.android.volley.toolbox.StringRequest;
//
//public class DetailsPojo {
//
//    private String area;
//
//    private String address;
//
//    private String code;
//
//    private String dtime;
//
//    private String del;
//    private String cgroup;
//
//    private String act;
//
//    private String phone;
//
//    private String name;
//
//    private String id;
//
//    private String aid;
//
//    private String cid;
//
//    public String getArea ()
//    {
//        return area;
//    }
//
//    public void setArea (String area)
//    {
//        this.area = area;
//    }
//
//
//    public String getAddress ()
//    {
//        return address;
//    }
//
//    public void setAddress (String address)
//    {
//        this.address = address;
//    }
//
//    public String getCode ()
//    {
//        return code;
//    }
//
//    public void setCode (String code)
//    {
//        this.code = code;
//    }
//
//    public String getDtime ()
//    {
//        return dtime;
//    }
//
//    public void setDtime (String dtime)
//    {
//        this.dtime = dtime;
//    }
//
//    public String getDel ()
//{
//    return del;
//}
//
//    public void setDel (String del)
//    {
//        this.del = del;
//    }
//
//    public String getCgroup ()
//    {
//        return cgroup;
//    }
//
//    public void setCgroup (String cgroup)
//    {
//        this.cgroup = cgroup;
//    }
//
//
//    public String getAct ()
//{
//    return act;
//}
//
//    public void setAct (String act)
//    {
//        this.act = act;
//    }
//
//
//
//
//
//    public String getPhone ()
//    {
//        return phone;
//    }
//
//    public void setPhone (String phone)
//    {
//        this.phone = phone;
//    }
//
//
//
//
//
//
//
//
//
//    public String getName ()
//    {
//        return name;
//    }
//
//    public void setName (String name)
//    {
//        this.name = name;
//    }
//
//
//
//
//
//
//
//
//    public String getId ()
//    {
//        return id;
//    }
//
//    public void setId (String id)
//    {
//        this.id = id;
//    }
//
//    public String getAid ()
//{
//    return aid;
//}
//
//    public void setAid (String aid)
//    {
//        this.aid = aid;
//    }
//
//    public String getCid ()
//    {
//        return cid;
//    }
//
//    public void setCid (String cid)
//    {
//        this.cid = cid;
//    }
//
//
//
//
//    @Override
//    public String toString()
//    {
//        return "ClassPojo [area = "+area+", 11 = "+11+", address = "+address+", code = "+code+", dtime = "+dtime+", del = "+del+", cgroup = "+cgroup+", 0 = "+0+", 1 = "+1+", 2 = "+2+", 3 = "+3+", act = "+act+", 4 = "+4+", phone = "+phone+", 5 = "+5+", 6 = "+6+", 7 = "+7+", name = "+name+", 8 = "+8+", 9 = "+9+", id = "+id+", aid = "+aid+", cid = "+cid+", 10 = "+10+"]";
//    }
//}
