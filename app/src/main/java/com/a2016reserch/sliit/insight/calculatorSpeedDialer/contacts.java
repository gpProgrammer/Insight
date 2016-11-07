package com.a2016reserch.sliit.insight.calculatorSpeedDialer;

/**
 * Created by Sandasala on 11/7/2016.
 */
public class contacts {

    private String pr_no;
    private String con_no;
    private String con_name;

    public contacts()
    {

    }
    public contacts(String Pr_no,String Con_no,String Con_name)
    {
        this.pr_no=Pr_no;
        this.con_no=Con_no;
        this.con_name=Con_name;

    }

    public contacts(String Con_no,String Con_name)
    {

        this.con_no=Con_no;
        this.con_name=Con_name;

    }

    public String getPr_no() {
        return pr_no;
    }

    public void setPr_no(String pr_no) {
        this.pr_no = pr_no;
    }

    public String getCon_no() {
        return con_no;
    }

    public void setCon_no(String con_no) {
        this.con_no = con_no;
    }

    public String getCon_name() {
        return con_name;
    }

    public void setCon_name(String con_name) {
        this.con_name = con_name;
    }
}
