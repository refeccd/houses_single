package com.liaozan.web.controller;

import com.liaozan.biz.service.UserService;
import com.liaozan.common.model.User;
import com.liaozan.common.result.ResultMsg;
import com.liaozan.web.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("accounts/register")
	public String accountRegister(User account, ModelMap modelMap) {
		if (account == null || account.getName() == null) {
			return "/user/accounts/register";
		}
		ResultMsg resultMsg = UserHelper.validate(account);
		if (resultMsg.isSuccess() && userService.addAccount(account)) {
			modelMap.put("email", account.getEmail());
			return "/user/accounts/registerSubmit";
		}
		return "redirect:/user/accounts/register?" + resultMsg.asUrlParams();
	}

}
