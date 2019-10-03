package com.project.apiAuto_v3;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;


public class ExcelUtil_v6 {
    /***
     *
     * @param excelPath
     * @param rows 行号数组
     * @param cells 列号数组
     * @return
     */
    public static Object [][] testdata(String excelPath, int[] rows, int[] cells){
        //获取Workbook对象
        Object[][] testdata = null;
        try {
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            //获取Sheet对象
            Sheet sheet = workbook.getSheet("RegisterTestCaseSheet2");
            //定义保存数据的数组
            testdata = new Object[rows.length][cells.length];
            //System.out.println(rows.length+":"+cells.length);
            //通过循环获取每一行
            for (int i = 0; i < rows.length; i++){
                //根据行index，取出一行
                Row row = sheet.getRow(rows[i]-1);
                for (int j = 0; j < cells.length; j++){
                    //获取列
                    Cell cell = row.getCell(cells[j]-1);
                    //将列设置为字符串类型
                    cell.setCellType(CellType.STRING);
                    String cellValue = cell.getStringCellValue();
                    testdata[i][j] = cellValue;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return testdata;
    }

}
