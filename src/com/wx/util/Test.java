package com.wx.util;

import net.sf.json.JSONObject;

import com.wx.entity.AccessToken;


public class Test {
	@SuppressWarnings({ "unchecked", "static-access" })
	public static void main(String[] args) {
		AccessToken token =WeixinUtil.getAccessToken();
		System.out.println("token ："+token.getToken());
		System.out.println("时间"+token.getExpires());
	 /*	String token="AnBFBnatfarJfC_sJE2j-XDtG2q6pqhYq0aerxQfdxQyYvq31tYTxb4P_NQ7c31eArAsYSCaiGua9CI8p4CMb4O7PP7s3i2icCX4RBMr3d4BVIhACAOIV";
		//测试获取菜单
		String menuJson=WeixinUtil.getMenu(token);
		System.out.println(menuJson);
		JSONObject jsobj=JSONObject.fromObject(menuJson).getJSONObject("menu");
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("sub_button",ViewButton.class);
		classMap.put("button",ViewButton.class);
		Menu menu=(Menu) jsobj.toBean(jsobj, Menu.class,classMap);*/
		/*for(Button btn:menu.getButton()){
			System.out.println("总菜单名称："+btn.getName()+"_类型:"+btn.getType());
				for(Button b:btn.getSub_button()){
					System.out.println("子菜单名："+b.getName()+"_类型"+b.getType());
					System.out.println(((ViewButton)b).getUrl());
				}
				System.out.println(((ViewButton)btn).getUrl());
		}*/
		
		
		//创建菜单
		
		/*
		String menu1=JSONObject.fromObject(WeixinUtil.initMenu()).toString();//转成json
		int result=WeixinUtil.createMenu(Config.token, menu1);
			if(result==0){
				System.out.println("创建成功！");
			}
			else{
				System.out.println(result+"创建失败！");
			}*/
	}
}
