package com.ep.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author YYX
 *
 */
@Entity
@Table(name = "hospital")
public class Hospital implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String hospitalName; //名称
	private String logo;//logo
	private String userName;//联系人姓名
	private String telephone; //联系电话
	private String address; //地址
	private String brief; //简介 内容不超过200字
	private Integer userId; //用户ID
	
	
	public Hospital() {
		
	}
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "hospitalName", nullable = true)
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	@Column(name = "logo", nullable = true)
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Column(name = "userName", nullable = true)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "telephone", nullable = true)
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column(name = "address", nullable = true)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "brief", nullable = true)
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	@Column(name = "userId", nullable = true)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
}