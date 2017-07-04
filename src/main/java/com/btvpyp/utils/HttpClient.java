package com.btvpyp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

public class HttpClient {
	
	public static void main(String args[]) throws Exception{
//		sendGet("http://27.112.86.91/dataservice/services/DataService/getContentrec/%7B%22platformType%22:2,%22contentId%22:%22VIDE1359592506325591%22%7D");
		String r = sendPost("http://api.btv.com.cn/openData/brtvQuestionResult/interface/addBrtvQuestionResult","data=%7B%22id%22%3A%2234%22%2C%22articleid%22%3A%2212%22%2C%22questionnairesId%22%3A%223%22%2C%22questionsId%22%3A%226%22%2C%22questionstitle%22%3A%22%E9%98%BF%E6%96%AF%E9%A1%BF%E9%A9%AC%E4%B8%81%22%2C%22optionId%22%3A%2212%22%2C%22optiontitle%22%3A%22%E5%B0%8F%E6%9C%88%E6%9C%88%22%2C%22userId%22%3A%223%22%2C%22userName%22%3A%22%E9%99%88ue%22%2C%22userLogo%22%3A%22http%3A%2F%2Fwww.brtn.cn%2Fdownload%2Fttt.png%22%2C%22createDate%22%3A%221476929989%22%2C%22answer%22%3A%22answer_abc%22%2C%22score%22%3A%2225%22%2C%22userIP%22%3A%22211.202.45.17%22%2C%22active_id%22%3A%221%22%7D");
		System.out.println(r);
	}
	
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     * @throws Exception 
     */
    public static String sendGet(String url) throws Exception {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
//            System.out.println(URLDecoder.decode(result, "UTF-8"));
            System.out.println(result.getBytes("UTF-8"));
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return URLDecoder.decode(result, "UTF-8").toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }   
    
}