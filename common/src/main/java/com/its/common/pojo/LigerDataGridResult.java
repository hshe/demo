package com.its.common.pojo;

import java.util.List;

/**
 * 封装ligterUi结果集.
 * @author hse
 * rows
 * total
 * private int ID;
	private int DepartmentID;
	private String RealName;
	private String DepartmentName;
	private int Sex;
	private int Age;
	private String IncomeDay;
	private String Phone;
	private String Address;
 *
 */
public class LigerDataGridResult {
	private long total;
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
