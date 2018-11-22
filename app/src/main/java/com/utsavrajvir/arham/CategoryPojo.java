package com.utsavrajvir.arham;

import java.sql.Time;

public class CategoryPojo {

    int cid;
    String cname;
    int cmcid;
    Time ctime;

    public CategoryPojo(int cid, String cname, int cmcid, Time ctime) {
        this.cid = cid;
        this.cname = cname;
        this.cmcid = cmcid;
        this.ctime = ctime;
    }


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCmcid() {
        return cmcid;
    }

    public void setCmcid(int cmcid) {
        this.cmcid = cmcid;
    }

    public Time getCtime() {
        return ctime;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
    }

}
