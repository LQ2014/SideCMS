package com.side.util.beans;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.side.util.annotation.Deal;

public class DealInfo implements Serializable {

	private static final long serialVersionUID = 8920645354710506029L;
	
	/**
	 * 默认返回类型
	 */
	private static final String def_type = "page";

	// 处理类
	private Class<?> clazz;
	// 处理方法
	private Method method;
	// 处理方法定义
	private Deal deal;

	public DealInfo(Class<?> clazz, Method method, Deal deal) {
		this.clazz = clazz;
		this.method = method;
		this.deal = deal;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}
	
	/**
	 * 获取请求处理类的新实例
	 * @return
	 * @throws Exception
	 */
	public Object getInstance() throws Exception {
		return clazz.newInstance();
	}
	
	/**
	 * 获取请求处理的返回类型
	 * @return json or page
	 */
	public String getType() {
		if (null == deal.type() || deal.type().trim().length() == 0) {
			return def_type;
		}
		return deal.type().trim().toLowerCase();
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(deal.path() + "[" + deal.type() + "]" + " -> ");
		buf.append(method);
		return buf.toString();
	}
}
