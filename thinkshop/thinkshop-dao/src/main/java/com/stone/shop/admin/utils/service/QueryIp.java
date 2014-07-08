package com.stone.shop.admin.utils.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;

public class QueryIp {

	public static void main(String[] args) {
		
		String ip="202.201.112.98";
		ipQuery(ip);
	}
	
	//查询IP地址
	public static void ipQuery(String ip)
	{
		String url="http://www.youdao.com/smartresult-xml/search.s";
		//参数 type=id&q=ip地址
		Map<String,String> parameters = new HashMap<String,String>();
        parameters.put("type", "ip");
        parameters.put("q", ip);//要查询的 ip
        
		try {
			Result result=SendRequest.sendGet(url, null, parameters, "gbk");
			
			HttpEntity  entity=result.getHttpEntity();
			InputStream is=entity.getContent();
			
			int len=0;
			byte[] buf=new byte[1024];
			while((len=is.read(buf))!=-1)
			{
				System.out.println(new String(buf,0,len));
			}
			is.close();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
