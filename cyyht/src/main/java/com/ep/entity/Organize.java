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
 *组织
 */
@Entity
@Table(name = "organize")
public class Organize implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String organizeName;//组织名称
	private String organizeType;//组织类型  1：医院。2：社区
	private Integer parentId;
	
	public Organize() {
		
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
	
	@Column(name = "organizeName", nullable = false)
	public String getOrganizeName() {
		return organizeName;
	}
	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}
	
	@Column(name = "organizeType", nullable = false)
	public String getOrganizeType() {
		return organizeType;
	}
	public void setOrganizeType(String organizeType) {
		this.organizeType = organizeType;
	}
	
	@Column(name = "parentId", nullable = false)
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}