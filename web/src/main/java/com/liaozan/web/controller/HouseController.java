package com.liaozan.web.controller;

import com.liaozan.biz.service.AgencyService;
import com.liaozan.biz.service.CityService;
import com.liaozan.biz.service.HouseService;
import com.liaozan.biz.service.RecommandService;
import com.liaozan.common.constants.CommonConstants;
import com.liaozan.common.constants.HouseUserType;
import com.liaozan.common.model.House;
import com.liaozan.common.model.HouseUser;
import com.liaozan.common.model.User;
import com.liaozan.common.model.UserMsg;
import com.liaozan.common.page.PageData;
import com.liaozan.common.page.PageParams;
import com.liaozan.common.result.ResultMsg;
import com.liaozan.web.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	@Autowired
	private RecommandService recommandService;
	@Autowired
	private CityService cityService;

	@RequestMapping(value = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public String houseList(Integer pageSize, Integer pageNum, House query, ModelMap modelMap) {
		PageData<House> housePageData = houseService.queryHouse(query, PageParams.build(pageSize, pageNum));
		List<House> recommandHouses = recommandService.getHotHouse(CommonConstants.RECOM_SIZE);
		modelMap.put("recomHouses", recommandHouses);
		modelMap.put("ps", housePageData);
		modelMap.put("vo", query);
		return "/house/listing";
	}

	@GetMapping("detail/{id}")
	public String houseDetail(@PathVariable Long id, ModelMap modelMap) {
		House house = houseService.queryOneHouse(id);
		HouseUser houseUser = houseService.getHouseUser(id);
		recommandService.increase(id);
		if (houseUser.getUserId() != null && !houseUser.getUserId().equals(0L)) {
			modelMap.put("agent", agencyService.getAgentDetail(houseUser.getUserId()));
		}
		List<House> recommandHouses = recommandService.getHotHouse(CommonConstants.RECOM_SIZE);
		modelMap.put("recomHouses", recommandHouses);
		modelMap.put("house", house);
		return "/house/detail";
	}

	@GetMapping("leaveMsg")
	public String houseMsg(UserMsg userMsg) {
		houseService.addHouseMsg(userMsg);
		return "redirect:/house/detail/" + userMsg.getHouseId();
	}

	@GetMapping("toAdd")
	public String toAdd(ModelMap modelMap) {
		modelMap.put("citys", cityService.getAllCitys());
		modelMap.put("communitys", houseService.getAllCommunitys());
		return "/house/add";
	}

	@GetMapping("add")
	public String add(House house) {
		User user = UserContext.getUser();
		house.setState(CommonConstants.HOUSE_STATE_UP);
		houseService.addHouse(house, user);
		return "redirect:/house/ownlist";
	}

	@GetMapping("ownlist")
	public String ownList(House house, Integer pageNum, Integer pageSize, ModelMap modelMap) {
		User user = UserContext.getUser();
		house.setUserId(user.getId());
		house.setBookmarked(false);
		modelMap.put("ps", houseService.queryHouse(house, PageParams.build(pageSize, pageNum)));
		modelMap.put("pageType", "own");
		return "/house/ownlist";
	}

	@GetMapping("rating")
	@ResponseBody
	public ResultMsg houseRate(Double rating, Long id) {
		houseService.updateRating(id, rating);
		return ResultMsg.successMsg("ok");
	}

	@PostMapping("bookmark")
	@ResponseBody
	public ResultMsg bookmark(Long id) {
		User user = UserContext.getUser();
		houseService.bindUser2House(id, user.getId(), true);
		return ResultMsg.successMsg("ok");
	}

	@PostMapping("unbookmark")
	@ResponseBody
	public ResultMsg unbookmark(Long id) {
		User user = UserContext.getUser();
		houseService.unbindUser2House(id, user.getId(), HouseUserType.BOOKMARK);
		return ResultMsg.successMsg("ok");
	}

	@GetMapping("del/{id}")
	public String delsale(@PathVariable Long id, String houseType) {
		User user = UserContext.getUser();
		HouseUserType houseUserType = "own".equals(houseType) ? HouseUserType.SALE : HouseUserType.BOOKMARK;
		houseService.unbindUser2House(id, user.getId(), houseUserType);
		return "redirect:/house/ownlist";
	}

	@GetMapping("bookmarked")
	public String bookmarkList(House house, Integer pageNum, Integer pageSize, ModelMap modelMap) {
		User user = UserContext.getUser();
		house.setBookmarked(true);
		house.setUserId(user.getId());
		modelMap.put("ps", houseService.queryHouse(house, PageParams.build(pageSize, pageNum)));
		modelMap.put("pageType", "book");
		return "/house/ownlist";
	}
}
