package com.example.myapplication;

public class appointment {

    private int appid;
    private int ppid;
    private int ddid;
    private String date;
    appointment(){

    }
    appointment(int b,int c,String d){

        this.ppid=b;
        this.ddid=c;
        this.date=d;
    }
    appointment(int a,int b,int c,String d){

        this.ppid=b;
        this.ddid=c;
        this.date=d;
        this.appid=a;
    }
    public void setAppid(int ppid)
    {
        this.appid=ppid;
    }
public void setPpid(int ppid)
{
    this.ppid=ppid;
}
    public void setDdid(int ppid)
    {
        this.ddid=ppid;
    }
    public void setDate(String ppid)
    {
        this.date=ppid;
    }
    public int getPpid(){
        return ppid;
    }
    public int getAppid(){
        return appid;
    }
    public int getDdid(){
        return ddid;
    }
    public String getDate(){
        return date;
    }
}
