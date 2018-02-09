package com.wab.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author hcq
 * @create 2018-01-26 下午 6:04
 **/

public class HttpUtils {
    public static final String BASIC_64 = "Basic " + new String(Base64.getEncoder().encode("appclient:appsecret".getBytes()));

    private static String executeDefault(HttpUriRequest request) throws IOException {
        String respContent = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse resp = client.execute(request);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
        }
        return respContent;
    }


    public static String httpPost(String url, List<BasicNameValuePair> nameValuePairs) throws IOException {
        return httpPost(url, null, nameValuePairs);
    }


    public static String httpPost(String url, Header header, List<BasicNameValuePair> nameValuePairs) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(header);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        return executeDefault(httpPost);
    }

    public static String httpGet(String url) throws IOException {
        return httpGet(url, null);
    }

    public static String httpGet(String url, Header header) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(header);
        return executeDefault(httpGet);
    }

    public static String httpGetWithToken(String url, String token) throws IOException {
        return httpGet(url, new BasicHeader("Authorization", "bearer " + token));
    }

    public static String httpPostwithToken(String url ,String token)  throws IOException {
        List<BasicNameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("token", token));
        return httpPost(url, nameValuePairs);
    }

    public static String getUser(String url, String token) throws IOException {
        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
        pairList.add(new BasicNameValuePair("token", token));
        Header header = new BasicHeader("Authorization", BASIC_64);
        return httpPost(url, header, pairList);
    }

    public static String getToken(String url, String app_id, String app_key, String username, String password) throws Exception {
        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
        pairList.add(new BasicNameValuePair("grant_type", "password"));
        pairList.add(new BasicNameValuePair("username", username));
        pairList.add(new BasicNameValuePair("password", password));
        Header header = new BasicHeader("Authorization", BASIC_64);
        return httpPost(url, header, pairList);
    }

}
