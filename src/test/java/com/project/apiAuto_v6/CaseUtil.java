package com.project.apiAuto_v6;

import java.util.ArrayList;
import java.util.List;

public class CaseUtil {
    /***
     * 保存所有的用例对象(解析一次就放进内存，作为共享数据，所以设为static）
     */
    public static List<Case> cases = new ArrayList<Case>();

    static {//作用就是初始化，保证调用前就已经就位
        //将所有数据解析封装到cases
        ExcelUtil_v8.load("src/test/resources/TestCase_v3.xlsx","Case2");
    }
    /***
     * 根据接口编号拿对应接口的测试数据
     * @param apiId  指定接口编号
     * @param cellNames  要获取的数据对应的列名字
     * @return
     */
    public static Object [][] getCaseDatasByApiId(String apiId, String [] cellNames){
        //通过循环找出指定接口编号对应的这些用例数据
        for (Case cs: cases){

        }
        return null;
    }


    public static void main(String [] args){
        //load("src/test/resources/TestCase_v3.xlsx", "Case2");
        /**
         for (Case cs: CaseUtil.cases){
         System.out.println("!!!"+cs);
         }
         **/

    }

}
