package com.liaozan.web.controller;

import com.liaozan.biz.service.AgencyService;
import com.liaozan.biz.service.HouseService;
import com.liaozan.common.model.House;
import com.liaozan.common.model.HouseUser;
import com.liaozan.common.model.UserMsg;
import com.liaozan.common.page.PageData;
import com.liaozan.common.page.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/15
 */
@Controller
@RequestMapping("house")
public class HouseController {
	@Autowired
	private HouseService houseService;
	@Autowired
	private AgencyService agencyService;

	@RequestMapping("list")
	public String houseList(Integer pageSize, Integer pageNum, House query, ModelMap modelMap) {
		PageData<House> housePageData = houseService.queryHouse(query, PageParams.build(pageSize, pageNum));
		modelMap.put("ps", housePageData);
		modelMap.put("vo", query);
		return "/house/listing";
	}

	@RequestMapping("detail/{id}")
	public String houseDetail(@PathVariable Long id, ModelMap modelMap) {
		House house = houseService.queryOneHouse(id);
		HouseUser houseUser = houseService.getHouseUser(id);
		if (houseUser.getUserId() != null && !houseUser.getUserId().equals(0L)) {
			modelMap.put("agent", agencyService.getAgentDetail(houseUser.getUserId()));
		}
		modelMap.put("house", house);
		return "/house/detail";
	}

	@RequestMapping("leaveMsg")
	public String houseMsg(UserMsg userMsg) {
		houseService.addHouseMsg(userMsg);
		return "redirect:/house/detail/" + userMsg.getId();
	}
}
