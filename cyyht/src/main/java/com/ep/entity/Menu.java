package com.ep.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String menuName; //菜单名称
	private Integer parentId;//父菜单ID
	private String creatTime;//创建时间
	private String updateTime; //修改时间
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "menuName", nullable = false)
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	@Column(name = "parentId", nullable = false)
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	@Column(name = "creatTime", nullable = false)
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	
	@Column(name = "updateTime", nullable = false)
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
    
	
}
