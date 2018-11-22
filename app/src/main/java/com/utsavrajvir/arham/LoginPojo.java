package com.utsavrajvir.arham;

public class LoginPojo {

    int stid;
    String stname;
    String stuname;
    String stemail;
    String stpwd;
    String stmobileno;
    String stisarhamstudent;
    String stmobileverified;

    public LoginPojo(int stid, String stname, String stuname, String stemail, String stpwd, String stmobileno, String stisarhamstudent, String stmobileverified) {
        this.stid = stid;
        this.stname = stname;
        this.stuname = stuname;
        this.stemail = stemail;
        this.stpwd = stpwd;
        this.stmobileno = stmobileno;
        this.stisarhamstudent = stisarhamstudent;
        this.stmobileverified = stmobileverified;
    }

    public int getStid() {
        return stid;
    }

    public void setStid(int stid) {
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

    public String getStemail() {
        return stemail;
    }

    public void setStemail(String stemail) {
        this.stemail = stemail;
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
