package com.project.apiAuto_v2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RegisterCase_v2 {
    //mobilephone:"13644445555", pwd:""
    //mobilephone:"", pwd:"123456"
    //mobilephone:"1364444", pwd:"123456"
    //mobilephone:"13644445555", pwd:"12345"
    //mobilephone:"13644445555", pwd:"123456"
    //mobilephone:"13644445555", pwd:"123456"


    @Test(dataProvider = "testdata")
    public void Test1(String mobilephone, String pwd) {
        String url = "http://47.107.166.132:8080/futureloan/mvc/api/member/register";
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobilephone", mobilephone);
        params.put("pwd", pwd);
        System.out.println(HttpUtil.doPost(url, params));
    }


    @DataProvider
    public Object[][] testdata(){
        Object [][] testdata = {
                {"13655554444",""},
                {"","123456"},
                {"1365555","123456"},
                {"13655554444","12345"},
                {"13655554444","123456"},
                {"13655554444","123456"},

        };
        return testdata;
    }

}