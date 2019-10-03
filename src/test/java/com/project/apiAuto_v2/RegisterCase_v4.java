package com.project.apiAuto_v2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RegisterCase_v4 {
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
        Object [][] testdata = ExcelUtil_v4.testdata("src/test/resources/TestCase_v1.xlsx",2,7,6,7);
        return testdata;
    }

}