package com.ep.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author YYX
 *  标签
 */

public class SpecializeBo implements java.io.Serializable {
	private Integer id;
	private String specializeName;//标签名称
	private Integer state; //是否启用 0:启用，1：禁用
	private Integer pageSize;
	private Integer pageNumber;
	
	public SpecializeBo() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpecializeName() {
		return specializeName;
	}

	public void setSpecializeName(String specializeName) {
		this.specializeName = specializeName;
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