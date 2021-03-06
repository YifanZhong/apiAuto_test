package com.project.apiAuto_v2;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class postDemo {

    public static void main(String[] args) throws IOException {
        //填写接口地址
        String url = "http://47.107.166.132:8080/futureloan/mvc/api/member/register";
        //准备测试数据
        String mobilephone = "13166666666";
        String pwd = "666666";

        Map<String, String> params = new HashMap<String, String>();
        params.put("mobilephone", mobilephone);
        params.put("pwd",pwd);
        System.out.println(HttpUtil.doPost(url,params));


        /**
        //指定接口请求方式为：post
        HttpPost post = new HttpPost(url);
        List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        parameters.add(new BasicNameValuePair("mobilephone", mobilephone));
        parameters.add(new BasicNameValuePair("pwd",pwd));
        post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
        //发起请求,获取接口响应信息（状态码，响应报文，或者某些特殊的响应头数据）
        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = client.execute(post);
        //状态码
        int code = httpResponse.getStatusLine().getStatusCode();
        System.out.println(code);
        //响应报文
        String result = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);
         **/
    }

    /**
    public static String doPost(String url, Map<String, String> params){
        //指定接口请求方式为：post
        HttpPost post = new HttpPost(url);
        //准备测试数据
        List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        //取出Map中所有的参数名
        Set<String> keys = params.keySet();
        //通过循环将参数保存到list集合
        for (String name:keys){
            String value = params.get(name);
            parameters.add(new BasicNameValuePair(name, value));

        }

        String result = "";
        try {
            post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
            //发起请求,获取接口响应信息（状态码，响应报文，或者某些特殊的响应头数据）
            HttpClient client = HttpClients.createDefault();
            HttpResponse httpResponse = client.execute(post);
            //状态码
            int code = httpResponse.getStatusLine().getStatusCode();
            System.out.println(code);
            //响应报文
            result = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(result);
        return result;
    }
     **/

}
