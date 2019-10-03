package com.project.apiAuto_v2;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RegisterCase_v1 {
    //mobilephone:"13644445555", pwd:""
    //mobilephone:"", pwd:"123456"
    //mobilephone:"1364444", pwd:"123456"
    //mobilephone:"13644445555", pwd:"12345"
    //mobilephone:"13644445555", pwd:"123456"
    //mobilephone:"13644445555", pwd:"123456"


    @Test
    public void Test1(){
        String url = "http://47.107.166.132:8080/futureloan/mvc/api/member/register";
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobilephone","13644445555");
        params.put("pwd","");
        System.out.println(HttpUtil.doPost(url,params));
    }

    @Test
    public void Test2(){
        String url = "http://47.107.166.132:8080/futureloan/mvc/api/member/register";
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobilephone","");
        params.put("pwd","123456");
        System.out.println(HttpUtil.doPost(url,params));
    }

    @Test
    public void Test3(){
        String url = "http://47.107.166.132:8080/futureloan/mvc/api/member/register";
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobilephone","1364444");
        params.put("pwd","123456");
        System.out.println(HttpUtil.doPost(url,params));
    }

    @Test
    public void Test4(){
        String url = "http://47.107.166.132:8080/futureloan/mvc/api/member/register";
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobilephone","13644445555");
        params.put("pwd","12345");
        System.out.println(HttpUtil.doPost(url,params));
    }

    @Test
    public void Test5(){
        String url = "http://47.107.166.132:8080/futureloan/mvc/api/member/register";
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobilephone","13644445555");
        params.put("pwd","123456");
        System.out.println(HttpUtil.doPost(url,params));
    }

    @Test
    public void Test6(){
        String url = "http://47.107.166.132:8080/futureloan/mvc/api/member/register";
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobilephone","13644445555");
        params.put("pwd","123456");
        System.out.println(HttpUtil.doPost(url,params));
    }
}
