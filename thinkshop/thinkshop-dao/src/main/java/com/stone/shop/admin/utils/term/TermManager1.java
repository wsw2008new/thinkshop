package com.stone.shop.admin.utils.term;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import com.stone.shop.base.common.utils.StringUtils;

public class TermManager1 {
	private static Logger log = Logger.getLogger(TermManager1.class);
	private static Map<String, Map<String, Term>> rootMap;

	public static Map<String, Term> getTermMap(String categoryId) {
		Map retMap = getRootMap().get(categoryId);
		if (retMap == null) {
			retMap = new HashMap();
			log.error("Term.xml中未找到categoryId = [" + categoryId + "]");
		}
		return retMap;
	}

	public static List<Term> getTermList(String categoryId) {
		try {
			return TermsDefineXmlParser.getList(categoryId);
		} catch (DocumentException e) {
			log.error("读取Term.xml文件异常", e);
		}
		return new ArrayList();
	}

	public static Term getTerm(String categoryId, String TermId) {
		return getTermMap(categoryId).get(TermId);
	}

	public static String getName(String categoryId, String TermId) {
		Term term = getTerm(categoryId, TermId);
		if (term == null) {
			return "";
		}

		return term.getName();
	}

	public static String getHTMLName(String categoryId, String TermId) {
		Term term = getTerm(categoryId, TermId);
		String name = "&nbsp;";
		if ((term != null) && (StringUtils.isNotEmpty(term.getName())))
			name = term.getName();
		return name;
	}

	public static String getHiddenValue(String categoryId, String TermId) {
		return getTerm(categoryId, TermId).getHiddenValue();
	}

	public static String getDisplayValue(String categoryId, String TermId) {
		return getTerm(categoryId, TermId).getDisplayValue();
	}

	public static String getHiddenValueByName(String categoryId, String name) {
		List<Term> termList = getTermList(categoryId);
		for (Term term : termList) {
			if (name.equals(term.getName())) {
				return term.getHiddenValue();
			}
		}
		return "";
	}

	public static String getIdByName(String categoryId, String name) {
		List<Term> termList = getTermList(categoryId);
		for (Term term : termList) {
			if (name.equals(term.getName())) {
				return term.getId();
			}
		}
		return "";
	}

	public static String getIdByHiddenValue(String categoryId, String hiddenValue) {
		List<Term> termList = getTermList(categoryId);
		for (Term term : termList) {
			if (hiddenValue.equals(term.getHiddenValue())) {
				return term.getId();
			}
		}
		return "";
	}

	public static Map<String, Map<String, Term>> getRootMap() {
		if (rootMap == null) {
			try {
				rootMap = TermsDefineXmlParser.getMap();
			} catch (Exception e) {
				rootMap = new HashMap();
				log.error("读取Term文件出错！", e);
			}
		}
		return rootMap;
	}
}
