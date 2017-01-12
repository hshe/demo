package com.its.common.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonResult {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	//响应状态/响应消息/响应中的数据
	private String msg;
	private Object data;
	private int status;
	
	public CommonResult() {
		// TODO Auto-generated constructor stub
	}
	public CommonResult(Object data){
		this.status=200;
		this.msg = "OK";
		this.data = data;
	}
	public CommonResult(int status, String msg){
		this.status = status;
		this.msg = msg;
	}
	public CommonResult(int status2, String msg2, Object data2) {
		this.status = status2;
		this.msg = msg2;
		this.data = data2;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public static CommonResult build(int status, String msg, Object data){
		//建立规则
		return new CommonResult(status, msg, data);
	}
	public static CommonResult build(int status, String msg){
		//建立规则
		return new CommonResult(status, msg);
	}
	public static CommonResult ok(Object data){
		return new CommonResult(data);
	}
	public static CommonResult ok(){
		return new CommonResult(null);
	}
	/**
	 * 将json结果中的数据拿出来封装到commonResult
	 * @param jsonData
	 * @param clazz
	 * @return
	 */
	public static CommonResult formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if(null == clazz){
				return MAPPER.readValue(jsonData, CommonResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if(data.isObject()){
				obj = MAPPER.readValue(data.traverse(), clazz);
			}else {
				obj = MAPPER.readValue(data.asText(), clazz);
			}
			return CommonResult.build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
