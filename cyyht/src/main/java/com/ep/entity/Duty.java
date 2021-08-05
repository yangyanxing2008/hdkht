package com.ep.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "duty")
public class Duty {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String week; //医生姓名
	private String workStartTime;
	private String workEndTime;//头像
	private Integer doctorId;//性别 0：女 1：男
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "workStartTime", nullable = false)
	public String getWorkStartTime() {
		return workStartTime;
	}
	public void setWorkStartTime(String workStartTime) {
		this.workStartTime = workStartTime;
	}
	
	@Column(name = "workEndTime", nullable = false)
	public String getWorkEndTime() {
		return workEndTime;
	}
	public void setWorkEndTime(String workEndTime) {
		this.workEndTime = workEndTime;
	}
	
	@Column(name = "week", nullable = false)
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	
	@Column(name = "doctorId", nullable = false)
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	
	
}
