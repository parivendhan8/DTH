package com.example.dth_project;

class ViewListModal {
    private String name;
    private String date;
    private int paidamnt;
    private int reference;

    public ViewListModal(String name, String date, int paidamnt, int reference) {
        this.name = name;
        this.date = date;
        this.paidamnt = paidamnt;
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String custname) {
        this.name = custname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String viewdate) {
        this.date = viewdate;
    }

    public int getPaidamnt() {
        return paidamnt;
    }

    public void setPaidamnt(int paidamnt) {
        this.paidamnt = paidamnt;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference){
      this.reference=reference;
    }
}
