package com.jjb.cmp.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**Http连接客户端，通过Apache Http common实现
 * 使用Http连接池进行连接
 * @author BIG.W.W.W
 * @version 1.0
 * @sine 2018/5/2
 */
public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
    private static String EMPTY_STR = "";
    private static  final String ENCORDING = "UTF-8";

    private static void init(){
        if (poolingHttpClientConnectionManager == null){
            poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
            poolingHttpClientConnectionManager.setMaxTotal(50);//设置连接池最大连接数
        }
    }

    /**
     * 获取一个可关闭的http链接
     * */
    private static CloseableHttpClient getHttpClient(){
        init();
        return HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).build();
    }

    /**
     * 使用get方法连接http
     * @param url 目标url
     * */
    public static String httpGetRequest(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    /**发送一个http请求
     * @param url 请求链接
     * @param headers 请求头集合
     * @param params 请求参数集合
     * @throws URISyntaxException 如果url的语法错误 会抛出此异常
     * */
    public static String httpGetRequest(String url, Map<String,Object> headers,Map<String,Object> params) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(url);
        uriBuilder.setParameters(covertParams2NVPS(params));
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        headers.entrySet().forEach(header -> httpGet.addHeader(header.getKey(),String.valueOf(header.getValue())));
        return getResult(httpGet);
    }

    /**
     * 发送httpPost请求
     * @param url 请求的地址
     * @param headers 请求头　
     * @param params 请求参数
     * @exception UnsupportedEncodingException 当编码方式不支持时抛出此异常
     * */
    public static String httpPostRequest(String url,Map<String,Object> headers,Map<String,Object> params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if (headers != null){
            headers.entrySet().forEach(header -> httpPost.addHeader(header.getKey(),String.valueOf(header.getValue())));
        }
        if (params != null){
            httpPost.setEntity(new UrlEncodedFormEntity(covertParams2NVPS(params),ENCORDING));
        }
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url,Map<String,Object> params) throws IOException {
        return httpPostRequest(url,null,params);
    }


    /**
     * 将 map 键值对转化为 键值集合
     * @param params 参数键值对
     * */
    private static List<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        List<NameValuePair> pairs = new ArrayList<>();
        params.entrySet().forEach(param -> pairs.add(new BasicNameValuePair(param.getKey(),String.valueOf(param.getValue()))));
        return pairs;
    }


    /**
     * 获取http请求结果
     * @param httpRequestBase http请求基础对象
     * @return String 如果请求没有任何内容返回则会返回空字符串
     * */
    private static String getResult(HttpRequestBase httpRequestBase) throws IOException {
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpRequestBase);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, ENCORDING);
				
				return result;
			}
		} catch (IOException e) {
			throw e;
		}finally {
			if(httpResponse!=null) {
				httpResponse.close();
			}
			if(httpClient!=null) {
				httpClient.close();
			}
		}
       
        return EMPTY_STR;
    }
    /**
     * http请求
     * @authour wuqiong
     * @param httpRequestBase
     * @return
     * @throws IOException
     * @version V1.0
     * @date  2018年8月23日 上午9:10:50
     */
    public static String execute(HttpRequestBase httpRequestBase) throws IOException {
    	CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse httpResponse = httpClient.execute(httpRequestBase);
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null){
            String result = EntityUtils.toString(entity,ENCORDING);
            httpResponse.close();
            return result;
        }
        return EMPTY_STR;
    }
}
