package com.project.apiAuto_v5;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class RegisterCase_v8 {

    @Test(dataProvider = "testdata")
    public void Test1(String apiIdFromCase, String parameters) {
        int [] rows = {2,3,4,5,6,7,8,9,10,11,12,13,14};
        int [] cells = {1,3,4};
        //接口地址
        Object[][] testdata = ExcelUtil_v8.testdata("src/test/resources/TestCase_v2.xlsx","ApiInfoSheet", rows, cells);
        String url = "";
        String type = "";
        //筛选所需数据
        for (Object[] objects: testdata) {
            String apiIdFromRest = objects[0].toString(); //拿到来自ApiInfoSheet的接口编号
            if(apiIdFromCase.equals(apiIdFromRest)){
                type = objects[1].toString();
                url = objects[2].toString();
                break;
            }

        }
        //所需参数

        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameters);

        /**
        if ("POST".equalsIgnoreCase(type)){
            HttpUtil.doPost(url, params);
        }else if ("GET".equalsIgnoreCase(type)){
            HttpUtil.doGet(url,params);
        }
         **/
        System.out.println(HttpUtil.doService(url,type,params));

    }


    @DataProvider
    public Object[][] testdata(){
        int[] rows = {2,3,4,5,6,7};
        int[] cells = {3,4};
        Object [][] testdata = ExcelUtil_v8.testdata("src/test/resources/TestCase_v2.xlsx","RegisterCase1",rows, cells);
        return testdata;
    }

}