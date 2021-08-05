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

import com.ep.bo.PositionalBo;
import com.ep.bo.VisitUserBo;
import com.ep.entity.Role;
import com.ep.service.PositionalService;
import com.ep.service.VisitUserService;

import net.sf.json.JSONObject;

/***
 * 职称
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/positional")
public class PositionalController {
	@Autowired
	private PositionalService positionalService;
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
	 * 分页查询职称信息
	 * @param userInfo
	 */
	@ResponseBody
	@RequestMapping("/getPageList")
	public void getPageList(@RequestBody PositionalBo positionalBo) {
		try {
			String str = positionalService.getPageList(positionalBo);
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping("/getList")
	public void getList() {
		try {
			String str = positionalService.getList();
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
	public void save(@RequestBody PositionalBo positionalBo) {
		try {
			int num = positionalService.save(positionalBo);
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
	public void update(@RequestBody PositionalBo positionalBo) {
		try {
			String msg = positionalService.update(positionalBo);
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
	public void delete(@RequestBody PositionalBo positionalBo) {
		try {
			String msg = positionalService.delete(positionalBo);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
}
