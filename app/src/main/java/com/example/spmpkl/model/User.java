package com.example.spmpkl.model;

import android.location.Location;

import java.util.Comparator;
import java.util.Date;

public class User {

    String id, date, time, loc, ket, zin;

    public User() {

    }

    public String getZin() {
        return zin;
    }

    public void setZin(String zin) {
        this.zin = zin;
    }

    public User(String Date, String Time, String Location, String Ket, String Izin) {
        this.date = Date;
        this.time = Time;
        this.loc = Location;
        this.ket = Ket;
        this.zin = Izin;
    }

   /*public static Comparator<User>UserDateAscendingComparator = new Comparator<User>() {
       @Override
       public int compare(User user, User t1) {
           return user.getDate() - t1.getDate();
       }
   };*/


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }
}



