package com.project.apiAuto_v5;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtil {

    /***
     * 以POST的方式处理接口
     * @param url
     * @param params
     * @return
     */
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

    /***
     * 以GET的方式完成接口调用
     * @param url
     * @param params
     * @return
     */
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
        //System.out.println("url = "+ url);

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

}
