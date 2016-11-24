package com.wx.quart;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.wx.entity.AccessToken;
import com.wx.util.Config;
import com.wx.util.WeixinUtil;

public class getToken extends QuartzJobBean{
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		 AccessToken token =WeixinUtil.getAccessToken();
		 Config.token=token.getToken();
		 System.out.println("我执行了一次获取token"+" token是："+token.getToken());
	}

	
	public void initToken(){
		 AccessToken token =WeixinUtil.getAccessToken();
		 Config.token=token.getToken();
		 System.out.println("我初始了token"+" token是："+token.getToken());
	}
}
