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
 *�˵�����
 */
@Controller
@RequestMapping("weixin")
public class MenuController {
	//��ѯ�˵�
	@SuppressWarnings({ "static-access", "unchecked" })
	@RequestMapping("menu")
	public String getMenu(ModelMap model){
		String menuJson=WeixinUtil.getMenu(Config.token);//���󲢻�ȡ
		JSONObject jsobj=JSONObject.fromObject(menuJson).getJSONObject("menu");
		Map<String, Class> classMap = new HashMap<String, Class>();//���ý������������
		classMap.put("sub_button",ViewButton.class);
		classMap.put("button",ViewButton.class);
		//�����ɶ���
		Menu menu=(Menu) jsobj.toBean(jsobj, Menu.class,classMap);
		model.addAttribute("menu", menu);
		return "wxmenu";
	}
	
}
