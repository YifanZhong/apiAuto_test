package com.project.apiAuto_v3;

import com.alibaba.fastjson.JSONObject;
import com.project.apiAuto_v2.ExcelUtil_v5;
import com.project.apiAuto_v2.HttpUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RegisterCase_v6 {

    @Test(dataProvider = "testdata")
    public void Test1(String parameters) { //{"mobilephone":"13344445555","pwd":""}
        String url = "http://47.107.166.132:8080/futureloan/mvc/api/member/register";

        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameters);
        System.out.println(HttpUtil.doPost(url, params));
    }


    @DataProvider
    public Object[][] testdata(){
        int[] rows = {2,3,4,5,6,7};
        int[] cells = {6};
        Object [][] testdata = ExcelUtil_v6.testdata("src/test/resources/TestCase_v1.xlsx",rows, cells);
        return testdata;
    }

}