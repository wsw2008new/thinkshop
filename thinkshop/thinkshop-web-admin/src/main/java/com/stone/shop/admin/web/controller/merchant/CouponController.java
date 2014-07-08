package com.stone.shop.admin.web.controller.merchant;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stone.shop.admin.web.controller.BaseController;
import com.stone.shop.common.page.Pagination;
import com.stone.shop.common.utils.DateTimeUtils;
import com.stone.shop.common.utils.StringUtils;
import com.stone.shop.domain.merchant.CouponEntity;
import com.stone.shop.domain.merchant.MerchantEntity;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.exception.ServiceException;
import com.stone.shop.service.merchant.ICouponService;
import com.stone.shop.service.merchant.IMerchantService;
import com.stone.shop.service.model.NewsModel;
import com.stone.shop.utils.exception.WebException;
import com.stone.shop.utils.log.JscnLogger;

@Controller
@RequestMapping("/coupon")
public class CouponController extends BaseController {

	@Autowired
	public ICouponService couponService;
	@Autowired
	public IMerchantService merchantService;

	@RequestMapping("toadd")
	public String toAdd(String id, ModelMap model,Pagination page) {
		if (StringUtils.isNotEmpty(id)) {
			CouponEntity entity = couponService.getCoupon(id);
			model.addAttribute("entity", entity);
		}
		Set<String> column = new HashSet<String>();
		ResultList<MerchantEntity> list = merchantService.queryMerchant("0", "", "", page, column);
		model.put("list", list);
		return "page/main/merchant/addCoupon";
	}

	@RequestMapping("saveupdate")
	public String saveOrUpdate(CouponEntity entity, //BindingResult result,
			HttpServletRequest request, HttpServletResponse response,@RequestParam() MultipartFile file)
			throws WebException, IOException {
		try {
			String url = request.getHeader("Referer");
//			getValidError(result);
				if(!file.isEmpty()){
				String path = request.getSession().getServletContext().getRealPath("/upload-file/");// this.servletContext.getRealPath("/upload-file/");//获取本地存储路径
				System.out.println(file.getContentType()+","+file.getName()+","+file.getOriginalFilename());
				System.out.println(path);
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path+"/"+file.getOriginalFilename()));
				entity.setShow_pic("/upload-file/"+file.getOriginalFilename());
			}
			if (StringUtils.isNotEmpty(entity.getId())) {
				couponService.updateCoupon(entity);
				JscnLogger.info(
						"更新优惠券 [" + entity.getId() + ":" + entity.getName()+ "] 成功！",
						this.getClass());
			} else {
				entity.setId(StringUtils.getUUID());
				String[] idname = entity.getMerchant_id().split("#");
				entity.setMerchant_id(idname[0]);
				entity.setMerchant_name(idname[1]);
				couponService.addCoupon(entity);
				JscnLogger.info(
						"新增优惠券 [" + entity.getId() + ":" + entity.getName()+"] 成功！",
						this.getClass());
			}
			response.sendRedirect(url);
			return null;
		} catch (Exception e) {
			JscnLogger.error("新增优惠券 [" + entity.getId() + ":" + entity.getName()+ "] 失败！异常：" + e.getMessage(),
					this.getClass());
			throw new WebException("新增优惠券失败！异常：" + e.getMessage());
		}

	}

	@RequestMapping("delete")
	public String delete(String id, HttpServletResponse response)
			throws WebException, IOException {
		try {
			couponService.deleteCoupon(id);
			JscnLogger.info("删除优惠券[" + id + "] 成功！", this.getClass());
			response.sendRedirect("search.htm");
		} catch (ServiceException e) {
			JscnLogger.error("删除优惠券 [" + id + "] 失败！异常：" + e.getMessage(),
					this.getClass());
			throw new WebException("删除优惠券失败！异常：" + e);
		}
		return null;
	}

	@RequestMapping("search")
	public String search(String is_recommend, String merchant_name,String name,ModelMap model, Pagination page) throws WebException {
		try {
			List<NewsModel> newsTypeList = NewsModel.getAllNewsType();
			Set<String> column = new HashSet<String>();
			ResultList<CouponEntity> bank = couponService.queryCoupon(is_recommend,merchant_name,page, column);
			model.addAttribute("bank", bank);
			model.addAttribute("merchant_name", merchant_name);
			model.addAttribute("is_recommend", is_recommend);
			model.put("newsType", newsTypeList);
		} catch (ServiceException e) {
			JscnLogger.error("查询优惠券信息失败！异常：" + e.getMessage(), this.getClass());
			throw new WebException("查询优惠券信息失败！异常：" + e);
		}
		return "page/main/merchant/couponList";
	}

	@RequestMapping("downLogo")
	public void downBankLogo(String path, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String outpath = DateTimeUtils.getCurrentFullDate() + ".jpg";
		exportFile(path, outpath, request, response);
	}

	/** 导出文件
	 * 
	 * @param inpath
	 *        目前要读的文件
	 * @param outpath
	 *        导出生成的文件
	 * @param request
	 *        HttpServletRequest
	 * @param response
	 *        HttpServletResponse
	 * 
	 * @throws Exception
	 *         Exception */
	private void exportFile(String inpath, String outpath,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		outpath = URLEncoder.encode(outpath, "utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		File file = new File(inpath);
		response.setContentType("application/x-msdownload");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment;filename="
				+ outpath);
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream buff = new BufferedInputStream(fis);
		byte[] b = new byte[1024];
		long k = 0;
		OutputStream myout = response.getOutputStream();
		while (k < file.length()) {
			int j = buff.read(b, 0, 1024);
			k += j;
			myout.write(b, 0, j);
		}
		myout.flush();
	}
}
