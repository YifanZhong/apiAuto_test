package com.project.apiAuto_v10.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;


public class JDBCUtil {
    public static Properties properties = new Properties();
    static {
        System.out.println("静态代码块解析properties数据");
        InputStream iStream;
        try{
            iStream = new FileInputStream(new File("src/main/resources/jdbc.properties"));
            properties.load(iStream);
        }catch (Exception e){
            System.out.println("【There are some error!!!】");
            e.printStackTrace();
        }
    }
    public static Map<String,Object> query(String sql, Object... values){
        //List<Object> list = new ArrayList<Object>();
        Map<String,Object> columnLabelAndValues = null;
        try{
            //1.根据连接信息，获得数据库连接（连上数据库）
            Connection connection = getConnection();
            //2.获取PrepareStatement对象（此类型的对象有提供数据库操作方法）
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //3.设置条件字段的值（实时绑定）
            for(int i =0;i<values.length;i++){
                preparedStatement.setObject(i+1,values[i]);
            }
            //4.调用查询方法，执行查询，返回ResultSet（结果集）
            ResultSet resultSet = preparedStatement.executeQuery();
            //获取查询相关的信息
            ResultSetMetaData metaData = resultSet.getMetaData();
            //得到查询字段的数目
            int columnCount = metaData.getColumnCount();

            System.out.println("查询出的结果列数是"+columnCount);
            //5.从结果集取查询数据
            while (resultSet.next()){
                columnLabelAndValues = new HashMap<String,Object>();
                //循环取出每个查询字段的数据
                for(int i=1;i<=columnCount;i++){
                    String columnLabel= metaData.getColumnLabel(i);
                    String columValue=resultSet.getObject(i).toString();
                    columnLabelAndValues.put(columnLabel,columValue);
                }
                //list.add(columnLabelAndValues);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return columnLabelAndValues;

    }

    private static Connection getConnection() throws SQLException {
        //从properties取url
        String url = properties.getProperty("jdbc.url");
        //从properties取user
        String user = properties.getProperty("jdbc.username");
        //从properties取password
        String password = properties.getProperty("jdbc.password");
        Connection connection = DriverManager.getConnection(url,user,password);
        return connection;
    }
    /**
    public static void main(String[] args){
        String sql = "select *from userinfo";
        //List<Object> list=JDBCUtil.query(sql);
        //for(Object o:list){
           // System.out.println(o);
        }

    }**/
}