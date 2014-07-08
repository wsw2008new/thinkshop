package com.stone.shop.common.utils;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stone.shop.common.exception.SystemException;

public class JsonUtil {

	public static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	public static String objectToJson(Object bean) {
		try {
			// JsonFactory jsonFactory = new MappingJsonFactory();
			ObjectMapper objectMapper = new ObjectMapper();
			// ByteArrayOutputStream out = new ByteArrayOutputStream();
			// JsonGenerator generator = jsonFactory.createJsonGenerator(out,
			// JsonEncoding.UTF8);
			objectMapper.getSerializationConfig().setSerializationInclusion(
					JsonSerialize.Inclusion.NON_NULL);
			String json = objectMapper.writeValueAsString(bean);
			return json;
			// byte[] data = out.toByteArray();
			// out.close();
			// return new String(data, "UTF-8");
		} catch (IOException e) {
			logger.error(e.getMessage(), e, JsonUtil.class);
		}
		return null;
	}

	public static Map<?, ?> jsonToMap(String json) throws SystemException {
		try {
			if (StringUtils.isEmpty(json)) {
				return null;
			}
			ObjectMapper om = new ObjectMapper();
			Map<?, ?> map = om.readValue(json, Map.class);
			return map;
		} catch (Exception e) {
			logger.error(e.getMessage(), e, JsonUtil.class);
			throw new SystemException(e);
		}
	}

	public static <T> T jsonToObject(String json, Class<T> classs)
			throws SystemException {
		try {
			if (StringUtils.isEmpty(json)) {
				return null;
			}
			ObjectMapper objectMapper = new ObjectMapper();
			T map = objectMapper.readValue(json, classs);
			return map;
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

}
