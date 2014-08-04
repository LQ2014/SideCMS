package com.side.consts;

import org.apache.log4j.Logger;

import com.side.util.beans.DealInfo;
import com.side.util.pkg.DealUtil;

public class Config {
	
	//日志
	private static final Logger log = Logger.getLogger(Config.class);
	
	// log4j配置文件名称
	private static final String log4jFileName;

	static {
		log4jFileName = Config.class.getClassLoader().getResource("/").getPath() + "log4j.properties";
		DealUtil.reBuildDealMap();
	}

	public static String getLog4jfilename() {
		return log4jFileName;
	}
	
	public static DealInfo getDealInfo(String path) {
		return DealUtil.getDealMap().get(path);
	}

	private Config() {
		// 全局常量类，不能实例化
	}
	
}
