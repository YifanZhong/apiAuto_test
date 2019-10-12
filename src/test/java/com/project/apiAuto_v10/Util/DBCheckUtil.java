package com.project.apiAuto_v10.Util;

import com.alibaba.fastjson.JSONObject;
import com.project.apiAuto_v10.POJO.DBChecker;
import com.project.apiAuto_v10.POJO.DBQueryResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBCheckUtil {
    /***
     * 根据脚本执行查询并返回查询结果
     * @param validateSql 需要执行的查询语句
     * @return
     */
    public static String doQuery(String validateSql) {
        //将脚本字符串封装成对象
        List<DBChecker> dbCheckers = JSONObject.parseArray(validateSql, DBChecker.class);
        List<DBQueryResult> dbQueryResults = new ArrayList<DBQueryResult>();
        //循环遍历，取出SQL脚本执行
        for (DBChecker dbChecker:dbCheckers){
            //拿到sql的编号
            String no = dbChecker.getNo();
            String sql = dbChecker.getSql();
            //执行查询，获取结果
            Map<String, Object> columnLabelAndValues= JDBCUtil.query(sql);
            DBQueryResult dbQueryResult = new DBQueryResult();
            dbQueryResult.setNo(no);
            dbQueryResult.setColumnLabelAndValues(columnLabelAndValues);
            dbQueryResults.add(dbQueryResult);
            //再把List反序列成String

        }
        return JSONObject.toJSONString(dbQueryResults);
    }


    public static void main(String[] args){
        String validateSql = "[{\"no\":\"1\",\"sql\":\"select leaveamount from member where mobilephone='13517315120'\"}, {\"no\":\"2\",\"sql\":\"select count(*) as totalNum from financelog where IncomeMemberId = (select id from member where mobilephone='13517315120'\"}]";
        List<DBChecker> dbCheckers = JSONObject.parseArray(validateSql, DBChecker.class);
        for (DBChecker dbChecker:dbCheckers){
            System.out.println(dbChecker);
        }
    }
}
