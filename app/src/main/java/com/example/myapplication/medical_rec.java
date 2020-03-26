package com.example.myapplication;

public class medical_rec {
    private int mid;
    private int mpid;
    private int mdid;
    private String dis;
    private String sym;
    private String pres;
    medical_rec(){

    }
    medical_rec(int b,int c,String d,String e,String f){

        this.mpid=b;
        this.mdid=c;
        this.dis=d;
        this.sym=e;
        this.pres=f;
    }
    medical_rec(int a,int b,int c,String d,String e,String f){

        this.mpid=b;
        this.mdid=c;
        this.dis=d;
        this.sym=e;
        this.pres=f;
        this.mid=a;
    }

    public int getMid(){
        return mid;
    }
    public int getMdid(){
        return mdid;
    }
    public int getMpid(){
        return mpid;
    }

    public String getDis(){
        return dis;
    }

    public String getSym(){
        return sym;
    }

    public String  getPres(){
        return pres;
    }
    public void setMpid(int mid){
        this.mid=mid;
    }
    public void setMdid(int mid){
        this.mdid=mid;
    }
    public void setDis(String mid){
        this.dis=mid;
    }
    public void setSym(String mid){
        this.sym=mid;
    }
    public void setPres(String mid){
        this.pres=mid;
    }
}
