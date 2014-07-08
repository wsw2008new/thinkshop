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

import com.stone.shop.admin.exception.ServiceException;
import com.stone.shop.admin.service.merchant.IMerchantTypeService;
import com.stone.shop.admin.service.model.NewsModel;
import com.stone.shop.admin.utils.exception.WebException;
import com.stone.shop.admin.utils.log.JscnLogger;
import com.stone.shop.admin.web.controller.BaseController;
import com.stone.shop.base.common.page.Pagination;
import com.stone.shop.base.common.utils.DateTimeUtils;
import com.stone.shop.base.common.utils.StringUtils;
import com.stone.shop.domain.merchant.MerchantTypeEntity;
import com.stone.shop.domain.persistent.ResultList;

@Controller
@RequestMapping("/type")
public class MerchantTypeController extends BaseController {

	@Autowired
	public IMerchantTypeService merchantTypeService;

	@RequestMapping("toadd")
	public String toAdd(String id, ModelMap model) {
		List<NewsModel> newsType = NewsModel.getAllNewsType();
		if (StringUtils.isNotEmpty(id)) {
			MerchantTypeEntity entity = merchantTypeService.getMerchantType(id);
			model.addAttribute("entity", entity);
		}
		model.put("newsType", newsType);
		return "page/main/merchant/addMerchantType";
	}

	@RequestMapping("saveupdate")
	public String saveOrUpdate(MerchantTypeEntity entity, //BindingResult result,
			HttpServletRequest request, HttpServletResponse response,@RequestParam() MultipartFile file)
			throws WebException, IOException {
		try {
			String url = request.getHeader("Referer");
//			getValidError(result);
			if(!file.isEmpty()){
				
				String path = request.getSession().getServletContext().getRealPath("/upload-file/");// this.servletContext.getRealPath("/upload-file/");//获取本地存储路径
//				byte[] bytes = file.getBytes();
//				FileOutputStream fos = new FileOutputStream(path+file.getOriginalFilename()); // 上传到写死的上传路径
//				fos.write(bytes);  
				System.out.println(file.getContentType()+","+file.getName()+","+file.getOriginalFilename());
				System.out.println(path);
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path+"/"+file.getOriginalFilename()));
				entity.setShow_pic("/upload-file/"+file.getOriginalFilename());
			}
			if (StringUtils.isNotEmpty(entity.getId())) {
				merchantTypeService.updateMerchantType(entity);
				JscnLogger.info(
						"更新商户类型 [" + entity.getId() + ":" + entity.getName()+ "] 成功！",
						this.getClass());
			} else {
				entity.setId(StringUtils.getUUID());
				merchantTypeService.addMerchantType(entity);
				JscnLogger.info(
						"新增商户类型 [" + entity.getId() + ":" + entity.getName()+"] 成功！",
						this.getClass());
			}
			response.sendRedirect(url);
			return null;
		} catch (Exception e) {
			JscnLogger.error("新增商户类型 [" + entity.getId() + ":" + entity.getName()+ "] 失败！异常：" + e.getMessage(),
					this.getClass());
			throw new WebException("新增商户类型失败！异常：" + e.getMessage());
		}

	}

	@RequestMapping("delete")
	public String delete(String id, HttpServletResponse response)
			throws WebException, IOException {
		try {
			merchantTypeService.deleteMerchantType(id);
			JscnLogger.info("删除商户类型 [" + id + "] 成功！", this.getClass());
			response.sendRedirect("search.htm");
		} catch (ServiceException e) {
			JscnLogger.error("删除商户类型 [" + id + "] 失败！异常：" + e.getMessage(),
					this.getClass());
			throw new WebException("删除商户类型失败！异常：" + e);
		}
		return null;
	}

	@RequestMapping("search")
	public String search(ModelMap model, Pagination page) throws WebException {
		try {
			List<NewsModel> newsTypeList = NewsModel.getAllNewsType();
			Set<String> column = new HashSet<String>();
			ResultList<MerchantTypeEntity> bank = merchantTypeService.queryMerchantType("",page, column);
			model.addAttribute("bank", bank);
			model.put("newsType", newsTypeList);
		} catch (ServiceException e) {
			JscnLogger.error("查询商户类型信息失败！异常：" + e.getMessage(), this.getClass());
			throw new WebException("查询商户类型信息失败！异常：" + e);
		}
		return "page/main/merchant/merchantTypeList";
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
