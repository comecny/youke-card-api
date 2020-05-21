package com.youke.common.jackson.time;

import com.youke.common.exception.json.UsualParseException;
import com.youke.common.jackson.Parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser implements Parser<Date> {
	private static List<PatternFormatterMatcher> matchers;
	
	static {
		matchers = new ArrayList<>();
		matchers.add(new PatternFormatterMatcher("\\d{4}-\\d{2}-\\d{2}", "yyyy-MM-dd"));
		matchers.add(new PatternFormatterMatcher("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", "yyyy-MM-dd hh:mm:ss"));
	}
	@Override
	public Date parse(String value) throws UsualParseException {
		String formatter = findFormatter(value);
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		try {
			Date date = sdf.parse(value);
			return date;
		} catch (ParseException e) {
			throw new UsualParseException(e);
		}
	}
	
	private static String findFormatter(String value) throws UsualParseException{
		for (PatternFormatterMatcher patternFormatterMatcher : matchers) {
			if (patternFormatterMatcher.match(value)) {
				return patternFormatterMatcher.getFormatter();
			}
		}
		throw new UsualParseException("日期字符串: " + value + " 不符合系统要求的日期格式");
	}

	private static class PatternFormatterMatcher{
		private int length;
		private Pattern pattern;
		private String formatter;
		PatternFormatterMatcher(String pattern, String formatter){
			this.pattern = Pattern.compile(pattern);
			this.formatter = formatter;
			this.length = formatter.length();
		}
		boolean match(String text) {
			if (text.length()!=this.length) {
				return false;
			}
			Matcher m = pattern.matcher(text);
			return m.matches();
		}
		public String getFormatter() {
			return formatter;
		}
	}
}
