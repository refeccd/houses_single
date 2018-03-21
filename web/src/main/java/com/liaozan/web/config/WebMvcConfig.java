package com.liaozan.web.config;

import com.liaozan.web.interceptor.AuthActionInterceptor;
import com.liaozan.web.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private AuthInterceptor authInterceptor;
	@Autowired
	private AuthActionInterceptor authActionInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor).excludePathPatterns("/static").addPathPatterns("/**");
		registry.addInterceptor(authActionInterceptor).addPathPatterns("/house/toAdd").addPathPatterns("/accounts/profile").addPathPatterns("/accounts/profileSubmit").addPathPatterns("/house/bookmarked")
				.addPathPatterns("/house/del").addPathPatterns("/house/ownlist").addPathPatterns("/house/add").addPathPatterns("/house/toAdd").addPathPatterns("/agency/agentMsg").addPathPatterns("/comment/leaveComment")
				.addPathPatterns("/comment/leaveBlogComment");
	}

	@Override
	public void configureViewResolvers (ViewResolverRegistry registry) {
		registry.jsp();
	}
}
