package com.ep.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.bo.VisitCardBo;
import com.ep.bo.VisitUserBo;
import com.ep.dao.BaseDao;
import com.ep.entity.VisitCard;
import com.ep.entity.VisitUser;
import com.ep.service.VisitCardService;
import com.ep.service.VisitUserService;
@Service("visitCardService")
public class VisitCardServiceImpl implements VisitCardService{
	@Autowired
	public BaseDao baseDao;
	
	@Override
	public String getPageList(VisitCardBo visitCardBo) {
		JSONObject objmenu = new JSONObject();
		if(StringUtils.isEmpty(visitCardBo.getPageSize().toString())){
			visitCardBo.setPageSize(20); 
		}
		if(StringUtils.isEmpty(visitCardBo.getPageNumber().toString())){
			visitCardBo.setPageNumber(1); 
		}
		String hql = "SELECT * ";
		String stateSql = "from visitcard where 1=1 and nickName like '%"+visitCardBo.getNickName()+"%'";
		if(visitCardBo.getState()==0){
			stateSql += " and state=0";
		}else if(visitCardBo.getState()==1){
			stateSql += " and state=1";
		}
		String countSql = "SELECT count(*) num "+stateSql;
		List<Map<String, Object>> list = baseDao.getPageList(hql+stateSql, visitCardBo.getPageSize(), visitCardBo.getPageNumber());
		int counts = baseDao.getAllCount(countSql);
		if(list.size()>0){
			objmenu.put("code", "1");
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i).get("id"));
				obj.put("uuid", list.get(i).get("uuid"));
				obj.put("nickName", list.get(i).get("nickName"));
				obj.put("headImg", list.get(i).get("headImg"));
				obj.put("state", list.get(i).get("state"));
				obj.put("createTime", list.get(i).get("createTime"));
				array.add(obj);
			}
			objmenu.put("data", array);
			objmenu.put("total", counts);
		}else{
			objmenu.put("code", "0");
			objmenu.put("message", "没有用户信息");
		}
		return objmenu.toString();
		
	}

	@Override
	public int save(VisitCardBo visitCardBo) {
		VisitCard visitCard = new VisitCard();
		int num = 0;
		try {
			String sql = "select * from visitcard where uuid='"+visitCardBo.getUuid()+"'";
			List<VisitCard> list = baseDao.getAllList(sql);
			if(list.size()>0){
				num = 1;
			}else{
				BeanUtils.copyProperties(visitCard, visitCardBo);
				visitCard.setState(0);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
				visitCard.setCreateTime(df.format(new Date()));
				num = baseDao.saveDao(visitCard);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			num = 0;
			e.printStackTrace();
		} 
		return num;
	}

	/***
	 * 拉黑/启用修改
	 */
	@Override
	public String update(VisitCardBo visitCardBo) {
		JSONObject objmenu = new JSONObject();
		String sql = "select * from visitcard WHERE id="+visitCardBo.getId();
		List<VisitCard> list = baseDao.getAllList(sql);
		int num = 0;
		if(list.size()>0){
			VisitCard visitCard = list.get(0);
			visitCard.setState(visitCard.getState());
			num = baseDao.updateDao(visitCard);
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
	
	/***
	 * 参数必须传ID和UUID
	 */
	@Override
	public String delete(VisitCardBo visitCardBo) {
		JSONObject objmenu = new JSONObject();
		String seluser = "delete from visituser where visituuid='"+visitCardBo.getUuid()+"'";
		String sql = "delete from visitcard WHERE id="+visitCardBo.getId();
		baseDao.delById(seluser);//删除就诊人员信息
		int num = baseDao.delById(sql);//删除就诊卡信息
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
