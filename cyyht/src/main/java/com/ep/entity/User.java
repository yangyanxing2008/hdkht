package com.ep.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String accountNumber; //账号
	private String accountPwd;//密码
	private Integer roleId;//角色ID 角色：1：超级管理员、2：社区、3：医院、4：医生
	private Integer enable;//是否启用    0：启用 1：禁用
	private String createTime;//创建时间
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "accountNumber", nullable = false)
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Column(name = "accountPwd", nullable = false)
	public String getAccountPwd() {
		return accountPwd;
	}
	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}
	
	@Column(name = "roleId", nullable = false)
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	@Column(name = "enable", nullable = false)
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	
	@Column(name = "createTime", nullable = false)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
    
	
}
