package com.utsavrajvir.arham;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Contacts  {

    private String mcid,mname,mtime,id;

    public Contacts(String m_Cid, String m_Name, String m_Time, String id) {
        mcid = m_Cid;
        mname = m_Name;
        mtime = m_Time;
        this.id = id;
    }


    public Contacts(String m_Id, String m_Name, String m_Time) {
        mcid = m_Id;
        mname = m_Name;
        mtime = m_Time;
    }




    public String getMcid() {
        return mcid;
    }

    public void setMcid(String mcid) {
        this.mcid = mcid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
