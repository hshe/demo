package com.its.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiUtils {
	
	//根据LigerDataGridResult和对应的字段名称.返回wk
	public static HSSFWorkbook getwk(String fields, String data){
		//JsonUtils.getJsonByKey(data, "rows");
		/**
		 * json
		 * 转map/list
		 * 
		 * TODO
		 * rowFields
		 * dataFields
		 * 
		 * 
		 */
		List<Object> dataLists = JsonUtils.jsonToList(data, Object.class);
		
		Map<String, String> fieldMap = JsonUtils.jsonToPojo(JsonUtils.getJsonByKey(fields, "fields"), LinkedHashMap.class);
//		JsonUtils.jsonToList(JsonUtils.getJsonByKey(fields, "fields"), HashMap.class);
		
		ArrayList<String> rowlists = new ArrayList<String>();
		
		for(String key : fieldMap.keySet()){
			rowlists.add(fieldMap.get(key));
		}
		
		
		
		
		HSSFWorkbook wk = new HSSFWorkbook();
		HSSFSheet sheet = wk.createSheet(JsonUtils.getJsonByKey(fields, "fileName"));
//		sheet.setColumnWidth(0, 20*256);
		//sheet.setDefaultColumnWidth(30);
		//sheet.setDefaultColumnWidth(200);
		
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wk.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
//		style.setWrapText(wrapped);
		
		//数据头
		for(int i=0;i<rowlists.size();i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(rowlists.get(i));
			cell.setCellStyle(style);
//			sheet.autoSizeColumn((short)i);//对中文无效
			sheet.setColumnWidth(i, 6*256);
			
		}
		//填充数据
		for(int i = 0;i < dataLists.size();i++){
			row = sheet.createRow(i+1);
			Object dataRow = dataLists.get(i);
			HashMap<String, String> rowMap = JsonUtils.jsonToPojo(JsonUtils.objectToJson(dataRow), HashMap.class);
			//数据cell
			int j=0;
			for(String fieldKey : fieldMap.keySet()){
				HSSFCell cell = row.createCell(j++);
				//Time.null boolean
				String value = checkAndGetValue(fieldKey, rowMap.get(fieldKey));
				cell.setCellValue(value);
				cell.setCellStyle(style);
			}
			/*for(int j=0;j<rowlists.size();j++){
				rowMap.get(rowlists.get(j));
				row.createCell(j).setCellValue(rowMap.get(rowlists.get(j)));
			}*/
		}
		
		
		
		
		
		System.err.println(dataLists.size());
		return wk;
	}
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static String checkAndGetValue(String key, Object fieldValues) {
		//null == fieldValues?"":fieldValues.toString()
		String result = null == fieldValues?"":fieldValues.toString();
		if(key.toLowerCase().contains("time") && null != result && !"".equals(result)){
			try {
				if(Long.parseLong(result)>147279571863l)
					result = sdf.format(new Date(Long.parseLong(result)));
			} catch (Exception e) {
//				e.printStackTrace();
				System.err.println("timeFormat Exception:"+e.getMessage());
			}
		}
		return result;
	}
	
}
