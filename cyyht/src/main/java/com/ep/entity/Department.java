package com.ep.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String departmentName; //科室名称
	private Integer parentId;//父菜单ID
	private String logoUrl;   //科室logoUrl
	private Integer isCommon; //是否常见 0：否 1：是
	//private String creatTime;//创建时间
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "departmentName", nullable = false)
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Column(name = "parentId", nullable = false)
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	@Column(name = "logoUrl", nullable = true)
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	@Column(name = "isCommon", nullable = false)
	public Integer getIsCommon() {
		return isCommon;
	}
	public void setIsCommon(Integer isCommon) {
		this.isCommon = isCommon;
	}
	
	
	
	
	
	
	
	
	
}
