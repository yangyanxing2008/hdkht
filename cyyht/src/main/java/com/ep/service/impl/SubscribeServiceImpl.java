package com.ep.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.bo.PositionalBo;
import com.ep.bo.SubscribeBo;
import com.ep.dao.BaseDao;
import com.ep.entity.Doctor;
import com.ep.entity.Positional;
import com.ep.entity.Subscribe;
import com.ep.service.PositionalService;
import com.ep.service.SubscribeService;
@Service("subscribeService")
public class SubscribeServiceImpl implements SubscribeService{
	@Autowired
	public BaseDao baseDao;
	
	@Override
	public int save(SubscribeBo subscribeBo) {
		Subscribe subscribe = new Subscribe();
		int num = 0;
		try {
			String sql = "select id from SUBSCRIBE "
					+ "where visitUserId="+subscribeBo.getVisitUserId()+" and appointmentTime='"+subscribeBo.getAppointmentTime()+"'";
			List<Map<String, Object>> listS = baseDao.getAllList(sql);	
			if(listS.size()>0){
				num =  Integer.parseInt(listS.get(0).get("id").toString());
			}else{
				BeanUtils.copyProperties(subscribe, subscribeBo);
				String doctorSql = "select num from DOCTOR where id="+subscribeBo.getDoctorId();
				List<Map<String, Object>> list = baseDao.getAllList(doctorSql);	
				int yynum = Integer.parseInt(list.get(0).get("num").toString()); //预约数量
				String yyCountSql = "select count(*) num from SUBSCRIBE where doctorId="+subscribeBo.getDoctorId();
				int yycount = baseDao.getAllCount(yyCountSql);
				if((yynum-yycount)>0){
					int segment = ++yycount;
					subscribe.setSegment(segment);
					subscribe.setReservationNumber("D"+String.valueOf(System.currentTimeMillis()));
					subscribe.setPayState(0);
					subscribe.setVerification(0);//是否核销  0：否  1：是
					num = baseDao.saveDao(subscribe);
				}else{
					num = 0;
				}
			}
			
		} catch (Exception e) {
			 num = 0;
			e.printStackTrace();
		} 
		return num;
	}

	
	@Override
	public String getSubscribe(int subscribeId) {
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT * from SUBSCRIBE where id="+subscribeId; 
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONObject obj = new JSONObject();
			obj.put("id", list.get(0).get("id"));
			obj.put("appointmentTime", list.get(0).get("appointmentTime"));
			if(list.get(0).get("payState")=="1"){
				obj.put("payState", "已支付");
			}else{
				obj.put("payState", "未支付");
			}
			obj.put("reservationNumber", list.get(0).get("reservationNumber"));
			obj.put("reservationNumber", list.get(0).get("reservationNumber"));
			obj.put("verification", list.get(0).get("verification"));
			obj.put("segment", list.get(0).get("segment"));
			
			//查询社区信息
			String sqSql = "select communityName,logo,address,contactName,telephone from COMMUNITY where id="+list.get(0).get("communityId");
			List<Map<String, Object>> communitylist = baseDao.getAllList(sqSql);
			obj.put("communityName", communitylist.get(0).get("communityName"));
			obj.put("communitylogo", communitylist.get(0).get("logo"));
			obj.put("communityAddress", communitylist.get(0).get("address"));
			obj.put("contactName", communitylist.get(0).get("contactName"));
			obj.put("telephone", communitylist.get(0).get("telephone"));
			//医生信息
			String doctorSql = "select doctorName,registrationFee from DOCTOR where id="+list.get(0).get("doctorId");
			List<Map<String, Object>> doctorList = baseDao.getAllList(doctorSql);
			obj.put("doctorName", doctorList.get(0).get("doctorName"));
			obj.put("registrationFee", doctorList.get(0).get("registrationFee"));
		    //科室
			String departmentSql = "select departmentName from DEPARTMENT where id="+list.get(0).get("departmentId");
			List<Map<String, Object>> departmentList = baseDao.getAllList(departmentSql);
			obj.put("departmentName", departmentList.get(0).get("departmentName"));
			//患者
			String visitUserSql = "select userName,sex,telephone,conditions from VISITUSER where id="+list.get(0).get("visitUserId");
			List<Map<String, Object>> visitUserList = baseDao.getAllList(visitUserSql);
			obj.put("visitUserName", visitUserList.get(0).get("userName"));
			if(Integer.parseInt(visitUserList.get(0).get("sex").toString())==1){
				obj.put("visitUserSex", "男");
			}else{
				obj.put("visitUserSex", "女");
			}
			obj.put("visitUseId", list.get(0).get("visitUserId"));
			obj.put("visitUserTelephone", visitUserList.get(0).get("telephone"));
			obj.put("visitUserConditions", visitUserList.get(0).get("conditions"));
			objmenu.put("data", obj);
		}else{
			objmenu.put("code", "2");
			objmenu.put("msg", "号已预约完");
		}
		return objmenu.toString();
	}


	@Override
	public String update(SubscribeBo subscribeBo) {
		JSONObject objmenu = new JSONObject();
		int num = 0;
		Subscribe subscribe = new Subscribe();
		try {
			BeanUtils.copyProperties(subscribe, subscribeBo);
			num = baseDao.updateDao(subscribe);
		} catch (Exception e) {
			num = 0;
			e.printStackTrace();
		} 
		
		if(num>0){
			objmenu.put("code", "1");
			objmenu.put("message", "修改成功");
		}else{
			objmenu.put("code", "0");
			objmenu.put("message", "修改失败");
		}
		return objmenu.toString();
	}


	@Override
	public String delete(SubscribeBo subscribeBo) {
		JSONObject objmenu = new JSONObject();
		/*String sql = "delete from POSITIONAL WHERE id="+positionalBo.getId();
		int num = baseDao.delById(sql);
		if(num>0){
			objmenu.put("code", "1");
			objmenu.put("message", "删除成功");
		}else{
			objmenu.put("code", "0");
			objmenu.put("message", "删除失败");
		}*/
		return objmenu.toString();
	}


	@Override
	public String getList() {
		// TODO Auto-generated method stub
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT * from SUBSCRIBE where payState=0"; 
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("appointmentTime", list.get(i).get("appointmentTime"));
				obj.put("payState", list.get(i).get("payState"));
				obj.put("reservationNumber", list.get(i).get("reservationNumber"));
				obj.put("verification", list.get(i).get("verification"));
				obj.put("segment", list.get(i).get("segment"));
				
				//查询社区信息
				/*String sqSql = "select communityName,logo,address,contactName,telephone from COMMUNITY where id="+list.get(0).get("communityId");
				List<Map<String, Object>> communitylist = baseDao.getAllList(sqSql);
				obj.put("communityName", communitylist.get(0).get("communityName"));
				obj.put("communitylogo", communitylist.get(0).get("logo"));
				obj.put("communityAddress", communitylist.get(0).get("address"));
				obj.put("contactName", communitylist.get(0).get("contactName"));
				obj.put("telephone", communitylist.get(0).get("telephone"));*/
				//医生信息
				String doctorSql = "select doctorName,registrationFee from DOCTOR where id="+list.get(i).get("doctorId");
				List<Map<String, Object>> doctorList = baseDao.getAllList(doctorSql);
				obj.put("doctorName", doctorList.get(0).get("doctorName"));
				obj.put("registrationFee", doctorList.get(0).get("registrationFee"));
			    //科室
				String departmentSql = "select departmentName from DEPARTMENT where id="+list.get(i).get("departmentId");
				List<Map<String, Object>> departmentList = baseDao.getAllList(departmentSql);
				obj.put("departmentName", departmentList.get(0).get("departmentName"));
				//患者
				String visitUserSql = "select userName,sex,telephone,conditions from VISITUSER where id="+list.get(i).get("visitUserId");
				List<Map<String, Object>> visitUserList = baseDao.getAllList(visitUserSql);
				obj.put("visitUserName", visitUserList.get(0).get("userName"));
				if(Integer.parseInt(visitUserList.get(0).get("sex").toString())==1){
					obj.put("visitUserSex", "男");
				}else{
					obj.put("visitUserSex", "女");
				}
				obj.put("visitUseId", list.get(0).get("visitUserId"));
				obj.put("visitUserTelephone", visitUserList.get(0).get("telephone"));
				obj.put("visitUserConditions", visitUserList.get(0).get("conditions"));
				array.add(obj);
			}
			
			objmenu.put("data", array);
		}else{
			objmenu.put("code", "2");
			objmenu.put("msg", "号已预约完");
		}
		return objmenu.toString();
	}


	@Override
	public String getPageList(SubscribeBo subscribeBo) {
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT id,payState,appointmentTime,verification,communityId,doctorId,departmentId,visitUserId from subscribe";
		String countSql = "select count(id) num from subscribe";
		List<Map<String, Object>> list = baseDao.getPageList(hql, subscribeBo.getPageSize(), subscribeBo.getPageNumber());
		if(list.size()>0){
			objmenu.put("code", "1");
			int count = baseDao.getAllCount(countSql);
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("appointmentTime", list.get(i).get("appointmentTime"));
				obj.put("payState", list.get(i).get("payState"));
				obj.put("verification", list.get(i).get("verification"));//核销
			
				//查询社区信息
				String sqSql = "select communityName from community where id="+list.get(0).get("communityId");
				List<Map<String, Object>> communitylist = baseDao.getAllList(sqSql);
				obj.put("communityName", communitylist.get(0).get("communityName"));
				
				//医生信息
				String doctorSql = "select doctorName,registrationFee from doctor where id="+list.get(i).get("doctorId");
				List<Map<String, Object>> doctorList = baseDao.getAllList(doctorSql);
				obj.put("doctorName", doctorList.get(0).get("doctorName"));
				
			    //科室
				String departmentSql = "select departmentName from department where id="+list.get(i).get("departmentId");
				List<Map<String, Object>> departmentList = baseDao.getAllList(departmentSql);
				obj.put("departmentName", departmentList.get(0).get("departmentName"));
				//患者
				String visitUserSql = "select userName,sex,telephone,iCard from visituser where id="+list.get(i).get("visitUserId");
				List<Map<String, Object>> visitUserList = baseDao.getAllList(visitUserSql);
				obj.put("visitUserName", visitUserList.get(0).get("userName"));
				if(Integer.parseInt(visitUserList.get(0).get("sex").toString())==1){
					obj.put("visitUserSex", "男");
				}else{
					obj.put("visitUserSex", "女");
				}
				obj.put("visitUserTelephone", visitUserList.get(0).get("telephone"));
				obj.put("iCard", visitUserList.get(0).get("iCard"));
				array.add(obj);
				
			}
			objmenu.put("total", count);
			objmenu.put("data", array);
		}else{
			objmenu.put("code", "2");
			objmenu.put("msg", "号已预约完");
		}
		return objmenu.toString();
	}


	@Override
	public String updateHx(SubscribeBo subscribeBo) {
		JSONObject objmenu = new JSONObject();
		int num = 0;
		try {
			String sql = "select * from subscribe where id="+subscribeBo.getId();
			List<Map<String, Object>> list = baseDao.getAllList(sql);
			if(list.size()>0){
				Subscribe subscribe = new Subscribe();
				subscribe.setId(subscribeBo.getId());
				subscribe.setAppointmentTime(list.get(0).get("appointmentTime").toString());
				subscribe.setCommunityId(Integer.parseInt(list.get(0).get("communityId").toString()));
				subscribe.setDoctorId(Integer.parseInt(list.get(0).get("doctorId").toString()));
				subscribe.setDepartmentId(Integer.parseInt(list.get(0).get("departmentId").toString()));
				subscribe.setVisitUserId(Integer.parseInt(list.get(0).get("visitUserId").toString()));
				subscribe.setPayState(Integer.parseInt(list.get(0).get("payState").toString()));
				subscribe.setReservationNumber(list.get(0).get("reservationNumber").toString());
				subscribe.setSegment(Integer.parseInt(list.get(0).get("segment").toString()));
				subscribe.setVerification(1);
				num = baseDao.updateDao(subscribe);
			}
			
		} catch (Exception e) {
			num = 0;
			e.printStackTrace();
		} 
		
		if(num>0){
			objmenu.put("code", "1");
			objmenu.put("message", "核查成功");
		}else{
			objmenu.put("code", "0");
			objmenu.put("message", "核查失败");
		}
		return objmenu.toString();
	}

}
