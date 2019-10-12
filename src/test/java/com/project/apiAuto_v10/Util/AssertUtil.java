package com.project.apiAuto_v10.Util;

import org.testng.Assert;

public class AssertUtil {
    /***
     * 断言比较结果
     * @param actualResponseData
     * @param expectedResponseData
     */
    public static String assertEquals(String actualResponseData, String expectedResponseData) {
        String passMsg = "通过";
        try{
            Assert.assertEquals(actualResponseData,expectedResponseData);
            //System.out.println("通过"+actualResponseData);

        }catch (Throwable e){
            passMsg = actualResponseData;
            //System.out.println("不通过");
        }
        return passMsg;

    }
}
