package com.ep.entity;

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
@Entity
@Table(name = "community")
public class Community {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String communityName; //社区名称
	private String cuid;//社区UID
	private String logo;//logo
	private String contactName;//联系人姓名
	private String telephone;//联系人电话
	private String address;//地址
	private String brief;//简介
	private Integer userId;//用户ID
	private String qr;//二维码
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "communityName", nullable = false)
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	
	@Column(name = "cuid", nullable = false)
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	
	@Column(name = "logo", nullable = false)
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Column(name = "contactName", nullable = false)
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	@Column(name = "telephone", nullable = false)
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column(name = "address", nullable = false)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "brief", nullable = false)
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	@Column(name = "userId", nullable = false)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "qr", nullable = true)
	public String getQr() {
		return qr;
	}
	public void setQr(String qr) {
		this.qr = qr;
	}
	
}
