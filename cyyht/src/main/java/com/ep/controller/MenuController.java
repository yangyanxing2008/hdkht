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

import com.ep.entity.CSPZ;
import com.ep.service.MenuService;


@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;
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
	@RequestMapping("/getMenuList")
	public void getCspz() {
		try {
			String str = menuService.getList();
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
	public void update(@RequestBody CSPZ cspz) {
		try {
			String  msg = "";//cspzService.saveOrUpdate(cspz);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	 
}
