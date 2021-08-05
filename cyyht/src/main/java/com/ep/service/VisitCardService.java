package com.ep.service;


import com.ep.bo.VisitCardBo;



/**
 * 
 * @author yyx
 *
 */
public interface VisitCardService {
	public String getPageList(VisitCardBo visitCardBo);
	public int save(VisitCardBo visitCardBo);
	public String update(VisitCardBo visitCardBo);
	public String delete(VisitCardBo visitCardBo);
}
