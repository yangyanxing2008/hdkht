package com.ep.bo;



public class DepartmentBo {
	private Integer id;
	private String departmentName; //菜单名称
	private Integer parentId;//父菜单ID
	private String logoUrl;   //科室logoUrl
	private Integer isCommon; //是否常见 0：否 1：是
	private String creatTime;//创建时间
	private Integer pageSize;
	private Integer pageNumber;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public Integer getIsCommon() {
		return isCommon;
	}
	public void setIsCommon(Integer isCommon) {
		this.isCommon = isCommon;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
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
