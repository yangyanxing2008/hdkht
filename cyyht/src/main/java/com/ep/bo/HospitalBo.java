package com.ep.bo;

/**
 * 
 * @author YYX
 * 医院信息
 *
 */

public class HospitalBo implements java.io.Serializable {
	private Integer id;
	private String hospitalName; //名称
	private String userName;//联系人姓名
	private String telephone; //联系电话
	private String address; //地址
	private String accountNumber; //账号
	private String accountPwd;//密码
	private String logo;//logo
	private String brief; //简介 内容不超过200字
	private Integer pageSize;
	private Integer pageNumber;
	
	public HospitalBo() {
		
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	
}