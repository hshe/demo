package com.its.common.utils;

import java.lang.reflect.Method;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	/**
	 * 对象转换成json
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data){
		try {
			ObjectMapper map = new ObjectMapper();
			String string = map.writeValueAsString(data);
			return string;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * json对象转成对象
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> beanType){
		try {
			T t = new ObjectMapper().readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * json转成 list
	 * @return
	 */
	public static <T>List<T> jsonToList(String jsonData, Class<T> beanType){
		ObjectMapper map = new ObjectMapper();
		JavaType javaType = map.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = map.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getJsonByKey(String content, String key){
		try {
			ObjectMapper map = new ObjectMapper();
			JsonNode node = map.readTree(content);
			if(node.has(key)){
				return node.get(key).toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param beforeContent name:name1
	 * @param afterContent name:name2
	 * 
	 * value1 --> null 
	 * @return	name1->name2
	 */
	public static String getJsonByPojo2Pojo(Object beforeContent, Object afterContent){
		if(null == beforeContent){
			return "bug: "+objectToJson(afterContent);
		}
		if(null == afterContent){
			return "bug: "+objectToJson(beforeContent);
		}
		String returnValues = "";
		try {
//			Field[] beforeContentFields = beforeContent.getClass().getDeclaredFields();
//			Field[] afterContentFields = afterContent.getClass().getDeclaredFields();
//			for(Field beforeContentField : beforeContentFields){
//				for(Field afterContentField : afterContentFields){
//					if(beforeContentField.getName().equals(afterContentField.getName())){
//						returnValues += beforeContentField.get(beforeContentField.getName())+" -> "+afterContentField.get(beforeContentField.getName());
//						
//					}
//				}
//			}
			Method[] beforeContentMethods = beforeContent.getClass().getDeclaredMethods();
			Method[] afterContentMethods = afterContent.getClass().getDeclaredMethods();
			for(Method beforeContentMethod : beforeContentMethods){
				for(Method afterContentMethod : afterContentMethods){
					if(null != beforeContentMethod && beforeContentMethod.getName().equals(afterContentMethod.getName()) &&  afterContentMethod.getName().toLowerCase().contains("get")){
						if(null != beforeContentMethod && beforeContentMethod.getName().toLowerCase().contains("password")){
							returnValues += " password -> ******";
							continue;
						}
						Object invoke = beforeContentMethod.invoke(beforeContent, null);
						Object invoke2 = afterContentMethod.invoke(afterContent, null);
						if(null != invoke && !invoke.equals(invoke2) && null != invoke2){
							returnValues += afterContentMethod.getName().toLowerCase().replaceAll("get", "")+": "+invoke +" -> " + invoke2 +"; ";
							break;
						}
					}
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValues;
	}
	/**
	 * 相同字段赋值.
	 * @param source
	 * @param dest
	 */
	/*public static void copyObject2Object(Object source, Object dest){
		if(null == source || null == dest){
			return ;
		}
		Method[] sourceMethods = source.getClass().getDeclaredMethods();
		Method[] destMethods = dest.getClass().getDeclaredMethods();
		for(Method method : sourceMethods){
			
		}
		
		
		
	}*/
}
