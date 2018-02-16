package com.liaozan.web.controller;

import com.liaozan.biz.service.RecommandService;
import com.liaozan.common.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@Controller
public class HomePageController {
	@Autowired
	private RecommandService recommendService;

	@RequestMapping("index")
	public String accountsRegister(ModelMap modelMap) {
		return "redirect:/";
	}

	@RequestMapping("")
	public String index(ModelMap modelMap) {
		List<House> houses = recommendService.getLastest();
		modelMap.put("recomHouses", houses);
		return "/homepage/index";
	}
}
