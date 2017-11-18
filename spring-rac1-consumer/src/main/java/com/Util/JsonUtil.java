package com.Util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public static String objectToJson(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }
}
