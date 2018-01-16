package com.liaozan.web.controller;

import com.liaozan.biz.service.UserService;
import com.liaozan.common.constants.CommonConstants;
import com.liaozan.common.model.User;
import com.liaozan.common.result.ResultMsg;
import com.liaozan.common.utils.HashUtils;
import com.liaozan.web.utils.UserHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@Controller
@RequestMapping("accounts")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("register")
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

	@RequestMapping("verify")
	public String verify(String key) {
		boolean result = userService.enable(key);
		if (result) {
			return "redirect:/index?" + ResultMsg.successMsg("激活成功").asUrlParams();
		} else {
			return "redirect:/accounts/register?" + ResultMsg.errorMsg("激活失败，请确认链接是否过期").asUrlParams();
		}
	}

	@RequestMapping("signin")
	public String signin(HttpServletRequest request) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String target = request.getParameter("target");
		if (userName == null || password == null) {
			request.setAttribute("target", target);
			return "/user/accounts/signin";
		}
		User user = userService.auth(userName, password);
		if (user == null) {
			return "redirect:/accounts/signin?target=" + target + "&username=" + userName + "&" + ResultMsg.errorMsg("用户名或密码错误").asUrlParams();
		} else {
			HttpSession session = request.getSession();
			session.setAttribute(CommonConstants.USER_ATTRIBUTE, user);
			return StringUtils.isNotBlank(target) ? "redirect:" + target : "redirect:/index";
		}
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/index";
	}

	@RequestMapping("profile")
	public String profile(HttpServletRequest request, User updateUser) {
		if (updateUser.getEmail() == null) {
			return "/user/accounts/profile";
		}
		userService.updateUser(updateUser);
		User query = new User();
		query.setEmail(updateUser.getEmail());
		List<User> users = userService.getUserByQuery(query);
		request.getSession().setAttribute(CommonConstants.USER_ATTRIBUTE, users.get(0));
		return "redirect:/accounts/profile?" + ResultMsg.successMsg("更新成功").asUrlParams();
	}

	@RequestMapping("changePassword")
	public String changePassword(User user) {
		if (!Objects.equals(user.getNewPassword(), user.getConfirmPasswd())) {
			return "redirect:/accounts/profile?" + ResultMsg.errorMsg("两次密码输入不一致").asUrlParams();
		}
		User auth = userService.auth(user.getEmail(), user.getPasswd());
		if (auth == null) {
			return "redirect:/accounts/profile?" + ResultMsg.errorMsg("密码错误").asUrlParams();
		}
		user.setPasswd(HashUtils.encryPassword(user.getNewPassword()));
		userService.updateUser(user);
		return "redirect:/accounts/profile?" + ResultMsg.successMsg("更新密码成功").asUrlParams();
	}
}
