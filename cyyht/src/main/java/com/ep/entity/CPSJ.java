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
@Table(name = "cpsj")
public class CPSJ implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String cpbh; //产品编号
	private String yzl;//原重量
	private String yczsj;//原称重时间
	private String xzl; // 现重量
	private String xczsj; //现称重时间
	private String cz; //差值
	private String zt;  //是否合格，1：不合格 0：合格
	
	
	public CPSJ() {
		
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
	
	@Column(name = "cpbh", nullable = false)
	public String getCpbh() {
		return cpbh;
	}
	public void setCpbh(String cpbh) {
		this.cpbh = cpbh;
	}
	
	@Column(name = "yzl", nullable = false)
	public String getYzl() {
		return yzl;
	}
	public void setYzl(String yzl) {
		this.yzl = yzl;
	}
	
	@Column(name = "yczsj", nullable = false)
	public String getYczsj() {
		return yczsj;
	}
	public void setYczsj(String yczsj) {
		this.yczsj = yczsj;
	}
	
	@Column(name = "xzl", nullable = false)
	public String getXzl() {
		return xzl;
	}
	public void setXzl(String xzl) {
		this.xzl = xzl;
	}
	
	@Column(name = "xczsj", nullable = false)
	public String getXczsj() {
		return xczsj;
	}
	public void setXczsj(String xczsj) {
		this.xczsj = xczsj;
	}
	
	@Column(name = "cz", nullable = false)
	public String getCz() {
		return cz;
	}
	public void setCz(String cz) {
		this.cz = cz;
	}
	
	@Column(name = "zt", nullable = false)
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	
}