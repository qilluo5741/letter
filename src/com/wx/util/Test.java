package com.wx.util;

import net.sf.json.JSONObject;

import com.wx.entity.AccessToken;


public class Test {
	@SuppressWarnings({ "unchecked", "static-access" })
	public static void main(String[] args) {
		AccessToken token =WeixinUtil.getAccessToken();
		System.out.println("token ��"+token.getToken());
		System.out.println("ʱ��"+token.getExpires());
	 /*	String token="AnBFBnatfarJfC_sJE2j-XDtG2q6pqhYq0aerxQfdxQyYvq31tYTxb4P_NQ7c31eArAsYSCaiGua9CI8p4CMb4O7PP7s3i2icCX4RBMr3d4BVIhACAOIV";
		//���Ի�ȡ�˵�
		String menuJson=WeixinUtil.getMenu(token);
		System.out.println(menuJson);
		JSONObject jsobj=JSONObject.fromObject(menuJson).getJSONObject("menu");
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("sub_button",ViewButton.class);
		classMap.put("button",ViewButton.class);
		Menu menu=(Menu) jsobj.toBean(jsobj, Menu.class,classMap);*/
		/*for(Button btn:menu.getButton()){
			System.out.println("�ܲ˵����ƣ�"+btn.getName()+"_����:"+btn.getType());
				for(Button b:btn.getSub_button()){
					System.out.println("�Ӳ˵�����"+b.getName()+"_����"+b.getType());
					System.out.println(((ViewButton)b).getUrl());
				}
				System.out.println(((ViewButton)btn).getUrl());
		}*/
		
		
		//�����˵�
		
		/*
		String menu1=JSONObject.fromObject(WeixinUtil.initMenu()).toString();//ת��json
		int result=WeixinUtil.createMenu(Config.token, menu1);
			if(result==0){
				System.out.println("�����ɹ���");
			}
			else{
				System.out.println(result+"����ʧ�ܣ�");
			}*/
	}
}
