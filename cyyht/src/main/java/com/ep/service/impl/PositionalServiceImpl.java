package com.ep.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.bo.PositionalBo;
import com.ep.dao.BaseDao;
import com.ep.entity.Doctor;
import com.ep.entity.Positional;
import com.ep.service.PositionalService;
@Service("positionalService")
public class PositionalServiceImpl implements PositionalService{
	@Autowired
	public BaseDao baseDao;
	
	@Override
	public int save(PositionalBo positionalBo) {
		Positional positional = new Positional();
		int num = 0;
		try {
			BeanUtils.copyProperties(positional, positionalBo);
			num = baseDao.saveDao(positional);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 num = 0;
			e.printStackTrace();
		} 
		return num;
	}


	@Override
	public String getPageList(PositionalBo positionalBo) {
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT * ";
		String sql = "from positional where positionalName like '%"+positionalBo.getPositionalName()+"%'"; 
		if(StringUtils.isNotEmpty(positionalBo.getState().toString()))
			sql += "and state="+positionalBo.getState();
		String countSql = "SELECT count(*) num "+sql; 
		List<Map<String, Object>> list = baseDao.getPageList(hql+sql, positionalBo.getPageSize(), positionalBo.getPageNumber());
		if(list.size()>0){
			int num = baseDao.getAllCount(countSql);
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("positionalName", list.get(i).get("positionalName"));
				obj.put("state", list.get(i).get("state"));
				array.add(obj);
			}
			
			objmenu.put("data", array);
			objmenu.put("total", num);
		}else{
			objmenu.put("code", "0");
			objmenu.put("msg", "没有职称信息");
		}
		return objmenu.toString();
	}


	@Override
	public String update(PositionalBo positionalBo) {
		JSONObject objmenu = new JSONObject();
		int num = 0;
		Positional positional = new Positional();
		try {
			BeanUtils.copyProperties(positional, positionalBo);
			num = baseDao.updateDao(positional);
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
	public String delete(PositionalBo positionalBo) {
		JSONObject objmenu = new JSONObject();
		String sql = "delete from positional WHERE id="+positionalBo.getId();
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
		String hql = "SELECT * from positional where state=0"; 
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("positionalName", list.get(i).get("positionalName"));
				obj.put("state", list.get(i).get("state"));
				array.add(obj);
			}
			objmenu.put("data", array);
		}else{
			objmenu.put("code", "0");
			objmenu.put("msg", "没有职称信息");
		}
		return objmenu.toString();
	}

}
