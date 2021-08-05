package com.ep.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 * 就诊卡
 * @author yyx
 *
 */

@Entity
@Table(name = "visitcard")
public class VisitCard {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String uuid; //uuid
	private String nickName;//昵称
	private String headImg;//头像
	private Integer state;//状态 0：正常  1：黑名单
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
	
	@Column(name = "uuid", nullable = true)
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Column(name = "nickName", nullable = true)
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Column(name = "headImg", nullable = true)
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	
	@Column(name = "state", nullable = true)
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	@Column(name = "createTime", nullable = true)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
