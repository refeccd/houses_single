package com.liaozan.web.interceptor;

import com.liaozan.common.model.User;
import com.liaozan.common.result.ResultMsg;
import com.liaozan.web.utils.UserContext;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@Component
public class AuthActionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = UserContext.getUser();
		if (user == null) {
			String message = "请先登录";
			String target = URLEncoder.encode(request.getRequestURL().toString(), "UTF-8");
			if (HttpMethod.GET.matches(request.getMethod())) {
				response.sendRedirect("/accounts/signin?" + ResultMsg.errorMsg(message).asUrlParams() + "&target=" + target);
				return false;
			} else {
				response.sendRedirect("/accounts/signin?" + ResultMsg.errorMsg(message).asUrlParams());
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

	}
}
