package com.example.myapplication;

public class admin {
    public String anme;
    public String aid;
    public String aemail;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public admin() {
    }

    public admin(String name, String ppno) {
        this.anme = name;
        this.aemail = ppno;

    }

    public void setAnme(String name){
        this.anme=name;
    }
    public void setAemail(String email){
        this.aemail=email;
    }

    public String getAnme(){
        return anme;

    }
    public String getAemail(){
        return aemail;

    }

}
