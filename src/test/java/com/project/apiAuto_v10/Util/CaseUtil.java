package com.project.apiAuto_v10.Util;

import com.project.apiAuto_v10.POJO.Case;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CaseUtil {
    /***
     * 保存所有的用例对象(解析一次就放进内存，作为共享数据，所以设为static）
     */
    public static List<Case> cases = new ArrayList<Case>();

    static {//作用就是初始化，保证调用前就已经就位
        //将所有数据解析封装到cases
        ExcelUtil_v10.load("src/test/resources/TestCase_v10.xlsx","CaseInfoSheet", Case.class);
    }
    /***
     * 根据接口编号拿对应接口的测试数据
     * @param apiId  指定接口编号
     * @param cellNames  要获取的数据对应的列名字
     * @return
     */
    public static Object [][] getCaseDatasByApiId(String apiId, String [] cellNames) {
        Class<Case> clazz = Case.class;
        //保存指定接口的case对象的集合
        ArrayList<Case> csList = new ArrayList<Case>();
        //通过循环找出指定接口编号对应的这些用例数据
        for (Case cs: cases){
            if (cs.getApiId().equals(apiId)){
                csList.add(cs);
            }

        }
        Object [][] testdata = new Object[csList.size()][cellNames.length];//准备二维数组，保存返回数据
        for (int i = 0; i < csList.size(); i++) {
            Case aCase = csList.get(i);
            //取指定的列
            for (int j = 0; j < cellNames.length; j++) {
                //用反射来做，要反射的方法
                String methodName = "get"+cellNames[j];
                Method method = null;
                try {
                    method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(aCase);
                    testdata[i][j] = value;
                } catch (Exception e) {
                    e.printStackTrace();
                }


                /**
                String cellName = cellNames[j];
                //这样不好
                String value = "";
                if (cellName.equals("CaseId")){
                    value = aCase.getCaseId();
                }else if (cellName.equals("ApiId")){
                    value = aCase.getApiId();
                }else if (cellName.equals("Desc")){
                    value = aCase.getDesc();
                }else if (cellName.equals("Params")){
                    value = aCase.getParams();
                }

                testdata[i][j] = value;
                 **/
            }

        }
        return testdata;
    }


    public static void main(String [] args){
        //load("src/test/resources/TestCase_v3.xlsx", "Case2");
        /**
         for (Case cs: CaseUtil.cases){
         System.out.println("!!!"+cs);
         }
         **/

        String [] cellNames = {"Params"};
        Object[][] testdata = getCaseDatasByApiId("1", cellNames);
        for (Object[] objects: testdata){
            for (Object object : objects){
                System.out.println("["+object+"]");
            }

        }

    }

}
