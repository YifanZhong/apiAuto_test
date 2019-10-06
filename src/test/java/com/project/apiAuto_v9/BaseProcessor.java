package com.project.apiAuto_v9;


import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * 接口测试统一处理类
 */
public class BaseProcessor {

    @Test(dataProvider = "testdata")
    public void Test1(String caseId, String apiId, String parameters) {
        //url
        String url = RestUtil.getUrlByApiId(apiId);
        //type
        String type = RestUtil.getTypeByApiId(apiId);
        //所需参数
        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameters);
        //调用service方法，完成接口调用，拿到接口响应response
        String result = HttpUtil.doService(url,type,params);
        /**
         if ("POST".equalsIgnoreCase(type)){
         HttpUtil.doPost(url, params);
         }else if ("GET".equalsIgnoreCase(type)){
         HttpUtil.doGet(url,params);
         }
         **/
        System.out.println(result);
        //根据caseId回写Excel用例数据
        //ExcelUtil_v10.writeBackData("src/test/resources/TestCase_v8.xlsx", "Case2", caseId, "ActualResponseData", result);

        //保存回写数据对象
        WriteBackData writeBackData = new WriteBackData("Case2",caseId,"ActualResponseData", result);
        ExcelUtil_v10.writeBackData.add(writeBackData);
    }

    @AfterSuite
    public void batchWriteBackData(){
        ExcelUtil_v10.batchWhiteBackData("src/test/resources/TestCase_v9.xlsx");
    }
}
