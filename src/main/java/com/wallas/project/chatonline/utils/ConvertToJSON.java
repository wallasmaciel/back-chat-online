package com.wallas.project.chatonline.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class ConvertToJSON {
	public static String parse(Object obj) throws Exception {
	    ObjectMapper mapper = new ObjectMapper();
	    FilterProvider filters = new SimpleFilterProvider();  
	    ObjectWriter writer = mapper.writer(filters);  
	    return writer.writeValueAsString(obj);
	}
}
