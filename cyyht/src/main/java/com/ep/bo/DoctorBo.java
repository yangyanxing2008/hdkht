package com.ep.bo;


public class DoctorBo {
	private Integer id;
	private String doctorName; //医生姓名
	private String headImg;//头像
	private Integer sex;//性别 0：女 1：男
	private Integer age;//年龄
	private Integer hospitalId;//医院ID
	private Integer hospitalName;//医院名称
	private Integer departmentId;//科室ID
	private String departmentName;//科室名称
	private Integer departmentType;//科室类型  1：一科室 2：二科室
	private Integer positionalId;//职称ID
	private Integer positionalName;//职称名称
	private String specialize;//擅长标签
	private String qr;//二维码
	private String workStartTime;//工作开始时间
	private String workEndTime;//工作结束时间
	private String registrationFee;//挂号费
	private String brief;//简介
	private Integer intervalTime; //间隔时间
	private String telephone;
	
	private String accountNumber; //账号
	private String accountPwd;//密码
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	private Integer pageSize;
	private Integer pageNumber;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	public String getSpecialize() {
		return specialize;
	}
	public void setSpecialize(String specialize) {
		this.specialize = specialize;
	}
	public String getQr() {
		return qr;
	}
	public void setQr(String qr) {
		this.qr = qr;
	}
	public String getWorkStartTime() {
		return workStartTime;
	}
	public void setWorkStartTime(String workStartTime) {
		this.workStartTime = workStartTime;
	}
	public String getWorkEndTime() {
		return workEndTime;
	}
	public void setWorkEndTime(String workEndTime) {
		this.workEndTime = workEndTime;
	}
	public String getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(Integer hospitalName) {
		this.hospitalName = hospitalName;
	}
	public Integer getDepartmentType() {
		return departmentType;
	}
	public void setDepartmentType(Integer departmentType) {
		this.departmentType = departmentType;
	}
	public Integer getPositionalId() {
		return positionalId;
	}
	public void setPositionalId(Integer positionalId) {
		this.positionalId = positionalId;
	}
	public Integer getPositionalName() {
		return positionalName;
	}
	public void setPositionalName(Integer positionalName) {
		this.positionalName = positionalName;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public Integer getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(Integer intervalTime) {
		this.intervalTime = intervalTime;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountPwd() {
		return accountPwd;
	}
	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	
	
	
}
