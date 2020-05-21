package com.youke.common.spring.advice;


import com.youke.result.BaseAPIExcution;
import com.youke.utils.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class ExceptionAdvice {
	private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler(value = {BindException.class})
	@ResponseBody
	public ErrorExcution bindException(BindException ex, HttpServletRequest request) {
		logger.error(errorMsg(), ex);
		if (!logger.isDebugEnabled()) {
			return getErrors();
		}
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		StringBuffer msg = new StringBuffer();
		for (FieldError fieldError : fieldErrors) {
			msg.append(fieldError.getDefaultMessage());
		}
		return new ErrorExcution(msg.toString());
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	@ResponseBody
	public ErrorExcution methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
		logger.error(errorMsg(), ex);
		if (!logger.isDebugEnabled()) {
			return getErrors();
		}
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		StringBuffer msg = new StringBuffer();
		for (FieldError fieldError : fieldErrors) {
			msg.append(fieldError.getDefaultMessage());
			msg.append(";");
		}
		return new ErrorExcution(msg.toString());
	}
	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseBody
	public ErrorExcution ConstraintViolationExceptionHandle(ConstraintViolationException e) {
		logger.error(errorMsg(), e);
		if (!logger.isDebugEnabled()) {
			return getErrors();
		}
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		StringBuilder msg = new StringBuilder();
		for (ConstraintViolation<?> violation : violations) {
			msg.append(violation.getMessage());
			msg.append(";");
		}
		return new ErrorExcution(msg.toString());
	}
	
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public ErrorExcution handleException(Exception e, HttpServletResponse response) {
		logger.error(errorMsg(),e);
		if (!logger.isDebugEnabled()) {
			int status = response.getStatus();
			return getErrors();
		}
		return new ErrorExcution(e.getMessage());
	}
	
		public static class ErrorExcution extends BaseAPIExcution {
			public ErrorExcution(String msg) {
				this.msg = msg;
			}
	}
	
	private ErrorExcution getErrors() {
		return new ErrorExcution("系统异常");
	}
	
	private String errorMsg() {
		String path = RequestUtil.getCurrentServletPath();
		String method = RequestUtil.getCurrentMethod();
		return "请求 " + method + " " + path + " 接口时发生异常:";
	}
	
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
	    return new MethodValidationPostProcessor();
	}

}
