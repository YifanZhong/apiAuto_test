package com.project.apiAuto_v2;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

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
}
