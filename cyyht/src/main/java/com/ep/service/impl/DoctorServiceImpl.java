package com.ep.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ep.bo.DoctorBo;
import com.ep.dao.BaseDao;
import com.ep.entity.Doctor;
import com.ep.service.DoctorService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("doctorService")
public class DoctorServiceImpl implements DoctorService{
	@Autowired
	public BaseDao baseDao;

	@Override
	public int save(DoctorBo doctorBo) {
		Doctor doctor = new Doctor();
		int num = 0;
		try {
			BeanUtils.copyProperties(doctor, doctorBo);
			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date fromDate = simpleFormat.parse(doctor.getWorkStartTime());  
		    Date toDate = simpleFormat.parse(doctor.getWorkEndTime());  
		    long from = fromDate.getTime();  
		    long to = toDate.getTime();  
		    int minutes = (int) ((to - from) / (1000 * 60));  
		    int nums = (int) Math.ceil(minutes/doctor.getIntervalTime());
		    doctor.setNum(nums);
			num = baseDao.saveDao(doctor);
		} catch (Exception e) {
			num = 0;
			e.printStackTrace();
		}
		return num;
	}


	@Override
	public String getPageList(DoctorBo doctorBo) {
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT id,doctorName,headImg,sex,age,";
		hql += "(select hospitalName from hospital where id=doctor.hospitalId) as hospitalName,";
		hql += "(select departmentName from department where id=doctor.departmentId) as departmentName,";
		hql += "(select positionalName from positional where id=doctor.positionalId) as positionalName,";
		hql += "specialize,qr,workStartTime,workEndTime,registrationFee,brief,intervalTime,telephone ";
		String sql = "from doctor where doctorName like '%"+doctorBo.getDoctorName()+"%' ";
		/*if(StringUtils.isNotEmpty(doctorBo.getSex().toString()))
			sql += "and sex＝"+doctorBo.getSex();
		if(StringUtils.isNotEmpty(doctorBo.getAge().toString()))
			sql += " and age＝"+doctorBo.getAge();*/
		/*sql += " and specialize like '%"+doctorBo.getSpecialize()+"%'";
		sql += " and hospitalId in (select id from HOSPITAL WHERE hospitalName like '%"+doctorBo.getHospitalName()+"%')";
		sql += " and positionalId in (select id from POSITIONAL WHERE positionalName like '%"+doctorBo.getPositionalName()+"%')";
        if(doctorBo.getDepartmentType()==1){
        	sql += " and departmentId in (select id from DEPARTMENT WHERE parentId=0)";
        }
        else if(doctorBo.getDepartmentType()==2){
        	sql += " and departmentId in (select id from DEPARTMENT WHERE parentId!=0)";
        }*/
		String countSql = "select count(*) "+sql;
		List<Map<String, Object>> list = baseDao.getPageList(hql+sql, doctorBo.getPageSize(), doctorBo.getPageNumber());
		if(list.size()>0){
			int num = baseDao.getAllCount(countSql);
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("doctorName", list.get(i).get("doctorName"));
				obj.put("headImg", list.get(i).get("headImg"));
				if(Integer.parseInt(list.get(i).get("sex").toString())==1){
					obj.put("sex", "男");
				}else{
					obj.put("sex", "女");
				}
				obj.put("age", list.get(i).get("age"));
				obj.put("hospitalName", list.get(i).get("hospitalName"));
				obj.put("departmentName", list.get(i).get("departmentName"));
				obj.put("positionalName", list.get(i).get("positionalName"));
				obj.put("specialize", list.get(i).get("specialize"));
				obj.put("qr", list.get(i).get("qr"));
				obj.put("workStartTime", list.get(i).get("workStartTime"));
				obj.put("workEndTime", list.get(i).get("workEndTime"));
				obj.put("registrationFee", list.get(i).get("registrationFee"));
				obj.put("brief", list.get(i).get("brief"));
				obj.put("intervalTime", list.get(i).get("intervalTime"));
				obj.put("telephone", list.get(i).get("telephone"));
				array.add(obj);
			}
			objmenu.put("data", array);
			objmenu.put("total", num);
		}else{
			objmenu.put("code", "0");
			objmenu.put("msg", "没有医生信息");
		}
		return objmenu.toString();
	}


	@Override
	public String update(DoctorBo doctorBo) {
		JSONObject objmenu = new JSONObject();
		int num = 0;
		Doctor doctor = new Doctor();
		try {
			BeanUtils.copyProperties(doctor, doctorBo);
			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date fromDate = simpleFormat.parse(doctor.getWorkStartTime());  
		    Date toDate = simpleFormat.parse(doctor.getWorkEndTime());  
		    long from = fromDate.getTime();  
		    long to = toDate.getTime();  
		    int minutes = (int) ((to - from) / (1000 * 60));  
		    int nums = (int) Math.ceil(minutes/doctor.getIntervalTime());
		    doctor.setNum(nums);
			num = baseDao.updateDao(doctor);
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
	public String delete(DoctorBo doctorBo) {
		JSONObject objmenu = new JSONObject();
		String sql = "delete from doctor WHERE id="+doctorBo.getId();
		int num = baseDao.delById(sql);
		if(num>0){
			objmenu.put("code", "1");
			objmenu.put("message", "删除成功");
		}else{
			objmenu.put("code", "0");
			objmenu.put("message", "删除失败");
		}
		return objmenu.toString();
	}


	@Override
	public String getList() {
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT id,doctorName,headImg,sex,age,";
		hql += "(select hospitalName from hospital where id=doctor.hospitalId) as hospitalName,";
		hql += "(select departmentName from department where id=doctor.departmentId) as departmentName,";
		hql += "(select positionalName from positional where id=doctor.positionalId) as positionalName,";
		hql += "specialize,qr,workStartTime,workEndTime,registrationFee,brief,intervalTime,telephone from doctor";
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("doctorName", list.get(i).get("doctorName"));
				obj.put("headImg", list.get(i).get("headImg"));
				obj.put("sex", list.get(i).get("sex"));
				obj.put("age", list.get(i).get("age"));
				obj.put("hospitalName", list.get(i).get("hospitalName"));
				obj.put("departmentName", list.get(i).get("departmentName"));
				obj.put("positionalName", list.get(i).get("positionalName"));
				obj.put("specialize", list.get(i).get("specialize"));
				obj.put("qr", list.get(i).get("qr"));
				obj.put("workStartTime", list.get(i).get("workStartTime"));
				obj.put("workEndTime", list.get(i).get("workEndTime"));
				obj.put("registrationFee", list.get(i).get("registrationFee"));
				obj.put("brief", list.get(i).get("brief"));
				obj.put("intervalTime", list.get(i).get("intervalTime"));
				obj.put("telephone", list.get(i).get("telephone"));
				array.add(obj);
			}
			objmenu.put("data", array);
		}else{
			objmenu.put("code", "0");
			objmenu.put("msg", "没有医生信息");
		}
		return objmenu.toString();
	}


	@Override
	public String getDoctor(DoctorBo doctorBo) {
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT id,doctorName,headImg,sex,age,hospitalId,departmentId,positionalId,";
		hql += "specialize,qr,workStartTime,workEndTime,registrationFee,brief,intervalTime,"
				+ "telephone from doctor where id="+doctorBo.getId();
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONObject obj = new JSONObject();
			obj.put("id", list.get(0).get("id"));
			obj.put("doctorName", list.get(0).get("doctorName"));
			obj.put("headImg", list.get(0).get("headImg"));
			obj.put("sex", list.get(0).get("sex"));
			obj.put("age", list.get(0).get("age"));
			obj.put("hospitalId", list.get(0).get("hospitalId"));
			obj.put("departmentId", list.get(0).get("departmentId"));
			obj.put("positionalId", list.get(0).get("positionalId"));
			obj.put("specialize", list.get(0).get("specialize"));
			obj.put("qr", list.get(0).get("qr"));
			obj.put("workStartTime", list.get(0).get("workStartTime"));
			obj.put("workEndTime", list.get(0).get("workEndTime"));
			obj.put("registrationFee", list.get(0).get("registrationFee"));
			obj.put("brief", list.get(0).get("brief"));
			obj.put("intervalTime", list.get(0).get("intervalTime"));
			obj.put("telephone", list.get(0).get("telephone"));
			objmenu.put("data", obj);
		}else{
			objmenu.put("code", "0");
			objmenu.put("msg", "没有医生信息");
		}
		return objmenu.toString();
	}


	@Override
	public String getDoctorList(@RequestBody DoctorBo doctorBo) {
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT id,doctorName,headImg,expert,";
		hql += "(select hospitalName from hospital where id=doctor.hospitalId) as hospitalName,";
		hql += "(select departmentName from department where id=doctor.departmentId) as departmentName,";
		hql += "(select positionalName from positional where id=doctor.positionalId) as positionalName,";
		hql += "brief from doctor where 1=1";
		if(doctorBo.getDoctorName().length()>0){
			hql += " and doctorName like '"+doctorBo.getDoctorName()+"'";
		}
		if(doctorBo.getDepartmentName().length()>0){
			String departNamesql = "select id from department where departmentName like '%"+doctorBo.getDepartmentName()+"%'";
			List<Map<String, Object>> list = baseDao.getAllList(departNamesql);
			if(list.size()>0)
				hql += " and departmentId="+list.get(0).get("id");
		}
		
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("headImg", list.get(i).get("headImg"));
				obj.put("doctorName", list.get(i).get("doctorName"));
				obj.put("positionalName", list.get(i).get("positionalName"));
				obj.put("hospitalName", list.get(i).get("hospitalName"));
				obj.put("departmentName", list.get(i).get("departmentName"));
				obj.put("brief", list.get(i).get("brief"));
				obj.put("expert", list.get(i).get("expert")); //擅长
				array.add(obj);
			}
			objmenu.put("data", array);
		}else{
			objmenu.put("code", "0");
			objmenu.put("msg", "没有医生信息");
		}
		return objmenu.toString();
	}
	
}
