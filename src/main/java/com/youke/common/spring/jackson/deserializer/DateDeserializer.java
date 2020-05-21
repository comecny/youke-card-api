package com.youke.common.spring.jackson.deserializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.youke.common.exception.json.UsualParseException;
import com.youke.common.jackson.Parser;
import com.youke.common.jackson.time.DateParser;

import java.io.IOException;
import java.util.Date;


public class DateDeserializer extends JsonDeserializer<Date> {
	private Parser<Date> parser;

	public DateDeserializer(DateParser parser) {
		this.parser = parser;
	}

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonToken t = p.getCurrentToken();
		if (t!= JsonToken.VALUE_STRING) {
			return null;
		}
		String value = p.getText().trim();
		if(value==null) {
			return null;
		}else {
			Date date;
			try {
				date = parser.parse(value);
			} catch (UsualParseException e) {
				throw new IOException(e);
			}
			return date;
		}
	}

	@Override
	public Class<?> handledType() {
		return Date.class;
	}

}
