package com.stone.shop.utils.service;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class QueryWeather {
	public static void main(String[] args) {
		weatherQuery("南京天气");
	}
	public static void weatherQuery(String city)
	{
		//解析  sb(xml格式的数据)
		SAXBuilder saxb=new SAXBuilder();
		try 
		{
			Document doc=saxb.build("http://www.youdao.com/smartresult-xml/search.s?type=weather&jsFlag=true&q="+city);
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
	
}
