package com.wallas.project.chatonline.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class JSONConvert {
	public static String parse(Object obj) throws JsonProcessingException {
	    ObjectMapper mapper = new ObjectMapper();
	    FilterProvider filters = new SimpleFilterProvider();  
	    ObjectWriter writer = mapper.writer(filters);  
	    return writer.writeValueAsString(obj);
	}
}
