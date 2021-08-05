package com.ep.bo;

/***
 * 就诊人员
 * @author Administrator
 *
 */

public class VisitUserBo {
	private Integer id;
	private String visituuid; //就诊卡uuid
	private String nickName;//昵称
	private String headImg;//头像
	private String userName;//用户名
	private String telephone;//电话
	private String iCard;//身份证号
	private Integer sex;//性别  0：女   1：男
	private String conditions;//病况
	private Integer pageSize;
	private Integer pageNumber;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVisituuid() {
		return visituuid;
	}
	public void setVisituuid(String visituuid) {
		this.visituuid = visituuid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getiCard() {
		return iCard;
	}
	public void setiCard(String iCard) {
		this.iCard = iCard;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
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
