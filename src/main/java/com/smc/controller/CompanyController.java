package com.smc.controller;

import com.alibaba.fastjson.JSONObject;
import com.smc.entity.CompanyEntity;
import com.smc.entity.IPODetailEntity;
import com.smc.service.CompanyService;
import com.smc.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author BoZhang
 * E-mail:dlzbo@cn.ibm.com
 * @version date：May 12, 2020 7:57:57 PM
*/
@CrossOrigin
@RestController
@RequestMapping("/admin/manage/company")
public class CompanyController {


	@Autowired
	private CompanyService companyService;


	@PostMapping
	public CommonResult newCompany(@RequestBody JSONObject jsonObject) {
		CompanyEntity companyDtl = jsonObject.getObject("company", CompanyEntity.class);
		IPODetailEntity ipoDetail = jsonObject.getObject("ipo", IPODetailEntity.class);
		return companyService.addCompany(companyDtl, ipoDetail);
	}

	@PutMapping
	public CommonResult updateCompany(
			@RequestBody CompanyEntity companyDtl) {
		return companyService.updateCompany(companyDtl);
	}

	@PostMapping("/activeCompany")
	public CommonResult activeCompany(@RequestParam("companyName") String companyName) {
		return companyService.setActiveForCompany(companyName, "1");
	}

	@PostMapping("/inactiveCompany")
	public CommonResult inactiveCompany(@RequestParam("companyName") String companyName) {
		return companyService.setActiveForCompany(companyName, "0");
	}

}
