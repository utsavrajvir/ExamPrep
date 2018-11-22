package com.utsavrajvir.arham;

public class SubCategoryPojo {

    int scid;
    String scname;
    String sctime;
    int sccid;

    public SubCategoryPojo(int scid, String scname, String sctime, int sccid) {
        this.scid = scid;
        this.scname = scname;
        this.sctime = sctime;
        this.sccid = sccid;
    }


    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public String getScname() {
        return scname;
    }

    public void setScname(String scname) {
        this.scname = scname;
    }

    public String getSctime() {
        return sctime;
    }

    public void setSctime(String sctime) {
        this.sctime = sctime;
    }

    public int getSccid() {
        return sccid;
    }

    public void setSccid(int sccid) {
        this.sccid = sccid;
    }
}
