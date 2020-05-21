package com.youke.common.spring.advice;


import com.youke.common.jackson.time.DateParser;
import com.youke.common.spring.binder.DateBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;

@ControllerAdvice
public class BinderAdvice {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateBinder(new DateParser()));
	}
}
