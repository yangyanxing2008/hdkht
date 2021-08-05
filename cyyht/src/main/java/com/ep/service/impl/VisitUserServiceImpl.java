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

import com.ep.bo.VisitUserBo;
import com.ep.dao.BaseDao;
import com.ep.entity.VisitUser;
import com.ep.service.VisitUserService;
@Service("visitUserService")
public class VisitUserServiceImpl implements VisitUserService{
	@Autowired
	public BaseDao baseDao;
	
	@Override
	public String getList(VisitUserBo visitUserBo) {
		JSONObject objmenu = new JSONObject();
		String hql = "SELECT a.*,b.headImg from visituser a left JOIN visitcard b ON a.visituuid = b.uuid where 1=1";
		if(StringUtils.isNotEmpty(visitUserBo.getVisituuid())){
			hql+=" and visituuid='"+visitUserBo.getVisituuid()+"'";
		}
		List<Map<String, Object>> list = baseDao.getAllList(hql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("visituuid", list.get(i).get("visituuid"));
				obj.put("userName", list.get(i).get("userName"));
				obj.put("telephone", list.get(i).get("telephone"));
				obj.put("icard", list.get(i).get("icard"));
				obj.put("sex", list.get(i).get("sex"));
				obj.put("condition", list.get(i).get("condition"));
				obj.put("headImg", list.get(i).get("headImg"));
				array.add(obj);
			}
			objmenu.put("data", array);
		}else{
			objmenu.put("code", "0");
			objmenu.put("message", "没有用户信息");
		}
		return objmenu.toString();
		
	}
	
	@Override
	public String getPageList(VisitUserBo visitUserBo) {
		JSONObject objmenu = new JSONObject();
		String hql = "select *,(select nickName FROM visitcard WHERE uuid=visituser.visituuid) as nickName,"
				+ "(select headImg FROM visitcard WHERE uuid=visituser.visituuid) as headImg from visituser ";
		String countSql = "SELECT count(*) num from  visituser";
		List<Map<String, Object>> list = baseDao.getPageList(hql, visitUserBo.getPageSize(), visitUserBo.getPageNumber());
		if(list.size()>0){
			objmenu.put("code", "1");
			int num = baseDao.getAllCount(countSql);
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("visituuid", list.get(i).get("visituuid"));
				obj.put("userName", list.get(i).get("userName"));
				obj.put("telephone", list.get(i).get("telephone"));
				obj.put("icard", list.get(i).get("iCard"));
				if(Integer.parseInt(list.get(i).get("sex").toString())==1){
					obj.put("sex", "男");
				}else{
					obj.put("sex", "女");
				}
				obj.put("condition", list.get(i).get("conditions"));
				obj.put("headImg", list.get(i).get("headImg"));
				obj.put("nickName", list.get(i).get("nickName"));
				array.add(obj);
			}
			objmenu.put("data", array);
			objmenu.put("total", num);
		}else{
			objmenu.put("code", "0");
			objmenu.put("message", "没有用户信息");
		}
		return objmenu.toString();
		
	}

	@Override
	public int save(VisitUserBo visitUserBo) {
		VisitUser visitUser = new VisitUser();
		int num = 0;
		try {
			BeanUtils.copyProperties(visitUser, visitUserBo);
			num = baseDao.saveDao(visitUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			num = 0;
			e.printStackTrace();
		} 
		return num;
	}

	@Override
	public String update(VisitUserBo visitUserBo) {
		JSONObject objmenu = new JSONObject();
		String sql = "select * from visituser WHERE id="+visitUserBo.getId();
		List<Map<String, Object>> list = baseDao.getAllList(sql);
		int num = 0;
		if(list.size()>0){
			VisitUser visitUser = new VisitUser();
			visitUser.setId(Integer.parseInt(list.get(0).get("id").toString()));
			visitUser.setiCard(list.get(0).get("iCard").toString());
			visitUser.setSex(Integer.parseInt(list.get(0).get("sex").toString()));
			visitUser.setTelephone(list.get(0).get("telephone").toString());
			visitUser.setUserName(list.get(0).get("userName").toString());
			try {
				visitUser.setConditions(visitUserBo.getConditions());
				
				num = baseDao.updateDao(visitUser);
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
	public String delete(VisitUserBo visitUserBo) {
		JSONObject objmenu = new JSONObject();
		String sql = "delete from visituser WHERE id="+visitUserBo.getId();
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
	
}
