package com.liaozan.web.interceptor;

import com.google.common.base.Joiner;
import com.liaozan.common.constants.CommonConstants;
import com.liaozan.common.model.User;
import com.liaozan.common.result.ResultMsg;
import com.liaozan.web.utils.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final String PATTEN_STATIC = "/static";
	private static final String PATTEN_ERROR = "/error";
	private static final String PATTEN_TARGET = "target";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		parameterMap.forEach((k, v) -> {
			if (k.equals(ResultMsg.ERROR_MSG_KEY) || k.equals(ResultMsg.SUCCESS_MSG_KEY) || k.equals(PATTEN_TARGET)) {
				request.setAttribute(k, Joiner.on(",").join(v));
			}
		});
		String requestURI = request.getRequestURI();
		if (requestURI.startsWith(PATTEN_STATIC) || requestURI.startsWith(PATTEN_ERROR)) {
			return true;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(CommonConstants.USER_ATTRIBUTE);
		if (user != null) {
			UserContext.setUser(user);
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		UserContext.remove();
	}
}
