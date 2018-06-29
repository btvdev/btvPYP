package com.btvpyp.utils;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONObjectUtils {
	
	/**
	 * 列表转json
	 * @param list
	 * @return
	 */
	public static String listToJson(List<?> list) {
		String json = JSONArray.fromObject(list).toString();
		return json;
	}
	
	/**
	 * json转列表
	 * @param json
	 * @return
	 */
	public static List<Object> jsonToList(String json) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		List<Object> list = (List<Object>)JSONArray.toCollection(jsonArray);
		return list;		
	}
	
	/**
	 * JAVABean对象转成json字符串
	 * @param object
	 * @return
	 */
	public static String objectToJsonString(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}
	
	/**
	 * json字符串转成JAVABean
	 * @param jsonString
	 * @return
	 */
	public static Object jsonStringToObject(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Object object = (Object)JSONObject.toBean(jsonObject, Object.class);
		return object;
	}
}
