package com.liaozan.web.pointcut;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/16
 */
@Aspect
@Component
public class RequestLogger {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogger.class);

	@Pointcut("execution(* *com.liaozan.web.controller.*(..))")
	public void pointCut() {
	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes != null) {
			HttpServletRequest request = attributes.getRequest();
			try {
				LOGGER.debug("【URL】: {}", request.getRequestURL().toString());
				LOGGER.debug("【HTTP_METHOD】: {}", request.getMethod());
				LOGGER.debug("【IP】: {}", request.getRemoteAddr());
				LOGGER.debug("【QUERY_PARAMS】: {}", processParam(request));
				LOGGER.debug("【CLASS_METHOD】: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
				LOGGER.debug("【ARGS】: {}", joinPoint.getArgs());
				result = joinPoint.proceed(joinPoint.getArgs());
				LOGGER.debug("【RESULT】:{}",result);
			} catch (Throwable e) {
				LOGGER.error("【ERROR】: {} --> {}", e.getMessage(), e);
				throw e;
			}
			return result;
		}
		return null;
	}

	private static String processParam(HttpServletRequest request) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(request.getParameterMap());
	}

}
