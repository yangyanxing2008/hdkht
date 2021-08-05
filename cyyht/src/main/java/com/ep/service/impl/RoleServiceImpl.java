package com.ep.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.dao.BaseDao;
import com.ep.service.RoleService;
@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Autowired
	public BaseDao baseDao;
	
	@Override
	public String getList() {
		String hql = "SELECT id,roleName,createTime from role";
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		JSONObject objmenu = new JSONObject();
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("roleName", list.get(i).get("roleName"));
				obj.put("createTime", list.get(i).get("createTime"));
				array.add(obj);
			}
			objmenu.put("data", array);
		}else{
			objmenu.put("code", "0");
			objmenu.put("data", "");
		}
		return objmenu.toString();
		
	}

	
	
}
