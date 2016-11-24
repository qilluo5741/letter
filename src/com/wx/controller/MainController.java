package com.wx.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wx.entity.WeChat;
import com.wx.util.LogUtil;
import com.wx.util.MessageUtil;
import com.wx.util.SHA1;
/**
 * 接口入口
 */
@Controller
public class MainController {
	//这个Token是给微信开发者接入时填的
    //可以是任意英文字母或数字，长度为3-32字符
    private static String Token = "niewei";
    @RequestMapping(value="main",method=RequestMethod.POST)
    @ResponseBody
	public void mainPost(HttpServletRequest request, HttpServletResponse response){
    	try {
    		//设置请求编码，以及相应编码
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			//响应
			PrintWriter out=response.getWriter();
			//接受传过来的信息 并转换成键值对的集合
			Map<String, String> map;
			map = MessageUtil.xmlToMap(request);
			//开发者微信号
			String toUserName=map.get("ToUserName"); 
			//发送方帐号（一个OpenID）
			String fromUserName=map.get("FromUserName");
			// 文本消息内容
			String content	=map.get("Content");
			//测试  用户发送的文字，组装文字消息,以xml形式。返回用户
			String message=MessageUtil.initText(toUserName, fromUserName, content);
			out.print(message);//返回
			out.close();//关闭
		} catch (Exception e) {
			//添加日志记录信息
			LogUtil.getInstance().warn("微信服务器入口Post请求错误！可能不是来自微信请求！");
		} 
	}
    /***
     *  微信token配置
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value="main",method=RequestMethod.GET)
    @ResponseBody 
	public String mainGet(WeChat we){
    	//验证URL真实性
        String signature = we.getSignature();// 微信加密签名
        String timestamp =we.getTimestamp();// 时间戳
        String nonce = we.getNonce();// 随机数
        String echostr = we.getEchostr();//随机字符串
        List<String> params = new ArrayList<String>();
        params.add(Token);
        params.add(timestamp);
        params.add(nonce);
        //1. 将token、timestamp、nonce三个参数进行字典序排序
        Collections.sort(params, new Comparator<String>() {
            public int compare(String o1, String o2) {
                try {
                	return o1.compareTo(o2);
				} catch (Exception e) {
					return 0;
				}
            }
        });

        //2. 将三个参数字符串拼接成一个字符串进行sha1加密
        String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
        if (temp.equals(signature)) {
        	try {
				return echostr;
			} catch (Exception e) {
				LogUtil.getInstance().warn("字符串进行sha1加密出错！来源于微信请求！");
				return null;
			}
        }
       LogUtil.getInstance().warn("微信服务器请求错误！不是来自微信请求！");
        return null;
	}
}
