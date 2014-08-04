package com.side.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.side.consts.AnnotationConst;
import com.side.consts.Config;
import com.side.servlet.ServeletContext;
import com.side.util.beans.DealInfo;

public class SideFilter implements Filter {
	
	// 日志
	private static final Logger log = Logger.getLogger(SideFilter.class);

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		ServeletContext.setRequest(req, res);
		String path = ((HttpServletRequest)req).getServletPath();
		DealInfo dealInfo = Config.getDealInfo(path);
		
		// Debug 日志
		log.debug("Request path: " + path);
		log.debug("Deal info: " + dealInfo);
		
		// 映射配置存在，调用配置的处理方法
		if (null != dealInfo) {
			try {
				Object retObj = dealInfo.getMethod().invoke(dealInfo.getInstance());
				if (AnnotationConst.TYPE_JSON.equals(dealInfo.getType())) {
					write(res, (String)retObj);
				} else if (AnnotationConst.TYPE_PAGE.equals(dealInfo.getType())) {
					req.getRequestDispatcher(dealInfo.getDeal().forward()).forward(req, res);
				}
			} catch (Exception e) {
				log.error("Dispatch request error, deal info：" + dealInfo, e);
			}
		} else {
			// 映射配置不存在，继续进行处理
			chain.doFilter(req, res);
		}
	}
	
	/**
	 * 向前台写返回字符串
	 * @param res 响应
	 * @param info 返回的字符串
	 * @throws IOException
	 */
	private void write(ServletResponse res, String info) throws IOException {
		PrintWriter pw = null;
		try {
			pw = res.getWriter();
			pw.write(info);
			log.debug("Deal request successful, the value to return back is：" + info);
		} catch (Exception e) {
			throw new IOException("Deal request successful, but a error occured when write the data back. The data is: " + info, e);
		} finally {
			if (null != pw) {
				pw.flush();
				pw.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig cfg) throws ServletException {
		// 加载配置信息
		PropertyConfigurator.configure(Config.getLog4jfilename());
	}

}
