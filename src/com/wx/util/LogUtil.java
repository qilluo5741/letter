package com.wx.util;

import org.apache.log4j.Logger;

public class LogUtil {
	//����һ����־��¼��
	private static Logger logger = null;
	/**
	 * ���ⲿ�ṩ��������ķ���
	 * @return
	 */
	public static Logger getInstance(){
		if(logger == null){
			new LogUtil();
		}
		System.out.println(logger);
		return logger;
	}
	
	public LogUtil() {
		/*// TODO Auto-generated constructor stub
		//��ʼ����־�ļ�
		String filePath = this.getClass().getResource("/").getPath();
		System.out.println(filePath);
		filePath = filePath.substring(1);
		System.out.println(filePath);
		//��װ������Ϣ�ļ�·��
		filePath = filePath+"log4j.properties";
		System.out.println(filePath);*/
		
		//ʵ������־��¼��
		logger = Logger.getLogger(this.getClass());
		
		//������־��¼������־�ļ�
		//PropertyConfigurator.configure(filePath);
	}
	
	/*public static void main(String[] args) {
		new LogUtil().logger.info("main���������á�������");
	}*/
}
