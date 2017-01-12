package com.its.common.var;

import java.util.concurrent.ConcurrentHashMap;

public class VarList {

	private static ConcurrentHashMap<String, String> clist;
//	private static List<String> list;

	public static void clearList(){
		clist.clear();
	}
	public static void addValue(String key){
		clist.put(key, null);
	}
	public static boolean checkValue(String key){
		if(clist.contains(key)){
			return true;
		}
		return false;
	}

	
	
}
