package com.wx.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wx.menu.Menu;
import com.wx.menu.ViewButton;
import com.wx.util.Config;
import com.wx.util.WeixinUtil;
/***
 *菜单管理
 */
@Controller
@RequestMapping("weixin")
public class MenuController {
	//查询菜单
	@SuppressWarnings({ "static-access", "unchecked" })
	@RequestMapping("menu")
	public String getMenu(ModelMap model){
		String menuJson=WeixinUtil.getMenu(Config.token);//请求并获取
		JSONObject jsobj=JSONObject.fromObject(menuJson).getJSONObject("menu");
		Map<String, Class> classMap = new HashMap<String, Class>();//设置解析的相关属性
		classMap.put("sub_button",ViewButton.class);
		classMap.put("button",ViewButton.class);
		//解析成对象
		Menu menu=(Menu) jsobj.toBean(jsobj, Menu.class,classMap);
		model.addAttribute("menu", menu);
		return "wxmenu";
	}
	
}
