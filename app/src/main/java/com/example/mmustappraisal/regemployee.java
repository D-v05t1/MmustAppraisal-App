package com.example.mmustappraisal;

public class regemployee {

    public  String usermail;
    public  String passwd;
    public  String confirmpasswd;
    public  String username;
    public  String number;
    public String getConfirmpasswd() {
        return confirmpasswd;
    }

    public void setConfirmpasswd(String confirmpasswd) {
        this.confirmpasswd = confirmpasswd;
    }



    public regemployee() {

    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
