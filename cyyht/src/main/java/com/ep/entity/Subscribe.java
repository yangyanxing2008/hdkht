package com.ep.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 * 预约表单
 * @author YYX
 *
 */
@Entity
@Table(name = "subscribe")
public class Subscribe {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer communityId; //社区ID
	private Integer departmentId;//科室ID
	private Integer doctorId;//医生ID
	private Integer visitUserId;//患者用户ID
	private String appointmentTime;//预约时间
	private Integer payState;//支付状态  0：未成功  1：成功
	private String reservationNumber;//预约单号
	private Integer verification;//是否核销  0：否  1：是
	private Integer segment;//号段
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "communityId", nullable = false)
	public Integer getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	
	@Column(name = "departmentId", nullable = false)
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	@Column(name = "doctorId", nullable = false)
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	
	
	@Column(name = "appointmentTime", nullable = true)
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	
	@Column(name = "payState", nullable = false)
	public Integer getPayState() {
		return payState;
	}
	public void setPayState(Integer payState) {
		this.payState = payState;
	}
	
	@Column(name = "reservationNumber", nullable = false)
	public String getReservationNumber() {
		return reservationNumber;
	}
	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}
	
	@Column(name = "verification", nullable = false)
	public Integer getVerification() {
		return verification;
	}
	public void setVerification(Integer verification) {
		this.verification = verification;
	}
	
	@Column(name = "segment", nullable = false)
	public Integer getSegment() {
		return segment;
	}
	public void setSegment(Integer segment) {
		this.segment = segment;
	}
	
	@Column(name = "visitUserId", nullable = false)
	public Integer getVisitUserId() {
		return visitUserId;
	}
	public void setVisitUserId(Integer visitUserId) {
		this.visitUserId = visitUserId;
	}


	
}
