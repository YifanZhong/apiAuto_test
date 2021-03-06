package com.project.apiAuto_v9.Cases;


import com.alibaba.fastjson.JSONObject;
import com.project.apiAuto_v9.POJO.WriteBackData;
import com.project.apiAuto_v9.Util.AssertUtil;
import com.project.apiAuto_v9.Util.ExcelUtil_v10;
import com.project.apiAuto_v9.Util.HttpUtil;
import com.project.apiAuto_v9.Util.RestUtil;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * 接口测试统一处理类
 */
public class BaseProcessor {

    @Test(dataProvider = "testdata")
    public void Test1(String caseId, String apiId, String parameters, String expectedResponseData) {
        //url
        String url = RestUtil.getUrlByApiId(apiId);
        //type
        String type = RestUtil.getTypeByApiId(apiId);
        //所需参数
        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameters);
        //调用service方法，完成接口调用，拿到接口响应response
        String actualResponseData = HttpUtil.doService(url,type,params);
        /**
         if ("POST".equalsIgnoreCase(type)){
         HttpUtil.doPost(url, params);
         }else if ("GET".equalsIgnoreCase(type)){
         HttpUtil.doGet(url,params);
         }
         **/
        //System.out.println(actualResponseData);
        //根据caseId回写Excel用例数据
        //ExcelUtil_v10.writeBackData("src/test/resources/TestCase_v8.xlsx", "Case2", caseId, "ActualResponseData", result);

        actualResponseData = AssertUtil.assertEquals(actualResponseData, expectedResponseData);
        //保存回写数据对象
        WriteBackData writeBackData = new WriteBackData("Case2",caseId,"ActualResponseData", actualResponseData);
        ExcelUtil_v10.writeBackData.add(writeBackData);
    }

    @AfterSuite
    public void batchWriteBackData(){

        ExcelUtil_v10.batchWhiteBackData("src/test/resources/TestCase_v9.xlsx");
    }
}
