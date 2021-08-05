package com.ep.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.dao.BaseDao;
import com.ep.service.MenuService;
@Service("menuService")
public class MenuServiceImpl implements MenuService{
	@Autowired
	public BaseDao baseDao;
	
	@Override
	public String getList() {
		String hql = "SELECT id,menuName,parentId from menu";
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		JSONObject objmenu = new JSONObject();
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("menuName", list.get(i).get("menuName"));
				obj.put("parentId", list.get(i).get("parentId"));
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
