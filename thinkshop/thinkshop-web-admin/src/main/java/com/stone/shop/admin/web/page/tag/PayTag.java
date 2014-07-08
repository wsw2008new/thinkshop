package com.stone.shop.admin.web.page.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.stone.shop.common.utils.StringUtils;
import com.stone.shop.domain.model.CheckState;
import com.stone.shop.domain.model.Flow;
import com.stone.shop.domain.model.PayType;
import com.stone.shop.domain.model.SettState;
import com.stone.shop.utils.log.JscnLogger;

public class PayTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2390547886352506692L;

	/** 结算状态 */
	private static final String SETTL_STATE = "0";
	/** 支付状态 */
	private static final String PAY_STATE = "1";
	/** 支付方式 */
	private static final String PAY_TYPE = "2";
	/** 交易类型 */
	private static final String ITEM_TYPE = "3";
	/** 资金流向 */
	private static final String MONEY_FLOW = "4";
	/** 审核状态 */
	private static final String CHECK_STATE = "5";

	private String flag;

	private String value;

	@Override
	public int doStartTag() throws JspException {
		try {
			if (StringUtils.isNotEmpty(value)) {
				if (SETTL_STATE.equals(flag)) {
					pageContext.getOut().print(
							SettState.getStateValue(Integer.parseInt(value)));
				} else if (PAY_TYPE.equals(flag)) {
					pageContext.getOut().print(
							PayType.getPayTypeValue(Integer.parseInt(value)));
				} else if (MONEY_FLOW.equals(flag)) {
					pageContext.getOut().print(
							Flow.getFlowValue(Integer.parseInt(value)));
				} else if (CHECK_STATE.equals(flag)) {
					pageContext.getOut().print(
							CheckState.getStateValue(Integer.parseInt(value)));
				}
				return EVAL_BODY_INCLUDE;
			}
		} catch (Exception e) {
			JscnLogger.error(e.getMessage(), e, this.getClass());
		}
		return 0;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
