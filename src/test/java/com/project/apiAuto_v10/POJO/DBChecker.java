package com.project.apiAuto_v10.POJO;


/***
 * 数据库验证器
 */
public class DBChecker {
    private String no;
    private String sql;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "no= 【" + no + "】, sql=【" + sql + '】';
    }
}
