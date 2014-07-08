package com.stone.shop.utils.term;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class TermsDefineXmlParser
{
  private static final String termFilePath = "/WEB-INF/classes/Terms.xml";

  public static Map<String, Map<String, Term>> getMap()
    throws DocumentException
  {
    Map rootMap = new HashMap();

    SAXReader reader = new SAXReader();
    String contextPath = RequestHelper.getContextRealPath();
    File file = new File(contextPath + "/WEB-INF/classes/Terms.xml");
    Document doc = reader.read(file);
    Element root = doc.getRootElement();
    for (Iterator i = root.elementIterator("category"); i.hasNext(); )
    {
      Element category = (Element)i.next();
      String id = category.attributeValue("id");
      Map categoryMap = new HashMap();

      for (Iterator j = category.elementIterator("term"); j.hasNext(); )
      {
        Element foo1 = (Element)j.next();
        String termId = foo1.attributeValue("id");
        Term term = new Term();
        term.setId(foo1.attributeValue("id"));
        term.setName(foo1.attributeValue("name"));
        term.setHiddenValue(foo1.attributeValue("hiddenValue"));
        term.setDisplayValue(foo1.attributeValue("displayValue"));
        term.setDescription(foo1.attributeValue("description"));
        categoryMap.put(termId, term);
      }

      rootMap.put(id, categoryMap);
    }

    return rootMap;
  }

  public static List<Term> getList(String categoryId)
    throws DocumentException
  {
    List categoryList = new ArrayList();

    SAXReader reader = new SAXReader();
    String contextPath = RequestHelper.getContextRealPath();
    File file = new File(contextPath + "/WEB-INF/classes/Terms.xml");
    Document doc = reader.read(file);
    Element root = doc.getRootElement();

    for (Iterator i = root.elementIterator("category"); i.hasNext(); )
    {
      Element category = (Element)i.next();
      String id = category.attributeValue("id");
      if (!id.equals(categoryId))
        continue;
      for (Iterator j = category.elementIterator("term"); j.hasNext(); )
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

  public static void removeElement(String categoryId, List<String> jg)
    throws Exception
  {
    SAXReader reader = new SAXReader();
    String contextPath = RequestHelper.getContextRealPath();
    File file = new File(contextPath + "/WEB-INF/classes/Terms.xml");
    Document doc = reader.read(file);
    Element root = doc.getRootElement();

    for (Iterator i = root.elementIterator("category"); i.hasNext(); )
    {
      Element category = (Element)i.next();
      String id = category.attributeValue("id");
      if (!id.equals(categoryId))
        continue;
      for (Iterator j = category.elementIterator("term"); j.hasNext(); )
      {
        Element term = (Element)j.next();
        category.remove(term);
      }
      for (int x = 0; x < jg.size(); x++)
      {
        Element e = category.addElement("term");
        e.addAttribute("id", String.valueOf(x + 1));
        e.addAttribute("name", jg.get(x));
        e.addAttribute("hiddenValue", String.valueOf(x + 1));
        e.addAttribute("displayValue", jg.get(x));
      }

      break;
    }

    doc.setRootElement(root);
    wirteXml(doc, "utf-8");
  }

  public static void wirteXml(Document document, String encoding)
    throws Exception
  {
    OutputFormat format = OutputFormat.createPrettyPrint();
    format.setEncoding(encoding);
    FileOutputStream fileWriter = new FileOutputStream(new File(RequestHelper.getContextRealPath() + "/WEB-INF/classes/Terms.xml"));
    XMLWriter output = new XMLWriter(fileWriter, format);
    output.write(document);

    output.close();
  }
}
