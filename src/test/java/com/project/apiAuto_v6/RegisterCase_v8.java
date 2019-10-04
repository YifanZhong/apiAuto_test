package com.project.apiAuto_v6;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class RegisterCase_v8 {

    @Test(dataProvider = "testdata")
    public void Test1(String apiId, String parameters) {
        //url
        String url = RestUtil.getUrlByApiId(apiId);
        //type
        String type = RestUtil.getTypeByApiId(apiId);
        //所需参数
        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameters);
        String result = HttpUtil.doService(url,type,params);
        /**
        if ("POST".equalsIgnoreCase(type)){
            HttpUtil.doPost(url, params);
        }else if ("GET".equalsIgnoreCase(type)){
            HttpUtil.doGet(url,params);
        }
         **/
        System.out.println(result);

    }


    @DataProvider
    public Object[][] testdata(){
        String [] cellNames = {"ApiId","Params"};
        Object [][] testdata = CaseUtil.getCaseDatasByApiId("1",cellNames);
        return testdata;
    }




}