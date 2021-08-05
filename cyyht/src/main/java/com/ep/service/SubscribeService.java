package com.ep.service;

import com.ep.bo.PositionalBo;
import com.ep.bo.SubscribeBo;

/**
 * 
 * @author yyx
 *  预约
 */
public interface SubscribeService {
	public int save(SubscribeBo subscribeBo);
	public String getList();
	public String getSubscribe(int subscribeId);
	public String update(SubscribeBo subscribeBo);
	public String updateHx(SubscribeBo subscribeBo);
	public String delete(SubscribeBo subscribeBo);
	public String getPageList(SubscribeBo subscribeBo);
}
