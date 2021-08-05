package com.ep.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author YYX
 *  标签
 */
@Entity
@Table(name = "specialize")
public class Specialize implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String specializeName;//擅长名称
	private Integer state; //是否启用 0:启用，1：禁用
	
	public Specialize() {
		
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
	
	@Column(name = "specializeName", nullable = false)
	public String getSpecializeName() {
		return specializeName;
	}
	public void setSpecializeName(String specializeName) {
		this.specializeName = specializeName;
	}
	
	@Column(name = "state", nullable = false)
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}