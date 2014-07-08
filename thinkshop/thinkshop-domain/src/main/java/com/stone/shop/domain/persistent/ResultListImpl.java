/**
 * 
 */
package com.stone.shop.domain.persistent;

import java.util.List;

import com.stone.shop.base.common.page.Pagination;

/**
 * 持久化结果集列表接口实现类
 */
public class ResultListImpl<T> implements ResultList<T> {

	private static final long serialVersionUID = -4590321979224243763L;

	public ResultListImpl() {

	}
	/** 返回记录列表 */
	private List<T> results;

	/** 返回分页信息 */
	private Pagination page;

	@Override
	public Pagination getPage() {
		return page;
	}

	@Override
	public List<T> getResults() {
		return results;
	}

	@Override
	public void setPage(Pagination page) {
		this.page = page;
	}

	@Override
	public void setResults(List<T> results) {
		this.results = results;
	}

}
