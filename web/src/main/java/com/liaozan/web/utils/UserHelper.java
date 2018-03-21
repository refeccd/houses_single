package com.liaozan.web.utils;

import com.google.common.base.Objects;
import com.liaozan.common.model.User;
import com.liaozan.common.result.ResultMsg;
import org.apache.commons.lang3.StringUtils;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
public class UserHelper {

	private static final Integer PASSWORD_LENGTH = 6;

	public static ResultMsg validate(User accout) {
		if (StringUtils.isBlank(accout.getEmail())) {
			return ResultMsg.errorMsg("Email有误");
		}
		if (StringUtils.isBlank(accout.getConfirmPasswd()) || StringUtils.isBlank(accout.getPasswd()) || !accout.getPasswd().equals(accout.getConfirmPasswd())) {
			return ResultMsg.errorMsg("请输入正确的密码");
		}
		if (accout.getPasswd().length() < PASSWORD_LENGTH) {
			return ResultMsg.errorMsg("密码不能小于" + PASSWORD_LENGTH + "位");
		}
		return ResultMsg.successMsg("");
	}

	public static ResultMsg validateResetPassword(String key, String password, String confirmPassword) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
			return ResultMsg.errorMsg("参数有误");
		}
		if (!Objects.equal(password, confirmPassword)) {
			return ResultMsg.errorMsg("密码必须与确认密码一致");
		}
		return ResultMsg.successMsg("");
	}

}
