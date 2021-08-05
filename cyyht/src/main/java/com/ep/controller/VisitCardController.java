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

import com.ep.bo.VisitCardBo;
import com.ep.bo.VisitUserBo;
import com.ep.entity.Role;
import com.ep.service.VisitCardService;
import com.ep.service.VisitUserService;

import net.sf.json.JSONObject;

/***
 * 查询就诊卡
 * @author YYX
 *
 */

@Controller
@RequestMapping("/visitCard")
public class VisitCardController {
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
	 * 分页查询就诊卡信息
	 * @param userInfo
	 */
	@ResponseBody
	@RequestMapping("/getPageList")
	public void getPageList(@RequestBody VisitCardBo visitCardBo) {
		try {
			String str = visitCardService.getPageList(visitCardBo);
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
	public void save(@RequestBody VisitCardBo visitCardBo) {
		try {
			int num = visitCardService.save(visitCardBo);
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
	public void update(@RequestBody VisitCardBo visitCardBo) {
		try {
			String msg = visitCardService.update(visitCardBo);
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
	public void delete(@RequestBody VisitCardBo visitCardBo) {
		try {
			String msg = visitCardService.delete(visitCardBo);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
}
