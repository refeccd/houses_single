package com.liaozan.web.controller;

import com.liaozan.biz.service.AgencyService;
import com.liaozan.biz.service.HouseService;
import com.liaozan.biz.service.RecommandService;
import com.liaozan.common.constants.CommonConstants;
import com.liaozan.common.model.House;
import com.liaozan.common.model.User;
import com.liaozan.common.page.PageData;
import com.liaozan.common.page.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/16
 */
@Controller
@RequestMapping("agency")
public class AgencyController {

	@Autowired
	private AgencyService agencyService;
	@Autowired
	private HouseService houseService;
	@Autowired
	private RecommandService recommandService;

	@GetMapping("agentList")
	public String agencyList (Integer pageSize, Integer pageNum, ModelMap modelMap) {
		PageData<User> pageData = agencyService.getAllAgent(PageParams.build(pageSize, pageNum));
		List<House> hotHouse = recommandService.getHotHouse(CommonConstants.RECOM_SIZE);
		modelMap.put("recomHouses", hotHouse);
		modelMap.put("ps", pageData);
		return "/user/agent/agentList";
	}

	@GetMapping("agentDetail/{id}")
	public String agentDetail (@PathVariable Long id, ModelMap modelMap) {
		User agentDetail = agencyService.getAgentDetail(id);
		House query = new House();
		query.setUserId(id);
		query.setBookmarked(false);
		PageData<House> housePageData = houseService.queryHouse(query, new PageParams(3, 1));
		if (housePageData != null) {
			modelMap.put("bindHouses", housePageData.getList());
		}
		List<House> hotHouse = recommandService.getHotHouse(CommonConstants.RECOM_SIZE);
		modelMap.put("recomHouses", hotHouse);
		modelMap.put("agent", agentDetail);
		return "/user/agent/agentDetail";
	}
}
