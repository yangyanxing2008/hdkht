package com.ep.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 * 就诊人员
 * @author Administrator
 *
 */

@Entity
@Table(name = "visituser")
public class VisitUser {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String visituuid; //就诊卡uuid
	private String userName;//用户名
	private String telephone;//电话
	private String iCard;//身份证号
	private Integer sex;//性别  0：女   1：男
	private String conditions;//病况
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "sex", nullable = true)
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
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
	
	@Column(name = "iCard", nullable = true)
	public String getiCard() {
		return iCard;
	}
	public void setiCard(String iCard) {
		this.iCard = iCard;
	}
	
	@Column(name = "visituuid", nullable = true)
	public String getVisituuid() {
		return visituuid;
	}
	public void setVisituuid(String visituuid) {
		this.visituuid = visituuid;
	}
	@Column(name = "conditions", nullable = true)
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	
	
	
	
	
	
}
