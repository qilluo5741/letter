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
 * �ӿ����
 */
@Controller
public class MainController {
	//���Token�Ǹ�΢�ſ����߽���ʱ���
    //����������Ӣ����ĸ�����֣�����Ϊ3-32�ַ�
    private static String Token = "niewei";
    @RequestMapping(value="main",method=RequestMethod.POST)
    @ResponseBody
	public void mainPost(HttpServletRequest request, HttpServletResponse response){
    	try {
    		//����������룬�Լ���Ӧ����
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			//��Ӧ
			PrintWriter out=response.getWriter();
			//���ܴ���������Ϣ ��ת���ɼ�ֵ�Եļ���
			Map<String, String> map;
			map = MessageUtil.xmlToMap(request);
			//������΢�ź�
			String toUserName=map.get("ToUserName"); 
			//���ͷ��ʺţ�һ��OpenID��
			String fromUserName=map.get("FromUserName");
			// �ı���Ϣ����
			String content	=map.get("Content");
			//����  �û����͵����֣���װ������Ϣ,��xml��ʽ�������û�
			String message=MessageUtil.initText(toUserName, fromUserName, content);
			out.print(message);//����
			out.close();//�ر�
		} catch (Exception e) {
			//�����־��¼��Ϣ
			LogUtil.getInstance().warn("΢�ŷ��������Post������󣡿��ܲ�������΢������");
		} 
	}
    /***
     *  ΢��token����
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value="main",method=RequestMethod.GET)
    @ResponseBody 
	public String mainGet(WeChat we){
    	//��֤URL��ʵ��
        String signature = we.getSignature();// ΢�ż���ǩ��
        String timestamp =we.getTimestamp();// ʱ���
        String nonce = we.getNonce();// �����
        String echostr = we.getEchostr();//����ַ���
        List<String> params = new ArrayList<String>();
        params.add(Token);
        params.add(timestamp);
        params.add(nonce);
        //1. ��token��timestamp��nonce�������������ֵ�������
        Collections.sort(params, new Comparator<String>() {
            public int compare(String o1, String o2) {
                try {
                	return o1.compareTo(o2);
				} catch (Exception e) {
					return 0;
				}
            }
        });

        //2. �����������ַ���ƴ�ӳ�һ���ַ�������sha1����
        String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
        if (temp.equals(signature)) {
        	try {
				return echostr;
			} catch (Exception e) {
				LogUtil.getInstance().warn("�ַ�������sha1���ܳ�����Դ��΢������");
				return null;
			}
        }
       LogUtil.getInstance().warn("΢�ŷ�����������󣡲�������΢������");
        return null;
	}
}
