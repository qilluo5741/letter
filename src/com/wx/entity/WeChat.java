package com.wx.entity;

public class WeChat {
	//��֤URL��ʵ��
   private  String signature ;// ΢�ż���ǩ��
   private  String timestamp ;// ʱ���
   private  String nonce;// �����
   private  String echostr;//����ַ���
public String getSignature() {
	return signature;
}
public void setSignature(String signature) {
	this.signature = signature;
}
public String getTimestamp() {
	return timestamp;
}
public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
}
public String getNonce() {
	return nonce;
}
public void setNonce(String nonce) {
	this.nonce = nonce;
}
public String getEchostr() {
	return echostr;
}
public void setEchostr(String echostr) {
	this.echostr = echostr;
}
   
}
