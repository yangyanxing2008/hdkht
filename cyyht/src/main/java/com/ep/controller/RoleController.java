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

import com.ep.entity.Role;
import com.ep.service.RoleService;


@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
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

	/**
	 *查询数据
	 */
	@ResponseBody
	@RequestMapping("/getList")
	public void getCspz() {
		try {
			String str = roleService.getList();
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/***
	 * 修改
	 */
    @ResponseBody
	@RequestMapping("/update")
	public void update(@RequestBody Role role) {
		try {
			String  msg = "";//cspzService.saveOrUpdate(cspz);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	 
}
