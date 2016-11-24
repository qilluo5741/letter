package com.wx.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.wx.entity.AccessToken;
import com.wx.menu.Button;
import com.wx.menu.Menu;
import com.wx.menu.ViewButton;


public class WeixinUtil {
	private static final String APPID="wx51afa0109e297b41";//appId
	private static final String APPSECRET="533023ba5626a4978c3a8eab5a13b302";//appsecret
	private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";//��ȡtoken
	private static final String GET_MENU="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";//��ȡ�˵�
	private static final String CREATE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";//�Զ���˵��ӿ�
	/***
	 * ��ȡaccess_token
	 * @return
	 */
	public static AccessToken getAccessToken(){
		AccessToken token=new AccessToken();
		String url=ACCESS_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObj=HttpUtil.doGetStr(url);//��ȡ���
		if(jsonObj!=null){
			token.setToken(jsonObj.getString("access_token"));
			token.setExpires(jsonObj.getInt("expires_in"));
		}
		return token;
	}
	 
	
	/**
	 * ��ȡ�˵�
	 * @param access_token
	 * @return
	 */
	public static String getMenu(String access_token){
		try {
			String url=GET_MENU.replace("ACCESS_TOKEN", access_token);
			JSONObject jsonObj=HttpUtil.doGetStr(url);//��ȡ���
			return jsonObj.toString();
		} catch (Exception e) {
			LogUtil.getInstance().warn("��ȡ�˵������쳣�������������ӡ���");
			return null;
		}
	}
	/***
	 * �����˵�
	 * @param token
	 * @param menu
	 * @return
	 */
	public static int createMenu(String token,String menu){
		int result=0;
		String url=CREATE_MENU_URL.replace("ACCESS_TOKEN",token);
		JSONObject jsonObject=HttpUtil.doPostStr(url, menu);
		if(jsonObject!=null){
			result=jsonObject.getInt("errcode");
		}
		return result;
	}
	/***
	 * ��װ�Զ���˵�
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu=new Menu();
		ViewButton button13 =new ViewButton();
		button13.setName("�û�����");
		button13.setType("view");
		button13.setUrl("http://www.lovehealth.top/ihealth");

		ViewButton button14 =new ViewButton();
		button14.setName("�û�����2");
		button14.setType("view");
		button14.setUrl("http://www.lovehealth.top/ihealth");
		
		ViewButton button15 =new ViewButton();
		button15.setName("�û�����3");
		button15.setType("view");
		button15.setUrl("http://www.lovehealth.top/ihealth");
		
		 
		//���˵�
		Button button=new Button();
		button.setName("�˵�");//������������Ӳ˵�
		List<Button> list=new ArrayList<Button>();

		list.add(button13);
		list.add(button14);
		list.add(button15);
		button.setSub_button(list);
		
		/*********************************/
		ViewButton button21 =new ViewButton();
		button21.setName("1111");
		button21.setType("view");
		button21.setUrl("http://www.lovehealth.top/ihealthWeb");
		
		/*********************************/
		ViewButton button31 =new ViewButton();
		button31.setName("2222");
		button31.setType("view");
		button31.setUrl("http://www.lovehealth.top/ihealthWeb");
		List<Button> list2=new ArrayList<Button>();
		list2.add(button);
		list2.add(button21);
		list2.add(button31);
		//�������˵�
		menu.setButton(list2);
		return menu;
	}
}
