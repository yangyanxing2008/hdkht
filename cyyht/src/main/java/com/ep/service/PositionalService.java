package com.ep.service;

import com.ep.bo.PositionalBo;

/**
 * 
 * @author yyx
 *  职称
 */
public interface PositionalService {
	public int save(PositionalBo positionalBo);
	public String getPageList(PositionalBo positionalBo);
	public String update(PositionalBo positionalBo);
	public String delete(PositionalBo positionalBo);
	public String getList();
}
