package com.side.util.beans;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.side.util.annotation.Deal;

public class DealInfo implements Serializable {

	private static final long serialVersionUID = 8920645354710506029L;
	
	/**
	 * Ĭ�Ϸ�������
	 */
	private static final String def_type = "page";

	// ������
	private Class<?> clazz;
	// ������
	private Method method;
	// ����������
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
	 * ��ȡ�����������ʵ��
	 * @return
	 * @throws Exception
	 */
	public Object getInstance() throws Exception {
		return clazz.newInstance();
	}
	
	/**
	 * ��ȡ������ķ�������
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
