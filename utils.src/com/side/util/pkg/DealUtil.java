package com.side.util.pkg;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.side.util.annotation.Deal;
import com.side.util.beans.DealInfo;

public class DealUtil {
	
	// 日志
	private static Logger log = Logger.getLogger(DealUtil.class);
	
	private static Map<String, DealInfo> dealMap = null;
	
	public synchronized static Map<String, DealInfo> getDealMap() {
		if (null == dealMap) {
			reBuildDealMap();
		}
		return dealMap;
	}
	
	public synchronized static void reBuildDealMap() {
		initDealMap();
		List<String> clazzs = PkgUtil.getClassName("");
		if (null != clazzs) {
			for (String clazz : clazzs) {
				try {
					add2DealMap(Class.forName(clazz));
				} catch (ClassNotFoundException e) {
					log.error("加载类：" + clazz + "失败，原因：Class not found...");
				}
			}
		} else {
			log.error("遍历包失败...");
		}
		
	}
	
	private static void add2DealMap(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Deal.class)) {
				Deal deal = method.getAnnotation(Deal.class);
				DealInfo dealInfo = new DealInfo(clazz, method, deal);
				dealMap.put(deal.path(), dealInfo);
				log.info("Loading " + dealInfo);
			}
		}
	}
	
	private static void initDealMap() {
		if (null == dealMap) {
			dealMap = new HashMap<String, DealInfo>();
		} else {
			dealMap.clear();
		}
	}
}
