package com.example.laundry;

public class dade {
     String orderID;
     String totalprice;
     String totalquantity,date,time,service;

public dade(){}

    public dade(String artistId, String totalprice, String totalquantity, String date, String time, String service) {
        this.orderID = artistId;
        this.totalprice = totalprice;
        this.totalquantity = totalquantity;
        this.date=date;
        this.time=time;
        this.service=service;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getTotalprice() {
        return totalprice;
    }

   public String getTotalquantity() {
        return totalquantity;

    }
    public String getDate(){
        return date;
    }
   public String getTime(){
        return time;
    }
    public String getService()
    {
        return service;
    }
}