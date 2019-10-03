package com.project.apiAuto_v2;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class getDemo {
    public static void main(String[] args) throws IOException {
        //提供接口地址
        String url = "http://47.107.166.132:8080/futureloan/mvc/api/member/register";
        //准备测试数据
        String mobilephone = "13166666666";
        String pwd = "666666";


        Map<String,String> params = new HashMap<String, String>();
        params.put("mobilephone", mobilephone);
        params.put("pwd",pwd);

        System.out.println(HttpUtil.doGet(url, params));

        /**
        url += ("?mobilephone="+ mobilephone + "&pwd=" + pwd);
        //System.out.println(url);
        //指定接口请求方式为：get
        HttpGet get = new HttpGet(url);
        //发起请求,拿到响应数据
        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = client.execute(get);
        int code = httpResponse.getStatusLine().getStatusCode();
        System.out.println(code);
        String result = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);
         **/
    }

    /**
    public static String doGet(String url, Map<String,String> params){
        Set<String> keys = params.keySet();

        int mark = 1;
        for (String name:keys){
            if (mark==1){
                url+=("?"+name+"="+params.get(name));
            }else{
                url+=("&"+name+"="+params.get(name));
            }
            mark++;
        }
        System.out.println("url = "+ url);


        //url += ("?mobilephone="+ mobilephone + "&pwd=" + pwd);

        //指定接口请求方式为：get
        HttpGet get = new HttpGet(url);
        //发起请求,拿到响应数据
        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = null;

        String result = "";
        try {
            httpResponse = client.execute(get);
            int code = httpResponse.getStatusLine().getStatusCode();
            System.out.println(code);
            result = EntityUtils.toString(httpResponse.getEntity());
            //System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    return result;
    }
     **/


}
