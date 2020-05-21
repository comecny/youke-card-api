package com.youke.common.jackson;


import com.youke.common.exception.json.UsualParseException;

public interface Parser<T> {

	T parse(String value) throws UsualParseException;
}
