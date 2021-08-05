package com.ep.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.bo.DepartmentBo;
import com.ep.dao.BaseDao;
import com.ep.entity.Department;
import com.ep.entity.User;
import com.ep.entity.VisitUser;
import com.ep.service.DepartmentService;
import com.ep.service.UserService;
import com.ep.util.MD5;
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	public BaseDao baseDao;
	
	@Override
	public int save(DepartmentBo departmentBo) {
		Department department = new Department();
		int num = 0;
		try {
			BeanUtils.copyProperties(department, departmentBo);
			department.setIsCommon(0);
			 num = baseDao.saveDao(department);
		} catch (IllegalAccessException e) {
			num = 0;
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			num = 0;
			e.printStackTrace();
		}
		
		return num;
	}


	@Override
	public String getPageList(DepartmentBo departmentBo) {
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT * "; 
		String sql = "from department where departmentName like '%"+departmentBo.getDepartmentName()+"%' and parentId="+departmentBo.getParentId();
		String countSql = "SELECT count(*) num "+sql;
		List<Map<String, Object>> list = baseDao.getPageList(hql+sql, departmentBo.getPageSize(), departmentBo.getPageNumber());
		if(list.size()>0){
			int num = baseDao.getAllCount(countSql);
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("departmentName", list.get(i).get("departmentName"));
				obj.put("logoUrl", list.get(i).get("logoUrl"));
				obj.put("parentId", list.get(i).get("parentId"));
				obj.put("isCommon", list.get(i).get("isCommon"));
				array.add(obj);
			}
			objmenu.put("data", array);
			objmenu.put("total", num);
		}else{
			objmenu.put("code", "0");
			objmenu.put("msg", "没有科室信息");
		}
		return objmenu.toString();
	}


	@Override
	public String update(DepartmentBo departmentBo) {
		JSONObject objmenu = new JSONObject();
		String sql = "select * from department where id="+departmentBo.getId();
		List<Department> list = baseDao.getAllList(sql);
		int num = 0;
		if(list.size()>0){
			Department department = new Department();
			try {
				BeanUtils.copyProperties(department, departmentBo);
				num = baseDao.updateDao(department);
			} catch (Exception e) {
				num = 0;
				e.printStackTrace();
			} 
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
	public String delete(DepartmentBo departmentBo) {
		JSONObject objmenu = new JSONObject();
		String selSQL = "select * from department where parentId="+departmentBo.getId();
		List<Department> list  = baseDao.getAllList(selSQL);
		if(list.size()>0){
			objmenu.put("code", "0");
			objmenu.put("message", "先删除二级科室再删除一级科室");
		}else{
			String sql = "delete from DEPARTMENT WHERE id="+departmentBo.getId();
			int num = baseDao.delById(sql);
			if(num>0){
				objmenu.put("code", "1");
				objmenu.put("message", "删除成功");
			}else{
				objmenu.put("code", "0");
				objmenu.put("message", "删除失败");
			}
		}
		
		return objmenu.toString();
	}


	@Override
	public String getList() {
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT * from department"; 
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("departmentName", list.get(i).get("departmentName"));
				obj.put("logoUrl", list.get(i).get("logoUrl"));
				obj.put("isCommon", list.get(i).get("isCommon"));
				obj.put("parentId", list.get(i).get("parentId"));
				array.add(obj);
			}
			objmenu.put("data", array);
		}else{
			objmenu.put("code", "0");
			objmenu.put("msg", "没有科室信息");
		}
		return objmenu.toString();
	}
	
}
