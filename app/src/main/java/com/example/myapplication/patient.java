package com.example.myapplication;

public class patient {
    public String pname;
    public String pemail;
    public String puid;
    public String ppno;
    public String paddr;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public patient() {
    }

    public patient(String name, String email,String puid) {
        this.pname = name;
        this.pemail = email;
        this.puid=puid;
    }
    public patient(String puid,String name, String email,String ppno,String paddr) {
        this.pname = name;
        this.pemail = email;
        this.puid=puid;
        this.ppno=ppno;
        this.paddr=paddr;
    }
    public patient(String name, String email,String ppno,String paddr) {

        this.pname = name;
        this.pemail = email;
        this.ppno=ppno;
        this.paddr=paddr;

    }
    public void setPatName(String name){
        this.pname=name;
    }
    public void setPatEmail(String email){
        this.pemail=email;
    }
    public void setPatPuid(String puid){
        this.puid=puid;
    }
    public void setPatPpno(String ppno){
        this.ppno=ppno;
    }
    public void setPatPaddr(String paddr){
        this.paddr=paddr;
    }
    public String getPatName(){
        return pname;

    }
    public String getPatEmail(){
        return pemail;

    }
    public String getPatPuid(){
        return puid;

    }
    public String getPatPpno(){
        return ppno;

    }
    public String getPatPaddr(){
        return paddr;

    }

}
