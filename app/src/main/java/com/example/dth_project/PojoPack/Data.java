package com.example.dth_project.PojoPack;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("uqid")
    @Expose
    private String uqid;
    @SerializedName("uname")
    @Expose
    private String uname;
    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("rid")
    @Expose
    private String rid;
    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("com")
    @Expose
    private Com com;
    @SerializedName("clist")
    @Expose
    private List<Clist> clist = null;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUqid() {
        return uqid;
    }

    public void setUqid(String uqid) {
        this.uqid = uqid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Com getCom() {
        return com;
    }

    public void setCom(Com com) {
        this.com = com;
    }

    public List<Clist> getClist() {
        return clist;
    }

    public void setClist(List<Clist> clist) {
        this.clist = clist;
    }

}