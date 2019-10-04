package com.project.apiAuto_v6;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


public class ExcelUtil_v8 {
    /***
     *
     * @param excelPath excel文件
     * @param rows 行号数组
     * @param cells 列号数组
     * @return testdata
     */
    public static Object [][] testdata(String excelPath, String sheetName, int[] rows, int[] cells){
        //获取Workbook对象
        Object[][] testdata = null;
        try {
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            //获取Sheet对象
            Sheet sheet = workbook.getSheet(sheetName);
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

    /***
     * 解析指定Excel表单的数据，封装为对象
     * @param excelPath excel文件的相对路径
     * @param sheetName Excel表单名
     */
    public static void load(String excelPath, String sheetName) {
        Class clazz = Case.class;
        //创建WorkBook对象
        try {
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            //创建sheet对象
            Sheet sheet = workbook.getSheet(sheetName);
            //获取第一行
            Row titleRow = sheet.getRow(0);
            //获取最后一行的列号
            int lastCellNum = titleRow.getLastCellNum();//拿到列号，比index大一
            //System.out.println(lastCellNum);




            //循环处理每一列, 取出每一列里面的字段名，保存到数组
            String [] fields = new String[lastCellNum]; //在外面准备的数组
            for (int i = 0; i < lastCellNum; i++){
                //根据列index获取对应的列
                Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                //设置列的类型为字符串
                cell.setCellType(CellType.STRING);
                //获取列的值（包含中文描述）
                String title = cell.getStringCellValue();
                //System.out.println(title);   //CaseId(用例编号)ApiId(接口编号)Decs(用例描述)Params(参数)
                title = title.substring(0, title.indexOf("("));
                fields[i] = title;

            }

            //获取行
            int lastRowIndex = sheet.getLastRowNum();
            //循环处理每一个数据行
            for (int i = 1; i <= lastRowIndex; i++) {
                Case cs = (Case) clazz.newInstance();
                //拿到一个数据行
                Row dataRow = sheet.getRow(i);

                if(dataRow==null){
                    continue;
                }
                //拿到此数据行上面的每一列,将数据封装到cs对象
                for (int j = 0; j < 4; j++) {
                    Cell cell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    //获取要反射的方法名
                    String methodName = "set"+fields[j];
                    //System.out.println(methodName);
                    //拿到methodName后，获取要反射的方法对象
                    Method method = clazz.getMethod(methodName, String.class);
                    //完成反射调用
                    method.invoke(cs,value);
                }
                System.out.println(cs);
                //
                CaseUtil.cases.add(cs);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }




}
