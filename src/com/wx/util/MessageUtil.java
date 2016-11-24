package com.wx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.wx.entity.TextMessage;

public class MessageUtil {
	public static final String MESSAGE_TEXT="text";
	public static final String MESSAGE_NRWS="news";
	public static final String MESSAGE_IMAGE="image";
	public static final String MESSAGE_VIOCE="voice";
	public static final String MESSAGE_LINK="link";
	public static final String MESSAGE_LOCATION="location";
	public static final String MESSAGE_EVNET="event";
	public static final String MESSAGE_SUBSCRIBE="subscribe";//关注
	public static final String MESSAGE_UNSUBSCRIBE="unsubscribe";
	public static final String MESSAGE_CLICK="CLICK";
	public static final String MESSAGE_VIEW="VIEW";
	public static final String MESSAGE_VIDEO="video";
	public static final String MESSAGE_MUSIC="music";
	/***
	  * 将文本消息对象转为xml
	  * @param text
	  * @return
	  */
	public static String textMessageToXml(TextMessage text){
		XStream xs=new XStream();
		xs.alias("xml",text.getClass());
		return xs.toXML(text);
	}
	/***
	 * 将xml转换为map集合
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String ,String> map =new HashMap<String, String>();
		SAXReader sr=new SAXReader();
		InputStream ins=request.getInputStream();//从request中获取输入流
		Document doc=sr.read(ins);
		Element  root=doc.getRootElement();
		List<Element> list=root.elements();
		//遍历 保存集合
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	/**
	 * 组装文字消息
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage text=new TextMessage();
		text.setFromUserName(toUserName);//相反的发回去。从哪儿发来。
		text.setCreateTime(new Date().getTime()+"");
		text.setToUserName(fromUserName);
		text.setMsgType(MESSAGE_TEXT);
		text.setContent(content);
		return textMessageToXml(text);
	}
}
