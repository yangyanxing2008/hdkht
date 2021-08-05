package com.ep.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.bo.DoctorBo;
import com.ep.bo.VisitUserBo;
import com.ep.entity.Role;
import com.ep.service.DoctorService;
import com.ep.service.VisitUserService;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.request = request;
		this.response = response;
	}

	/***
	 * 分页查询医生信息
	 * @param userInfo
	 */
	@ResponseBody
	@RequestMapping("/getPageList")
	public void getPageList(@RequestBody DoctorBo doctorBo) {
		try {
			String str = doctorService.getPageList(doctorBo);
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 所有医生
	 * @param userInfo
	 */
	@ResponseBody
	@RequestMapping("/geList")
	public void getList() {
		try {
			String str = doctorService.getList();
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 医生列表
	 * @param userInfo
	 */
	@ResponseBody
	@RequestMapping("/getDoctorList")
	public void getDoctorList(@RequestBody DoctorBo doctorBo) {
		try {
			String str = doctorService.getDoctorList(doctorBo);
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 查询所有医生信息
	 * @param userInfo
	 */
	@ResponseBody
	@RequestMapping("/getDoctor")
	public void getDoctor(@RequestBody DoctorBo doctorBo) {
		try {
			String str = doctorService.getDoctor(doctorBo);
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 保存
	 */
    @ResponseBody
	@RequestMapping("/save")
	public void save(@RequestBody DoctorBo doctorBo) {
		try {
			int num = doctorService.save(doctorBo);
			JSONObject jsonObject = new JSONObject();
			if(num>0){
				jsonObject.put("code", "1");
				jsonObject.put("data", num);
				jsonObject.put("message", "保存成功!");
			}else{
				jsonObject.put("code", "0");
				jsonObject.put("message", "保存失败!");
			}
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /***
	 * 修改
	 */
    @ResponseBody
	@RequestMapping("/update")
	public void update(@RequestBody DoctorBo doctorBo) {
		try {
			String msg = doctorService.update(doctorBo);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /***
	 * 删除
	 */
    @ResponseBody
	@RequestMapping("/delete")
	public void delete(@RequestBody DoctorBo doctorBo) {
		try {
			String msg = doctorService.delete(doctorBo);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
}
