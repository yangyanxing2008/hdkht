package com.ep.bo;

/**
 * 
 * @author YYX
 *  职称
 */

public class PositionalBo {
	private Integer id;
	private String positionalName;//职称名称
	private Integer state; //是否启用 0:启用，1：禁用
	private Integer pageSize;
	private Integer pageNumber;
	
	public PositionalBo() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPositionalName() {
		return positionalName;
	}

	public void setPositionalName(String positionalName) {
		this.positionalName = positionalName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	
}