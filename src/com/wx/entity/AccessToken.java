package com.wx.entity;

public class AccessToken {
	private String token;//	 ��ȡ����ƾ֤
	private int expires;//	 ƾ֤��Чʱ�䣬��λ����
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpires() {
		return expires;
	}
	public void setExpires(int expires) {
		this.expires = expires;
	}
	
}
