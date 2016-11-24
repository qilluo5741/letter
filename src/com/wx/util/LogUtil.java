package com.wx.util;

import org.apache.log4j.Logger;

public class LogUtil {
	//定义一个日志记录器
	private static Logger logger = null;
	/**
	 * 向外部提供产生对象的方法
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
		//初始化日志文件
		String filePath = this.getClass().getResource("/").getPath();
		System.out.println(filePath);
		filePath = filePath.substring(1);
		System.out.println(filePath);
		//组装配置信息文件路径
		filePath = filePath+"log4j.properties";
		System.out.println(filePath);*/
		
		//实例化日志记录器
		logger = Logger.getLogger(this.getClass());
		
		//配置日志记录器的日志文件
		//PropertyConfigurator.configure(filePath);
	}
	
	/*public static void main(String[] args) {
		new LogUtil().logger.info("main方法被调用。。。。");
	}*/
}
