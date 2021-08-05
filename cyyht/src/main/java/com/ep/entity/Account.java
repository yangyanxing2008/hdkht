package com.ep.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 * 财务
 * @author Administrator
 *
 */
@Entity
@Table(name = "account")
public class Account {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String orderNumber; //日期+订单编号组合，最长不超过10位
	private Integer amount;//金额
	private String payNumber;//支付单号 日期+支付编号组合，最长不超过10位
	private String serialNumber;//支付流水号 日期+编号组合，最长不超过10位
	private Integer result;//支付结果  0：失败  1：成功
	private Integer visitUserId;//支付用户
	private String createTime;//支付时间
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "orderNumber", nullable = false)
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@Column(name = "amount", nullable = false)
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	@Column(name = "payNumber", nullable = false)
	public String getPayNumber() {
		return payNumber;
	}
	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}
	
	@Column(name = "serialNumber", nullable = false)
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@Column(name = "result", nullable = false)
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	
	@Column(name = "visitUserId", nullable = false)
	public Integer getVisitUserId() {
		return visitUserId;
	}
	public void setVisitUserId(Integer visitUserId) {
		this.visitUserId = visitUserId;
	}
	
	@Column(name = "createTime", nullable = false)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
