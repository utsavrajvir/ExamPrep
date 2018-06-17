package com.utsavrajvir.arham;

public class Contacts {

    private String M_Cid,M_Name,M_Time,id;

    public Contacts(String m_Cid, String m_Name, String m_Time, String id) {
        M_Cid = m_Cid;
        M_Name = m_Name;
        M_Time = m_Time;
        this.id = id;
    }


    public Contacts(String m_Id, String m_Name, String m_Time) {
        M_Cid = m_Id;
        M_Name = m_Name;
        M_Time = m_Time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Contacts(String m_Cid) {
        M_Name = m_Cid;
    }

    public String getM_Cid() {
        return M_Cid;
    }

    public void setM_Cid(String m_Id) {
        M_Cid = m_Id;
    }

    public String getM_Name() {
        return M_Name;
    }

    public void setM_Name(String m_Name) {
        M_Name = m_Name;
    }

    public String getM_Time() {
        return M_Time;
    }

    public void setM_Time(String m_Time) {
        M_Time = m_Time;
    }


}
