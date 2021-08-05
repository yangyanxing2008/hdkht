package com.ep.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.ep.bo.HospitalBo;
import com.ep.dao.BaseDao;
import com.ep.entity.Hospital;
import com.ep.entity.VisitUser;
import com.ep.service.HospitalService;

@Service("hospitalService")
public class HospitalServiceImpl implements HospitalService{
	@Autowired
	public BaseDao baseDao;
	
	@Override
	public String getPageList(HospitalBo hospitalBo) {
		JSONObject objmenu = new JSONObject();
		if(StringUtils.isEmpty(hospitalBo.getPageSize().toString())){
			hospitalBo.setPageSize(20); 
		}
		if(StringUtils.isEmpty(hospitalBo.getPageNumber().toString())){
			hospitalBo.setPageNumber(1); 
		}
		String hql = "SELECT * ";
		String stateSql = "from hospital where 1=1 and hospitalName like '%"+hospitalBo.getHospitalName()+"%'";
		stateSql += " and userName like '%"+hospitalBo.getUserName()+"%'";
		stateSql += " and telephone like '%"+hospitalBo.getTelephone()+"%'";
		stateSql += " and address like '%"+hospitalBo.getAddress()+"%'";
		String countSql = "SELECT count(*) num "+stateSql;
		List<Map<String, Object>> list = baseDao.getPageList(hql+stateSql, hospitalBo.getPageSize(), hospitalBo.getPageNumber());
		int counts = baseDao.getAllCount(countSql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("hospitalName", list.get(i).get("hospitalName"));
				obj.put("logo", list.get(i).get("logo"));
				obj.put("userName", list.get(i).get("userName"));
				obj.put("telephone", list.get(i).get("telephone"));
				obj.put("address", list.get(i).get("address"));
				obj.put("brief", list.get(i).get("brief"));
				array.add(obj);
			}
			objmenu.put("data", array);
			objmenu.put("total", counts);
		}else{
			objmenu.put("code", "0");
			objmenu.put("message", "没有医院信息");
		}
		return objmenu.toString();
		
	}

	@Override
	public int save(Hospital hospital) {
		//保存医院信息
		int num = baseDao.saveDao(hospital);
		
		return num;
	}

	@Override
	public String update(HospitalBo hospitalBo) {
		JSONObject objmenu = new JSONObject();
		String sql = "select * from hospital WHERE id="+hospitalBo.getId();
		List<Map<String, Object>> list = baseDao.getAllList(sql);
		int num = 0;
		if(list.size()>0){
			Hospital hospital = new Hospital();
			try {
				BeanUtils.copyProperties(hospital, hospitalBo);
				if(list.get(0).get("userId")!=null && StringUtils.isNotEmpty(list.get(0).get("userId").toString()))
					hospital.setUserId(Integer.parseInt(list.get(0).get("userId").toString()));
				num = baseDao.updateDao(hospital);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			//
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
	public String delete(Integer id) {
		JSONObject objmenu = new JSONObject();
		String sql = "delete from hospital WHERE id="+id;
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
		String hql = "SELECT * from hospital ";
		
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("hospitalName", list.get(i).get("hospitalName"));
				obj.put("logo", list.get(i).get("logo"));
				obj.put("userName", list.get(i).get("userName"));
				obj.put("telephone", list.get(i).get("telephone"));
				obj.put("address", list.get(i).get("address"));
				obj.put("brief", list.get(i).get("brief"));
				array.add(obj);
			}
			objmenu.put("data", array);
		}else{
			objmenu.put("code", "0");
			objmenu.put("message", "没有医院信息");
		}
		return objmenu.toString();
	}

	@Override
	public String getHospital(HospitalBo hospitalBo) {
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT * from hospital where id="+hospitalBo.getId();
		
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONObject obj = new JSONObject();
			obj.put("id", list.get(0).get("id"));
			obj.put("hospitalName", list.get(0).get("hospitalName"));
			obj.put("logo", list.get(0).get("logo"));
			obj.put("userName", list.get(0).get("userName"));
			obj.put("telephone", list.get(0).get("telephone"));
			obj.put("address", list.get(0).get("address"));
			obj.put("brief", list.get(0).get("brief"));
			objmenu.put("data", obj);
		}else{
			objmenu.put("code", "0");
			objmenu.put("message", "没有医院信息");
		}
		return objmenu.toString();
	}
	
}
