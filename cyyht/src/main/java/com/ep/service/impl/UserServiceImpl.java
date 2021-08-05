package com.ep.service.impl;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.dao.BaseDao;
import com.ep.entity.User;
import com.ep.service.UserService;
import com.ep.util.MD5;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	public BaseDao baseDao;
	
	@Override
	public String login(User user){
		JSONObject objmenu = new JSONObject();
		if(user!=null && StringUtils.isNotEmpty(user.getAccountNumber()) && StringUtils.isNotEmpty(user.getAccountPwd())){
			String hql = "SELECT * from `user` where enable=0 and accountNumber='"+user.getAccountNumber()+"' and accountPwd='"+MD5.crypt(user.getAccountPwd()).substring(0, 20)+"'";
			List<Map<String, Object>> list = baseDao.getAllList(hql);
			if(list.size()>0){
				objmenu.put("code", "1");
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(0).get("id"));
				obj.put("accountNumber", list.get(0).get("accountNumber"));
				obj.put("roleId", list.get(0).get("roleId"));
				obj.put("uType", list.get(0).get("uType"));
				objmenu.put("data", obj);
			}else{
				objmenu.put("code", "0");
				objmenu.put("msg", "没有此账号信息");
			}
			
		}else{
			objmenu.put("code", "0");
			objmenu.put("message", "账号或密码错误！");
		}
		return objmenu.toString();
		
	}
	

	@Override
	public int save(User user) {
	    int num = baseDao.saveDao(user);
		return num;
	}


	@Override
	public List getUserByNumber(String accountNumber) {
		String sql = "select * from user WHERE accountNumber = '"+accountNumber+"'";
		List<Map<String, Object>> list = baseDao.getAllList(sql);
		return list;
	}
	
}
