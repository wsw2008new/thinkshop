package com.stone.shop.admin.utils.service;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;

public class QueryTel {

	public static void main(String[] args) {
			
		String tel="13619573599";
		telQuery(tel);
	}
	
	//电话 号码 归属地查询
	public static void telQuery(String tel)
	{
		String url="http://www.yodao.com/smartresult-xml/search.s";
		//参数 type=id&q=电话号码
		Map<String,String> parameters = new HashMap<String,String>();
        parameters.put("type", "mobile");
        parameters.put("q", tel);//要查询的手机号码
        
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
		
		/*
		 * <?xml version="1.0" encoding="gbk"?>
			<smartresult>
				<product type="identitycard">
					<code>35042619890905701x</code>
					<location>福建省三明市尤溪县</location>
					<birthday>19890905</birthday>
					<gender>m</gender>
				</product>
			</smartresult>
		 */
	}
}
