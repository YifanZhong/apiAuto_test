package com.project.apiAuto_v10.Cases;


import com.alibaba.fastjson.JSONObject;
import com.project.apiAuto_v10.POJO.WriteBackData;
import com.project.apiAuto_v10.Util.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * 接口测试统一处理类
 */
public class BaseProcessor {

    public String [] cellNames = {"CaseId","ApiId","Params","ExpectedResponseData","PreValidateSql","AfterValidateSql"};

    @Test(dataProvider = "testdata")
    public void Test1(String caseId, String apiId, String parameters, String expectedResponseData, String preValidateSql, String afterValidateSql) {

        if (preValidateSql!=null&&preValidateSql.trim().length()>0){
            //在接口调用前查询我们想要验证的字段
            String prerValidateResult = DBCheckUtil.doQuery(preValidateSql);
            //查询到数据之后保存到excel里面去
            //WriteBackData writeBackData = new WriteBackData("CaseInfoSheet",caseId,"PrerValidateResult", prerValidateResult);
            ExcelUtil_v10.writeBackData.add(new WriteBackData("CaseInfoSheet",caseId,"PrerValidateResult", prerValidateResult));


        }

        //url
        String url = RestUtil.getUrlByApiId(apiId);
        //type
        String type = RestUtil.getTypeByApiId(apiId);
        //所需参数
        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameters);
        //调用service方法，完成接口调用，拿到接口响应response
        String actualResponseData = HttpUtil.doService(url,type,params);
        actualResponseData = AssertUtil.assertEquals(actualResponseData, expectedResponseData);
        //保存回写数据对象
        //WriteBackData writeBackData = new WriteBackData("CaseInfoSheet",caseId,"ActualResponseData", actualResponseData);
        ExcelUtil_v10.writeBackData.add(new WriteBackData("CaseInfoSheet",caseId,"ActualResponseData", actualResponseData));
        if (afterValidateSql!=null&&afterValidateSql.trim().length()>0){
            //接口调用后查询我们想要验证的字段
            String afterValidateResult = DBCheckUtil.doQuery(afterValidateSql);
            ExcelUtil_v10.writeBackData.add(new WriteBackData("CaseInfoSheet",caseId,"AfterValidateResult", afterValidateResult));

        }
    }

    @AfterSuite
    public void batchWriteBackData(){

        ExcelUtil_v10.batchWhiteBackData("src/test/resources/TestCase_v10.xlsx");
    }
}
