package com.project.apiAuto_v7;

import java.util.ArrayList;
import java.util.List;

public class RestUtil {

    public static List<Rest> rests = new ArrayList<Rest>();
    //保证下面获取信息时，rests已经存在数据
    static {
        ExcelUtil_v9.load("src/test/resources/TestCase_v3.xlsx","ApiInfoSheet", Rest.class);
    }


    /***
     * 根据接口编号获取接口地址
     * @param apiId
     * @return
     */
    public static String getUrlByApiId(String apiId) {

        for (Rest rest: rests){
            if (rest.getApiId().equals(apiId)){
                return rest.getUrl();
            }
        }
        return "";
    }



    public static String getTypeByApiId(String apiId) {
        for (Rest rest: rests){
            if (rest.getApiId().equals(apiId)){
                return rest.getType();
            }
        }
        return "";
    }



    public static void main(String[] args){
        for (Rest rest : rests){
            System.out.println(rest);
        }

    }


}
