package com.ep.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.bo.HospitalBo;
import com.ep.entity.Hospital;
import com.ep.entity.User;
import com.ep.service.HospitalService;
import com.ep.service.UserService;
import com.ep.util.MD5;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/hospital")
public class HospitalController {
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private UserService userService;
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
	 * 分页查询医院信息
	 * @param userInfo
	 */
	@ResponseBody
	@RequestMapping("/getPageList")
	public void getPageList(@RequestBody HospitalBo hospitalBo) {
		try {
			String str = hospitalService.getPageList(hospitalBo);
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 查询所有医院信息
	 * @param userInfo
	 */
	@ResponseBody
	@RequestMapping("/getList")
	public void getList() {
		try {
			String str = hospitalService.getList();
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 查询所有医院信息
	 * @param userInfo
	 */
	@ResponseBody
	@RequestMapping("/getHospital")
	public void getHospital(@RequestBody HospitalBo hospitalBo) {
		try {
			String str = hospitalService.getHospital(hospitalBo);
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/save")
	public void save(@RequestBody HospitalBo hospitalBo) {
		JSONObject uobject = new JSONObject();
		try {
			//保存用户信息
			List list = userService.getUserByNumber(hospitalBo.getAccountNumber());
			if(list.size()>0){
				uobject.put("code", "0");
				uobject.put("message", "账号已经注册，重新输入！");
			}else{
				User user = new User();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
				user.setCreateTime(df.format(new Date()));
				user.setEnable(0);
				user.setRoleId(3);
				user.setAccountNumber(hospitalBo.getAccountNumber());
				user.setAccountPwd(MD5.crypt(hospitalBo.getAccountPwd()).substring(0, 20));
				int uid = userService.save(user);
				if(uid>0){
					Hospital hospital = new Hospital();
					int num = 0;
					try {
						BeanUtils.copyProperties(hospital, hospitalBo);
						hospital.setUserId(uid);
						num = hospitalService.save(hospital);
					} catch (Exception e) {
						num = 0;
						e.printStackTrace();
					} 
					if(num>0){
						uobject.put("code", "1");
						uobject.put("message", "添加成功！");
					}
				}else{
					uobject.put("code", "0");
					uobject.put("message", "保存用户账号不成功，请联系管理员！");
				}
			}			
			//保存医院信息
			response.getWriter().print(uobject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/***
	 * 删除
	 */
    @ResponseBody
	@RequestMapping("/delete")
	public void delete(@RequestBody HospitalBo hospitalBo) {
		try {
			String  msg = hospitalService.delete(hospitalBo.getId());
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /***
	 * 修改
	 */
    @ResponseBody
	@RequestMapping("/update")
	public void update(@RequestBody HospitalBo hospitalBo) {
		try {
			String  msg = hospitalService.update(hospitalBo);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 
}
