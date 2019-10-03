package com.project.apiAuto_v2;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;


public class ExcelUtil_v4 {
    /***
     *
     * @param excelPath
     * @param startRow 传行号，不是索引
     * @param endRow
     * @param startCell
     * @param endCell
     * @return
     */
    public static Object [][] testdata(String excelPath, int startRow, int endRow, int startCell, int endCell){
        //获取Workbook对象
        Object[][] testdata = null;
        try {
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            //获取Sheet对象
            Sheet sheet = workbook.getSheet("RegisterTestCaseSheet");
            //获取行
            testdata = new Object[endRow - startRow + 1][endCell - startCell + 1];
            for (int i = startRow-1; i <= (endRow - startRow + 1); i++){
                Row row = sheet.getRow(i);
                for (int j = startCell-1; j <= endCell-1; j++){
                    Cell cell = row.getCell(j);
                    //将列设置为字符串类型
                    cell.setCellType(CellType.STRING);
                    String cellValue = cell.getStringCellValue();
                    testdata[i-(startRow-1)][j-(startCell-1)] = cellValue;
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

}
