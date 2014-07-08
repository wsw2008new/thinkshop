package com.stone.shop.utils.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class QueryIdentity {

	public static void main(String[] args) {
		
//		String identity="150428198910045310";
		String identity="320381198708162757";
		
		//identityQuery(identity);//直接 打印 返回的 xml格式数据
		identityQuery2(identity);//解析 收到的 xml 格式数据
	}
	
	//身份证 查询请求 2
	public static void identityQuery2(String identity)
	{
		//解析  sb(xml格式的数据)
		SAXBuilder saxb=new SAXBuilder();
		try 
		{
			Document doc=saxb.build("http://www.youdao.com/smartresult-xml/search.s?type=id&q="+identity);
			Element root=doc.getRootElement();
				//System.out.println("根结点="+root);
				
			List lst=root.getChildren("product");
				//System.out.println("子结点数="+lst.size());
				
			for(int i=0;i<lst.size();i++)
			{
				Element element=(Element)lst.get(i);
				
				String type=element.getAttributeValue("type");
			    String code=element.getChildText("code");
			    String location=element.getChildText("location");
			    String birthday=element.getChildText("birthday");
			    String gender=element.getChildText("gender");
			    
			    System.out.println("证件类别:"+type);
			    System.out.println("证件号码:"+code);
		 	    System.out.println("贯籍:"+location);
			    System.out.println("生日:"+birthday);
			    System.out.println("性别:"+(gender.equals("m")?"男":"女"));
			    System.out.println("-----------------------------------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//身份证 查询请求1
	public static void identityQuery(String identity)
	{
		String url="http://www.youdao.com/smartresult-xml/search.s";
		//参数 type=id&q=身份证号
		Map<String,String> parameters = new HashMap<String,String>();
        parameters.put("type", "id");
        parameters.put("q", identity);//要查询的 身份证号
        
		try {
			Result result=SendRequest.sendGet(url, null, parameters, "gbk");
			
			HttpEntity  entity=result.getHttpEntity();
			print(entity.getContent());
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//输出 收到的信息
	public static void print(InputStream is)
	{
		StringBuffer sb=new StringBuffer();
		
		int len=0;
		byte[] buf=new byte[1024];
		
		try 
		{
			while((len=is.read(buf))!=-1)
			{
				sb.append(new String(buf,0,len));
			}
			
			//解析  sb(xml格式的数据)
			SAXBuilder saxb=new SAXBuilder();
			try 
			{
				Document doc=saxb.build(sb.toString());
				Element root=doc.getRootElement();
					System.out.println("根结点="+root);
					
				List lst=root.getChildren("product");
					System.out.println("子结点数="+lst.size());
					
				for(int i=0;i<lst.size();i++)
				{
					Element element=(Element)lst.get(i);
					
					String type=element.getAttributeValue("type");
				    String code=element.getChildText("code");
				    String location=element.getChildText("location");
				    String birthday=element.getChildText("birthday");
				    String gender=element.getChildText("gender");
				    
				    System.out.println("用户信息:");
				    System.out.println("证件类别:"+type);
				    System.out.println("证件号码:"+code);
			 	    System.out.println("贯籍:"+location);
				    System.out.println("生日:"+birthday);
				    System.out.println("性别:"+(gender.equals("m")?"男":"女"));
				    System.out.println("-----------------------------------");
				}
				
			} catch (JDOMException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
