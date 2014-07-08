package com.stone.shop.admin.utils.term;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TermManager {
	@SuppressWarnings("unchecked")
	public static List<Term> getList(String categoryId)throws DocumentException 
	{
		List categoryList = new ArrayList();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(TermManager.class.getResourceAsStream("/Terms.xml")); //ClassLoader.getSystemResourceAsStream("Terms.xml")
		Element root = doc.getRootElement();
		for (Iterator i = root.elementIterator("category"); i.hasNext();) 
		{
			Element category = (Element) i.next();
			String id = category.attributeValue("id");
			if (!id.equals(categoryId))
				continue;
			for (Iterator j = category.elementIterator("term"); j.hasNext();) 
			{
				Element foo1 = (Element)j.next();
		        Term term = new Term();
		        term.setId(foo1.attributeValue("id"));
		        term.setName(foo1.attributeValue("name"));
		        term.setHiddenValue(foo1.attributeValue("hiddenValue"));
		        term.setDisplayValue(foo1.attributeValue("displayValue"));
		        categoryList.add(term);
			}
			break;
		}
		return categoryList;
	}

	public static void main(String[] args) {
		try {
			List<Term> list = TermManager.getList("merchantType");
			for (Term term : list) {
				System.out.println(term.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

