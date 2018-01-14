package com.liaozan.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@Controller
public class HomePageController {
	/*@Autowired
	private RecommendService recommendService;*/

	@RequestMapping("index")
	public String accountsRegister(ModelMap modelMap){
		/*List<House> houses =  recommendService.getLastest();
		modelMap.put("recomHouses", houses);*/
		return "/homepage/index";
	}


	@RequestMapping("")
	public String index(ModelMap modelMap){
		return "redirect:/index";
	}
}
