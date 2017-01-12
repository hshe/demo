package com.its.common.pojo;

import java.util.List;

public class ModelFunResult {
	private int id;
	private String nodeName;
	private String url;
	
	private List<FunctionToModel> list;
	
}

class FunctionToModel{
	private int functionid;
	private String functionname;
	private boolean flag;
	public int getFunctionid() {
		return functionid;
	}
	public void setFunctionid(int functionid) {
		this.functionid = functionid;
	}
	public String getFunctionname() {
		return functionname;
	}
	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}