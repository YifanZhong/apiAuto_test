package com.project.apiAuto_v4;

public class RegisterParam {

    private String mobilephone;

    private String pwd;

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public RegisterParam(String mobilephone, String pwd) {
        this.mobilephone = mobilephone;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "{" +
                "\"mobilephone\":" +'\"' + mobilephone + '\"' +
                ", \"pwd\":" +'\"'+ pwd + '\"' +
                '}';
    }
}
