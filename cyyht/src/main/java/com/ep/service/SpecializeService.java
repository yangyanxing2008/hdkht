package com.ep.service;

import com.ep.bo.SpecializeBo;

/**
 * 
 * @author yyx
 *
 */
public interface SpecializeService {
	public int save(SpecializeBo specializeBo);
	public String getPageList(SpecializeBo specializeBo);
	public String getList();
	public String update(SpecializeBo specializeBo);
	public String delete(SpecializeBo specializeBo);
}
