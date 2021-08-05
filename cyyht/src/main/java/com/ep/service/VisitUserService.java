package com.ep.service;

import java.util.List;

import com.ep.bo.VisitUserBo;
import com.ep.entity.Menu;
import com.ep.entity.User;
import com.ep.entity.VisitUser;



/**
 * 
 * @author yyx
 *
 */
public interface VisitUserService {
	public String getList(VisitUserBo visitUserBo);
	public String getPageList(VisitUserBo visitUserBo);
	public int save(VisitUserBo visitUserBo);
	public String update(VisitUserBo visitUserBo);
	public String delete(VisitUserBo visitUserBo);
}
