package com.project.apiAuto;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class postDemo {

    public static void main(String[] args) throws IOException {
        //填写接口地址
        String url = "http://47.107.166.132:8080/futureloan/mvc/api/member/register";
        //指定接口请求方式为：post
        HttpPost post = new HttpPost(url);
        //准备测试数据
        String mobilephone = "13166666666";
        String pwd = "666666";
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

    }
}
