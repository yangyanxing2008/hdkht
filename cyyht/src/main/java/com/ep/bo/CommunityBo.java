package com.ep.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 社区
 * @author yyx
 *
 */

public class CommunityBo {
	private Integer id;
	private String communityName; //社区名称
	private String cuid;//社区UID
	private String logo;//logo
	private String contactName;//联系人姓名
	private String telephone;//联系人电话
	private String address;//地址
	private String brief;//简介
	private String accountNumber; //账号
	private String accountPwd;//密码
	private Integer userId;//后台用户ID 
	private String qr;//二维码
	private Integer pageSize;
	private Integer pageNumber;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
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
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getQr() {
		return qr;
	}
	public void setQr(String qr) {
		this.qr = qr;
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
	
}
