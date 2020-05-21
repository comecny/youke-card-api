package com.youke.common.spring.binder;


import com.youke.common.exception.json.UsualParseException;
import com.youke.common.jackson.Parser;
import com.youke.common.jackson.time.DateParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateBinder extends PropertyEditorSupport {
	
	private Logger logger = LoggerFactory.getLogger(DateBinder.class);
	
	private Parser<Date> parser;
	
	public DateBinder(DateParser parser) {
		this.parser = parser;
	}
	@Override
	public String getAsText() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = (Date) getValue();
		return sdf.format(date);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Date date;
		try {
			date = parser.parse(text);
			setValue(date);
		} catch (UsualParseException e) {
			logger.error("字符串 " + text + " 转化成时间失败", e);
		}
	}
}
