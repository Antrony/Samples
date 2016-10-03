package com.next.example.sample.bal;

/**
 * Created by next on 29/9/16.
 */
public class Bean {
    String fName;
    String lName;
    String team;
    String uName;
    public Bean(String fName,String lName,String team,String uName){
        this.fName=fName;
        this.lName=lName;
        this.team=team;
        this.uName=uName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }


}
