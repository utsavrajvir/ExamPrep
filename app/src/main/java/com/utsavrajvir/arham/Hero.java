package com.utsavrajvir.arham;

public class Hero {

    private String stid;
    private String stname;
    private String stuname;
    private String stpwd;
    private String stmobileno;
    private String stisarhamstudent;
    private String stmobileverified;



    public Hero(String stid, String stname, String stuname, String stpwd, String stmobileno, String stisarhamstudent, String stmobileverified) {
        this.stid = stid;
        this.stname = stname;
        this.stuname = stuname;
        this.stpwd = stpwd;
        this.stmobileno = stmobileno;
        this.stisarhamstudent = stisarhamstudent;
        this.stmobileverified = stmobileverified;
    }

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStpwd() {
        return stpwd;
    }

    public void setStpwd(String stpwd) {
        this.stpwd = stpwd;
    }

    public String getStmobileno() {
        return stmobileno;
    }

    public void setStmobileno(String stmobileno) {
        this.stmobileno = stmobileno;
    }

    public String getStisarhamstudent() {
        return stisarhamstudent;
    }

    public void setStisarhamstudent(String stisarhamstudent) {
        this.stisarhamstudent = stisarhamstudent;
    }

    public String getStmobileverified() {
        return stmobileverified;
    }

    public void setStmobileverified(String stmobileverified) {
        this.stmobileverified = stmobileverified;
    }
}
