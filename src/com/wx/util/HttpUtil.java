package com.wx.util;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
public class HttpUtil {
	/***
	 * get����
	 * @param url
	 * @return
	 */
	public static JSONObject doGetStr(String url){
		DefaultHttpClient httpC=new DefaultHttpClient();
		//��ʼ��
		HttpGet httpGet=new HttpGet(url);
		JSONObject jsonObj=null;//���ܽ��
		try {
			HttpResponse response=httpC.execute(httpGet);//����ִ�еĽ��
			HttpEntity entity= response.getEntity();//��ȡ���
			//�жϽ���Ƿ�Ϊ��
			if(entity!=null){
				//ת����Sting 
				String result=EntityUtils.toString(entity,"utf-8");//��ֹ����
				jsonObj=JSONObject.fromObject(result);//�ַ���ת��json��ʽ
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj;
	}
	/***
	 * post����
	 * @param url
	 * @param outStr
	 * @return
	 */
	public static JSONObject doPostStr(String url,String outStr){
		DefaultHttpClient httpC=new DefaultHttpClient();
		HttpPost httpPost=new HttpPost(url);
		JSONObject jsonObj=null;
		try {
			httpPost.setEntity(new StringEntity(outStr,"utf-8"));
			HttpResponse response=httpC.execute(httpPost);
			String resilt=EntityUtils.toString(response.getEntity(),"utf-8");
			System.out.println(resilt);
			jsonObj=JSONObject.fromObject(resilt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj;
	}
}
