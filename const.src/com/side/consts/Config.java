package com.side.consts;

import org.apache.log4j.Logger;

import com.side.util.beans.DealInfo;
import com.side.util.pkg.DealUtil;

public class Config {
	
	//��־
	private static final Logger log = Logger.getLogger(Config.class);
	
	// log4j�����ļ�����
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
		// ȫ�ֳ����࣬����ʵ����
	}
	
}
