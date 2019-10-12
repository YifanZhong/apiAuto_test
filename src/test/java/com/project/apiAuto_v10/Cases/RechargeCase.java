package com.project.apiAuto_v10.Cases;

import com.project.apiAuto_v10.Util.CaseUtil;
import org.testng.annotations.DataProvider;

public class RechargeCase extends BaseProcessor {

    @DataProvider
    public Object[][] testdata(){
        //String [] cellNames = {"CaseId","ApiId","Params","ExpectedResponseData","PreValidateSql","AfterValidateSql"};
        Object [][] testdata = CaseUtil.getCaseDatasByApiId("3",cellNames);
        return testdata;
    }
}
