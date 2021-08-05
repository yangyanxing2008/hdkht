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
 *  职称
 */
@Entity
@Table(name = "positional")
public class Positional implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String positionalName;//职称名称
	private Integer state; //是否启用 0:启用，1：禁用
	
	public Positional() {
		
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
	
	@Column(name = "positionalName", nullable = false)
	public String getPositionalName() {
		return positionalName;
	}
	public void setPositionalName(String positionalName) {
		this.positionalName = positionalName;
	}
	
	@Column(name = "state", nullable = false)
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}