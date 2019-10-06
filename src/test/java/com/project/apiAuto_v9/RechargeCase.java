package com.project.apiAuto_v9;

import org.testng.annotations.DataProvider;

public class RechargeCase extends BaseProcessor {

    @DataProvider
    public Object[][] testdata(){
        String [] cellNames = {"CaseId","ApiId","Params","ExpectedResponseData"};
        Object [][] testdata = CaseUtil.getCaseDatasByApiId("3",cellNames);
        return testdata;
    }
}
