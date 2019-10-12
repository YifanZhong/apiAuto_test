package com.project.apiAuto_v10.Util;

import com.project.apiAuto_v10.POJO.Case;
import com.project.apiAuto_v10.POJO.Rest;
import com.project.apiAuto_v10.POJO.WriteBackData;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelUtil_v10 {
    public static Map<String, Integer> caseIdRownumMapping = new HashMap<String, Integer>();
    public static Map<String, Integer> cellNameCellnumMapping = new HashMap<String, Integer>();
    //静态代码块，提前完成数据加载
    static{
        loadRownumAndCellnumMapping("src/test/resources/TestCase_v10.xlsx","CaseInfoSheet");
    }

    public static List<WriteBackData> writeBackData = new ArrayList<WriteBackData>();


    /***
     * 获取caseId以及它对应的行index
     * 获取cellName以及它对应的列index
     * @param excelPath
     * @param sheetName
     */
    private static void loadRownumAndCellnumMapping(String excelPath, String sheetName) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(excelPath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Row titleRow = sheet.getRow(0);
            //获取第一行
            if (titleRow!=null&&!isEmptyRow(titleRow)){
                int lastCellNum = titleRow.getLastCellNum(); //比index大一
                //循环处理标题行的每一列
                for (int i = 0; i < lastCellNum; i++) {
                    Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String title = cell.getStringCellValue();
                    title = title.substring(0,title.indexOf("("));
                    int cellNum = cell.getAddress().getColumn();
                    cellNameCellnumMapping.put(title,cellNum);
                }
            }
            //从第二行开始，获取所有的数据行
            int lastRowNum = sheet.getLastRowNum(); //没有比index大一，所以循环取等号
            for (int i = 1; i <= lastRowNum; i++) {
                Row dataRow = sheet.getRow(i);
                Cell firstCellOfRow = dataRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                firstCellOfRow.setCellType(CellType.STRING);
                String caseId = firstCellOfRow.getStringCellValue();
                int rowNum = dataRow.getRowNum();
                caseIdRownumMapping.put(caseId,rowNum);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }


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
    public static <T> void load(String excelPath, String sheetName, Class<T> clazz) {
        //Class clazz = Case.class;
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
                //Case cs = (Case) clazz.newInstance();
                Object obj = clazz.newInstance();
                //拿到一个数据行
                Row dataRow = sheet.getRow(i);

                if(dataRow==null||isEmptyRow(dataRow)){
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
                    method.invoke(obj,value);
                }
                //System.out.println(cs);
                //CaseUtil.cases.add(cs);
                if (obj instanceof Case){ //instanceof 是判断对象类型的语法
                    Case cs = (Case) obj;
                    CaseUtil.cases.add(cs);
                }else if (obj instanceof Rest){
                    Rest rest = (Rest) obj;
                    RestUtil.rests.add(rest);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private static boolean isEmptyRow(Row dataRow){
        int lastCellNum = dataRow.getLastCellNum();
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = dataRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            String value = cell.getStringCellValue();
            if (value!=null&&value.trim().length()>0){
                return false;
            }

        }
        return true;
    }


    /***
     * 回写接口回应response
     * @param excelPath
     * @param sheetName
     * @param caseId
     * @param cellName
     * @param result
     */
    public static void writeBackData(String excelPath, String sheetName, String caseId, String cellName, String result) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(excelPath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            int rownum = caseIdRownumMapping.get(caseId);//拿到行index
            Row row = sheet.getRow(rownum);
            int cellnum = cellNameCellnumMapping.get(cellName);//拿到列index
            Cell cell = row.getCell(cellnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(result);
            outputStream = new FileOutputStream(new File(excelPath));
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream!=null){
                    outputStream.close();
                }
                if (inputStream!=null){
                    inputStream.close();
                }
            } catch (Exception e2){
                e2.printStackTrace();
            }

        }


        //保证调用之前，caseIdRownumMapping数据就位
        //int rownum = caseIdRownumMapping.get(caseId);//拿到行index
        //int cellnum = cellNameCellnumMapping.get(cellName);//拿到列index

        //sheet.getRow(rownum)-->row
        //row.getCell(cellnum)-->cell
        //cell.setCellValue(result)

    }

    /***
     * 批量回写数据的方法
     * @param excelPath
     */
    public static void batchWhiteBackData(String excelPath) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(excelPath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            for (WriteBackData theWriteBackData:writeBackData){
                //获取sheetName
                String sheetName = theWriteBackData.getSheetName();
                Sheet sheet = workbook.getSheet(sheetName);
                String caseId = theWriteBackData.getCaseId();
                int rownum = caseIdRownumMapping.get(caseId);
                Row row = sheet.getRow(rownum);

                String cellName = theWriteBackData.getCellName();
                int cellnum = cellNameCellnumMapping.get(cellName);
                Cell cell = row.getCell(cellnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);

                String result = theWriteBackData.getResult();
                cell.setCellValue(result);

            }

            outputStream = new FileOutputStream(new File(excelPath));
            workbook.write(outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (outputStream!=null){
                    outputStream.close();
                }
                if (inputStream!=null){
                    inputStream.close();
                }
            } catch (Exception e2){
                e2.printStackTrace();
            }


        }

    }
}
