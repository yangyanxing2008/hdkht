package com.ep.service;

import com.ep.bo.DepartmentBo;

/**
 * 
 * @author yyx
 *
 */
public interface DepartmentService {
	public int save(DepartmentBo departmentBo);
	public String getPageList(DepartmentBo departmentBo);
	public String update(DepartmentBo departmentBo);
	public String delete(DepartmentBo departmentBo);
	public String getList();
	
}
