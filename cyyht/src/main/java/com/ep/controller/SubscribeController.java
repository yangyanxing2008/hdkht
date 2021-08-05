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
import com.ep.bo.SubscribeBo;
import com.ep.bo.VisitUserBo;
import com.ep.entity.Role;
import com.ep.service.PositionalService;
import com.ep.service.SubscribeService;
import com.ep.service.VisitUserService;

import net.sf.json.JSONObject;

/***
 * 预约
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/subscribe")
public class SubscribeController {
	@Autowired
	private SubscribeService subscribeService;
	
	@Autowired
	private VisitUserService visitUserService;
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
	 * 提交表单信息
	 * @param userInfo
	 */
	@ResponseBody
	@RequestMapping("/subscribeTj")
	public void subscribeTj(@RequestBody SubscribeBo subscribeBo) {
		try {
			//先更新患者病情再执行预约保存
			VisitUserBo visitUserBo = new VisitUserBo();
			visitUserBo.setId(subscribeBo.getVisitUserId());
			visitUserBo.setConditions(subscribeBo.getConditions());
			visitUserService.update(visitUserBo);
			//保存预约表单
			int num = subscribeService.save(subscribeBo);
			String str = subscribeService.getSubscribe(num);
			
			
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
	public void save(@RequestBody SubscribeBo subscribeBo) {
		try {
			//先更新患者病情再执行预约保存
			VisitUserBo visitUserBo = new VisitUserBo();
			visitUserBo.setId(subscribeBo.getVisitUserId());
			visitUserBo.setConditions(subscribeBo.getConditions());
			visitUserService.update(visitUserBo);
			//保存预约表单
			int num = subscribeService.save(subscribeBo);
			JSONObject jsonObject = new JSONObject();
			if(num>0){
				jsonObject.put("code", "1");
				jsonObject.put("data", num);//返回表单ID
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
	public void update(@RequestBody SubscribeBo subscribeBo) {
		try {
			String msg = subscribeService.update(subscribeBo);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    
    /***
	 * 修改
	 */
    @ResponseBody
	@RequestMapping("/updateHx")
	public void updateHx(@RequestBody SubscribeBo subscribeBo) {
		try {
			String msg = subscribeService.updateHx(subscribeBo);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    /***
	 * 预约单号查询
	 */
    @ResponseBody
	@RequestMapping("/getById")
	public void getById(@RequestBody SubscribeBo subscribeBo) {
		try {
			String msg = subscribeService.getSubscribe(subscribeBo.getId());
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
	public void delete(@RequestBody SubscribeBo subscribeBo) {
		try {
			String msg = subscribeService.delete(subscribeBo);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    
    
    /***
	 * 查询所有的预约单
	 */
    @ResponseBody
	@RequestMapping("/getList")
	public void getList() {
		try {
			String msg = subscribeService.getList();
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    /***
	 * 查询所有的预约单
	 */
    @ResponseBody
	@RequestMapping("/getPageList")
	public void getPageList(@RequestBody SubscribeBo subscribeBo) {
		try {
			String msg = subscribeService.getPageList(subscribeBo);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
}
