package com.wx.entity;

public class AccessToken {
	private String token;//	 获取到的凭证
	private int expires;//	 凭证有效时间，单位：秒
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
