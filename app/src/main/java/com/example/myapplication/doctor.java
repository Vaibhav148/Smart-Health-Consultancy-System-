package com.example.myapplication;

public class doctor {

    public String dname;
    public String demail;
    public String duid;
    public String dpno;
    public String daddr;
    public String dspec;
    public String dqual;
    public String bio;
   // int imageid;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public doctor() {
    }

    public doctor(String name, String email, String puid) {
        this.dname = name;
        this.demail = email;
        this.duid = puid;
    }

    public doctor(String puid, String name, String email, String ppno, String paddr, String dspec, String dqual,String bio) {
        this.dname = name;
        this.demail = email;
        this.duid = puid;
        this.dpno = ppno;
        this.daddr = paddr;
        this.dqual = dqual;
        this.dspec = dspec;
        this.bio=bio;
    }

    public doctor(String name, String email, String ppno, String paddr, String dspec, String dqual,String bio) {

        this.dname = name;
        this.demail = email;
        this.dpno = ppno;
        this.daddr = paddr;
        this.dqual = dqual;
        this.dspec = dspec;
        this.bio=bio;

    }

    public void setDocDname(String name) {
        this.dname = name;
    }

    public void setDocEmail(String email) {
        this.demail = email;
    }

    public void setDocDuid(String puid) {
        this.duid = puid;
    }

    public void setDocDpno(String ppno) {
        this.dpno = ppno;
    }

    public void setDocAddr(String paddr) {
        this.daddr = paddr;
    }

    public void setDocSpec(String paddr) {
        this.dspec = paddr;
    }

    public void setDocQual(String paddr) {
        this.dqual = paddr;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getBio() {
        return bio;

    }

    public String getDocName() {
        return dname;

    }

    public String getDocSpec() {
        return dspec;

    }


    public String getDocQual() {
        return dqual;

    }

    public String getDocEmail() {
        return demail;

    }

    public String getDocDuid() {
        return duid;

    }

    public String getDocdpno() {
        return dpno;

    }

    public String getDocAddr() {
        return daddr;

    }
}
