package com.ep.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.bo.VisitCardBo;
import com.ep.bo.VisitUserBo;
import com.ep.entity.Role;
import com.ep.service.VisitCardService;
import com.ep.service.VisitUserService;

import net.sf.json.JSONObject;

/**
 * 就诊用户
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/visitUser")
public class VisitUserController {
	@Autowired
	private VisitUserService visitUserService;
	
	@Autowired
	private VisitCardService visitCardService;
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
	 * 根据UUID查询用户信息
	 * @param userInfo
	 */
	@ResponseBody
	@RequestMapping("/getList")
	public void getList(@RequestBody VisitUserBo visitUserBo) {
		try {
			String str = visitUserService.getList(visitUserBo);
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 根据分页查询信息
	 * @param userInfo
	 */
	@ResponseBody
	@RequestMapping("/getPageList")
	public void getPageList(@RequestBody VisitUserBo visitUserBo) {
		try {
			String str = visitUserService.getPageList(visitUserBo);
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 添加就诊用户信息
	 * 先添加就诊卡再添加就诊用户
	 */
    @ResponseBody
	@RequestMapping("/save")
	public void save(@RequestBody VisitUserBo visitUserBo) {
		try {
			VisitCardBo visitCardBo = new VisitCardBo();
			visitCardBo.setUuid(visitUserBo.getVisituuid());
			visitCardBo.setNickName(visitUserBo.getNickName());
			visitCardBo.setHeadImg(visitUserBo.getHeadImg());
			
			int cardNum = visitCardService.save(visitCardBo);
			int num = 0;
			JSONObject jsonObject = new JSONObject();
			if(cardNum>0){
				num = visitUserService.save(visitUserBo);
				if(num>0){
					jsonObject.put("code", "1");
					jsonObject.put("data", num);
					jsonObject.put("message", "保存成功!");
				}else{
					jsonObject.put("code", "0");
					jsonObject.put("message", "保存就诊用户失败!");
				}
			}else{
				jsonObject.put("code", "0");
				jsonObject.put("message", "保存就诊卡信息失败!");
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
	public void update(@RequestBody VisitUserBo visitUserBo) {
		try {
			String msg = visitUserService.update(visitUserBo);
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
	public void delete(@RequestBody VisitUserBo visitUserBo) {
		try {
			String msg = visitUserService.delete(visitUserBo);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
}
