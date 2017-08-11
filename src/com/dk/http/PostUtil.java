package com.dk.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class PostUtil {
	public static String path = "http://192.168.1.56:8080/shangqi/user/logininfo.ll";
	public static String loginByPost(String username,String password){
		StringBuffer sb = new StringBuffer();
		String line;
		BufferedReader in = null;
		try {
			path = path+"?userName="+username+"&password="+password;
			URL getUrl = new URL(path);
			HttpURLConnection urlConnection = (HttpURLConnection)getUrl.openConnection();
			
			//设置请求参数
			urlConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
			urlConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			urlConnection.setRequestProperty("Connection", "keep-alive");
			urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36");
			
			//实际连接
//			urlConnection.connect();
			
			//获取所有响应头字段
			Map<String,List<String>> map = urlConnection.getHeaderFields();
			
			for(String key:map.keySet()){
				System.out.println(key+"-----"+map.get(key));
			}
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			while((line=in.readLine()) != null){
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}

}
