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
 * @author admin
 *
 */
@Entity
@Table(name = "cspz")
public class CSPZ implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String min;//最小值
	private String max;//最大值
	
	public CSPZ() {
		
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
	
	@Column(name = "min", nullable = false)
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	
	@Column(name = "max", nullable = false)
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
}