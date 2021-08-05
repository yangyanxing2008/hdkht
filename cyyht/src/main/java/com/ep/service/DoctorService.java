package com.ep.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.ep.bo.DepartmentBo;
import com.ep.bo.DoctorBo;
import com.ep.entity.Doctor;

/**
 * 
 * @author yyx
 *
 */
public interface DoctorService {
	public int save(DoctorBo doctorBo);
	public String getPageList(DoctorBo DoctorBo);
	public String getList();
	public String getDoctorList(@RequestBody DoctorBo doctorBo);
	public String getDoctor(DoctorBo doctorBo);
	public String update(DoctorBo doctorBo);
	public String delete(DoctorBo doctorBo);
}
