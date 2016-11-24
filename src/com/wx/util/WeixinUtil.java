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
	private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";//获取token
	private static final String GET_MENU="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";//获取菜单
	private static final String CREATE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";//自定义菜单接口
	/***
	 * 获取access_token
	 * @return
	 */
	public static AccessToken getAccessToken(){
		AccessToken token=new AccessToken();
		String url=ACCESS_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObj=HttpUtil.doGetStr(url);//获取结果
		if(jsonObj!=null){
			token.setToken(jsonObj.getString("access_token"));
			token.setExpires(jsonObj.getInt("expires_in"));
		}
		return token;
	}
	 
	
	/**
	 * 获取菜单
	 * @param access_token
	 * @return
	 */
	public static String getMenu(String access_token){
		try {
			String url=GET_MENU.replace("ACCESS_TOKEN", access_token);
			JSONObject jsonObj=HttpUtil.doGetStr(url);//获取结果
			return jsonObj.toString();
		} catch (Exception e) {
			LogUtil.getInstance().warn("获取菜单出现异常。请检查网络连接。。");
			return null;
		}
	}
	/***
	 * 创建菜单
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
	 * 组装自定义菜单
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu=new Menu();
		ViewButton button13 =new ViewButton();
		button13.setName("用户反馈");
		button13.setType("view");
		button13.setUrl("http://www.lovehealth.top/ihealth");

		ViewButton button14 =new ViewButton();
		button14.setName("用户反馈2");
		button14.setType("view");
		button14.setUrl("http://www.lovehealth.top/ihealth");
		
		ViewButton button15 =new ViewButton();
		button15.setName("用户反馈3");
		button15.setType("view");
		button15.setUrl("http://www.lovehealth.top/ihealth");
		
		 
		//主菜单
		Button button=new Button();
		button.setName("菜单");//下面添加两个子菜单
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
		//设置主菜单
		menu.setButton(list2);
		return menu;
	}
}
