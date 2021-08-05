package com.ep.service;

import com.ep.bo.HospitalBo;
import com.ep.entity.Hospital;

/**
 * 
 * @author yyx
 *
 */
public interface HospitalService {
	public String getPageList(HospitalBo hospitalBo);
	public String getHospital(HospitalBo hospitalBo);
	public String update(HospitalBo hospitalBo);
	public int save(Hospital hospital);
	public String getList();
	public String delete(Integer id);
}
