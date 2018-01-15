package com.liaozan.web.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/15
 */
@ControllerAdvice
public class ErrorHandleControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandleControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public String handleError(HttpServletRequest request, HttpSession session, Exception e) {
		LOGGER.error(e.getMessage());
		LOGGER.error(request.getRequestURI());
		return "error/500";
	}

}
