package com.project.apiAuto_v9.Util;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class HttpUtil {

    public static Map<String, String> cookies = new HashMap<String, String>();

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

            addCookieInRequestHeaderBeforeRequest(post);

            HttpResponse httpResponse = client.execute(post);


            getAndStoreCookiesFromResponseHeader(httpResponse);

            //状态码
            int code = httpResponse.getStatusLine().getStatusCode();
            //System.out.println(code);
            //响应报文
            result = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(result);
        return result;
    }

    private static void addCookieInRequestHeaderBeforeRequest(HttpRequest request) {
        String jsessionidCookie = cookies.get("JSESSIONID");
        if (jsessionidCookie!=null){
            request.addHeader("Cookie",jsessionidCookie);
        }
    }

    private static void getAndStoreCookiesFromResponseHeader(HttpResponse httpResponse) {
        //从响应头里取出名字为"Set-Cookie"的响应头
        Header setCookieHeader = httpResponse.getFirstHeader("Set-Cookie");
        //如果不为空
        if (setCookieHeader!=null){
            //取出此响应头的值
            String setCookieHeaderValue = setCookieHeader.getValue();
            if (setCookieHeaderValue!=null&&setCookieHeaderValue.trim().length()>0){
                //以；来切分
                String[] cookiePairs = setCookieHeaderValue.split(";");
                if (cookiePairs!=null){
                    for (String cookiePair:cookiePairs){
                        //如果包含了JSESSIONID，则意味着响应头里有会话id这个数据
                        if (cookiePair.contains("JSESSIONID"));
                        //保存到map
                        cookies.put("JSESSION",cookiePair);
                    }
                }
            }
        }
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
            addCookieInRequestHeaderBeforeRequest(get);

            httpResponse = client.execute(get);
            getAndStoreCookiesFromResponseHeader(httpResponse);
            int code = httpResponse.getStatusLine().getStatusCode();
            System.out.println(code);
            result = EntityUtils.toString(httpResponse.getEntity());
            //System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String doService(String url, String type, Map<String,String> params){
        String result = "";
        if ("POST".equalsIgnoreCase(type)){
            result = HttpUtil.doPost(url, params);
        }else if ("GET".equalsIgnoreCase(type)){
            result = HttpUtil.doGet(url,params);
        }
        return result;
    }

}
