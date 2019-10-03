package com.project.apiAuto_v2;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class ExcelUtil {
    public static Object [][] testdata(){
        String excelPath = "src/test/resources/TestCase_v1.xlsx";
        //获取Workbook对象
        Object[][] testdata = null;
        try {
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            //获取Sheet对象
            Sheet sheet = workbook.getSheet("RegisterTestCaseSheet");
            //获取行
            testdata = new Object[6][2];
            for (int i = 1; i <= 6; i++){
                Row row = sheet.getRow(i);
                for (int j = 5; j <= 6; j++){
                    Cell cell = row.getCell(j);
                    //将列设置为字符串类型
                    cell.setCellType(CellType.STRING);
                    String cellValue = cell.getStringCellValue();
                    testdata[i-1][j-5] = cellValue;
                }
            }

            //获取列
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return testdata;
    }

    public static void main(String[] args){
        Object[][] testdata = testdata();
        for (Object[] objects : testdata){
            for (Object object:objects){
                System.out.println("["+object+"]");
                //System.out.println(object);
            }
        }
    }
}
