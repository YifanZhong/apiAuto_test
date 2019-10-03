package com.project.apiAuto_v2;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class getDemo {
    public static void main(String[] args) throws IOException {


        //提供接口地址
        String url = "http://47.107.166.132:8080/futureloan/mvc/api/member/register";
        //准备测试数据
        String mobilephone = "13166666666";
        String pwd = "666666";
        url += ("?mobilephone="+ mobilephone + "&pwd=" + pwd);
        System.out.println(url);
        //指定接口请求方式为：get
        HttpGet get = new HttpGet(url);
        //发起请求,拿到响应数据
        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = client.execute(get);
        int code = httpResponse.getStatusLine().getStatusCode();
        System.out.println(code);
        String result = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);
    }

}
