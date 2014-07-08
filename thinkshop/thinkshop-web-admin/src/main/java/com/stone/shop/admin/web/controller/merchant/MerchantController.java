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
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stone.shop.admin.exception.ServiceException;
import com.stone.shop.admin.service.merchant.IMerchantService;
import com.stone.shop.admin.service.merchant.IMerchantTypeService;
import com.stone.shop.admin.service.model.BooleanModel;
import com.stone.shop.admin.utils.exception.WebException;
import com.stone.shop.admin.utils.log.JscnLogger;
import com.stone.shop.admin.utils.term.Term;
import com.stone.shop.admin.utils.term.TermManager;
import com.stone.shop.admin.web.controller.BaseController;
import com.stone.shop.base.common.page.Pagination;
import com.stone.shop.base.common.utils.DateTimeUtils;
import com.stone.shop.base.common.utils.StringUtils;
import com.stone.shop.domain.merchant.MerchantEntity;
import com.stone.shop.domain.merchant.MerchantTypeEntity;
import com.stone.shop.domain.persistent.ResultList;

@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

	@Autowired
	public IMerchantService merchantService;
	
	@Autowired
	public IMerchantTypeService typeService;
	
	@RequestMapping("toadd")
	public String toAdd(String id, ModelMap model,Pagination page) 
	{
		Set<String> column = new HashSet<String>();
		ResultList<MerchantTypeEntity> typeList = typeService.queryMerchantType("1",page, column);
		if (StringUtils.isNotEmpty(id)) {
			MerchantEntity entity = merchantService.getMerchant(id);
			model.addAttribute("entity", entity);
		}
		List<Term> list = null;
		try {
			list = TermManager.getList("merchantType");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		model.put("typeList", typeList);
		model.put("list", list);
		return "page/main/merchant/addMerchant";
	}

	@RequestMapping("saveupdate")
	public String saveOrUpdate(MerchantEntity entity, //BindingResult result,
			HttpServletRequest request, HttpServletResponse response,@RequestParam() MultipartFile[] file)
			throws WebException, IOException {
		try {
			String url = request.getHeader("Referer");
//			getValidError(result);
			for(int i=0;i<file.length;i++) {
				if(file[i].isEmpty()) continue;
				if(i==0){
					entity.setLogo("/upload-file/"+file[i].getOriginalFilename());
				}
				if(i==1){
					entity.setPop_pic1("/upload-file/"+file[i].getOriginalFilename());
				}
				if(i==2){
					entity.setPop_pic2("/upload-file/"+file[i].getOriginalFilename());
				}
				if(i==3){
					entity.setPop_pic3("/upload-file/"+file[i].getOriginalFilename());
				}
				if(i==4){
					entity.setPop_pic4("/upload-file/"+file[i].getOriginalFilename());
				}
				System.out.println(file[i].getContentType()+","+file[i].getName()+","+file[i].getOriginalFilename());
				String realpath = request.getSession().getServletContext().getRealPath("/upload-file/");
				System.out.println(realpath);
				FileUtils.copyInputStreamToFile(file[i].getInputStream(), new File(realpath+"/"+file[i].getOriginalFilename()));
			}
			if (StringUtils.isNotEmpty(entity.getId())) {
				merchantService.updateMerchant(entity);
				JscnLogger.info(
						"更新商户 [" + entity.getId() + ":" + entity.getName()+ "] 成功！",
						this.getClass());
			} else {
				entity.setId(StringUtils.getUUID());
				String[] idname = entity.getMerchant_type_id().split("#");
				entity.setMerchant_type_id(idname[0]);
				entity.setMerchant_type_name(idname[1]);
				merchantService.addMerchant(entity);
				JscnLogger.info(
						"新增商户 [" + entity.getId() + ":" + entity.getName()+"] 成功！",
						this.getClass());
			}
			response.sendRedirect(url);
			return null;
		} catch (Exception e) {
			JscnLogger.error("新增商户 [" + entity.getId() + ":" + entity.getName()+ "] 失败！异常：" + e.getMessage(),
					this.getClass());
			throw new WebException("新增商户失败！异常：" + e.getMessage());
		}

	}

	@RequestMapping("delete")
	public String delete(String id, HttpServletResponse response)
			throws WebException, IOException {
		try {
			merchantService.deleteMerchant(id);
			JscnLogger.info("删除商户[" + id + "] 成功！", this.getClass());
			response.sendRedirect("search.htm");
		} catch (ServiceException e) {
			JscnLogger.error("删除商户 [" + id + "] 失败！异常：" + e.getMessage(),
					this.getClass());
			throw new WebException("删除商户失败！异常：" + e);
		}
		return null;
	}

	@RequestMapping("search")
	public String search(String is_lock, String merchant_type_name,String name,ModelMap model, Pagination page) throws WebException {
		try {
			List<BooleanModel> allType = BooleanModel.getAllType();
			List<Term> list = null;
			try {
				list = TermManager.getList("merchantType");
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			Set<String> column = new HashSet<String>();
			ResultList<MerchantEntity> bank = merchantService.queryMerchant(is_lock,merchant_type_name,name,page, column);
			model.addAttribute("bank", bank);
			model.addAttribute("is_lock", is_lock);
			model.addAttribute("merchant_type_name", merchant_type_name);
			model.addAttribute("name", name);
			model.put("allType", allType);
			model.put("list", list);
		} catch (ServiceException e) {
			JscnLogger.error("查询商户信息失败！异常：" + e.getMessage(), this.getClass());
			throw new WebException("查询商户信息失败！异常：" + e);
		}
		return "page/main/merchant/merchantList";
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
