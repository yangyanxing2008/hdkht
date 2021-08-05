package com.ep.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String doctorName; //医生姓名
	private String telephone;
	private String headImg;//头像
	private Integer sex;//性别 0：女 1：男
	private Integer age;//年龄
	private Integer hospitalId;//医院ID
	private Integer departmentId;//科室ID
	private Integer positionalId;//职称ID
	private String specialize;//标签
	private String expert; //擅长
	private String qr;//二维码
	private String workStartTime;//工作开始时间
	private String workEndTime;//工作结束时间
	private String registrationFee;//挂号费
	private String brief;//简介
	private Integer intervalTime; //间隔时间
	private Integer num; //预约人数
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "doctorName", nullable = false)
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	@Column(name = "headImg", nullable = false)
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	@Column(name = "sex", nullable = false)
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@Column(name = "age", nullable = false)
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Column(name = "hospitalId", nullable = false)
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	@Column(name = "departmentId", nullable = false)
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	@Column(name = "positionalId", nullable = false)
	public Integer getPositionalId() {
		return positionalId;
	}
	public void setPositionalId(Integer positionalId) {
		this.positionalId = positionalId;
	}
	
	@Column(name = "specialize", nullable = false)
	public String getSpecialize() {
		return specialize;
	}
	public void setSpecialize(String specialize) {
		this.specialize = specialize;
	}
	
	@Column(name = "qr", nullable = true)
	public String getQr() {
		return qr;
	}
	public void setQr(String qr) {
		this.qr = qr;
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
	@Column(name = "registrationFee", nullable = false)
	public String getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}
	@Column(name = "brief", nullable = true)
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	@Column(name = "intervalTime", nullable = true)
	public Integer getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(Integer intervalTime) {
		this.intervalTime = intervalTime;
	}
	@Column(name = "num", nullable = true)
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Column(name = "telephone", nullable = true)
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name = "expert", nullable = true)
	public String getExpert() {
		return expert;
	}
	public void setExpert(String expert) {
		this.expert = expert;
	}
	
}
