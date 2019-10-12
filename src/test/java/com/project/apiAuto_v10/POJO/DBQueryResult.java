package com.project.apiAuto_v10.POJO;

import java.util.List;
import java.util.Map;


public class DBQueryResult {
    // 脚本编号
    private String no;
    //结果
    private Map<String, Object> columnLabelAndValues;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Map<String, Object> getColumnLabelAndValues() {
        return columnLabelAndValues;
    }

    public void setColumnLabelAndValues(Map<String, Object> columnLabelAndValues) {
        this.columnLabelAndValues = columnLabelAndValues;
    }
}