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
import com.ep.bo.SpecializeBo;
import com.ep.dao.BaseDao;
import com.ep.entity.Positional;
import com.ep.entity.Specialize;
import com.ep.service.PositionalService;
import com.ep.service.SpecializeService;
@Service("specializeService")
public class SpecializeServiceImpl implements SpecializeService{
	@Autowired
	public BaseDao baseDao;
	
	@Override
	public int save(SpecializeBo specializeBo) {
		Specialize specialize = new Specialize();
		int num = 0;
		try {
			BeanUtils.copyProperties(specialize, specializeBo);
			num = baseDao.saveDao(specialize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 num = 0;
			e.printStackTrace();
		} 
		return num;
	}


	@Override
	public String getPageList(SpecializeBo specializeBo) {
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT * ";
		String sql = "from specialize where specializeName like '%"+specializeBo.getSpecializeName()+"%'"; 
		if(StringUtils.isNotEmpty(specializeBo.getState().toString()))
			sql += "and state="+specializeBo.getState();
		String countSql = "SELECT count(*) num "+sql; 
		List<Map<String, Object>> list = baseDao.getPageList(hql+sql, specializeBo.getPageSize(), specializeBo.getPageNumber());
		if(list.size()>0){
			int num = baseDao.getAllCount(countSql);
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("specializeName", list.get(i).get("specializeName"));
				obj.put("state", list.get(i).get("state"));
				array.add(obj);
			}
			
			objmenu.put("data", array);
			objmenu.put("total", num);
		}else{
			objmenu.put("code", "0");
			objmenu.put("msg", "没有擅长标签信息");
		}
		return objmenu.toString();
	}


	@Override
	public String update(SpecializeBo specializeBo) {
		JSONObject objmenu = new JSONObject();
		int num = 0;
		Specialize specialize = new Specialize();
		try {
			BeanUtils.copyProperties(specialize, specializeBo);
			num = baseDao.updateDao(specialize);
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
	public String delete(SpecializeBo specializeBo) {
		JSONObject objmenu = new JSONObject();
		String sql = "delete from specialize WHERE id="+specializeBo.getId();
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
		String hql = "SELECT * from specialize where state=0"; 
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("specializeName", list.get(i).get("specializeName"));
				obj.put("state", list.get(i).get("state"));
				array.add(obj);
			}
			objmenu.put("data", array);
		}else{
			objmenu.put("code", "0");
			objmenu.put("msg", "没有擅长标签信息");
		}
		return objmenu.toString();
	}

}
