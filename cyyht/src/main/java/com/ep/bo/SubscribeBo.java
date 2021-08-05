package com.ep.bo;

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

public class SubscribeBo {
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
	private String conditions;//病况
	private Integer pageSize;
	private Integer pageNumber;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getVisitUserId() {
		return visitUserId;
	}
	public void setVisitUserId(Integer visitUserId) {
		this.visitUserId = visitUserId;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public Integer getPayState() {
		return payState;
	}
	public void setPayState(Integer payState) {
		this.payState = payState;
	}
	public String getReservationNumber() {
		return reservationNumber;
	}
	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}
	public Integer getVerification() {
		return verification;
	}
	public void setVerification(Integer verification) {
		this.verification = verification;
	}
	public Integer getSegment() {
		return segment;
	}
	public void setSegment(Integer segment) {
		this.segment = segment;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
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
	
	
	
}
