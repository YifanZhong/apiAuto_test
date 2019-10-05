package com.project.apiAuto_v7;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginCase_v9 {

    @Test(dataProvider = "testdata")
    public void Test1(String caseId, String apiId, String parameters) {
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
        ExcelUtil_v9.writeBackData("src/test/resources/TestCase_v7.xlsx", "Case2", caseId, "ActualResponseData", result);


    }


    @DataProvider
    public Object[][] testdata(){
        String [] cellNames = {"CaseId","ApiId","Params"};
        Object [][] testdata = CaseUtil.getCaseDatasByApiId("2",cellNames);
        return testdata;
    }
}
