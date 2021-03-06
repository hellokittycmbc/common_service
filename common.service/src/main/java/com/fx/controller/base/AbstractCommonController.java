/**
 * 
 */
package com.fx.controller.base;

import com.fx.util.HttpServletUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


/**
 *
 */
public abstract class AbstractCommonController
{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected final static int DEFAULT_PAGE_SIZE = 20;


	final static String FREEMARKER_BASE = "/WEB-INF/ftl/";
	protected final static File TPL_BASE = new File(ContextLoader.getCurrentWebApplicationContext()
			.getServletContext().getRealPath(FREEMARKER_BASE));
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	protected HttpServletRequest request;

	/**
	 * 获取模板文件
	 * @param tpl viewName
	 * @return
	 */
	protected File getTemplateFile(String tpl)
	{
		return new File(TPL_BASE, tpl + ".ftl");
	}
	
	/**
	 * 客户端IP
	 * @return
	 */
	protected String getRemoteAddr()
	{
		return HttpServletUtil.getRemoteAddr(request);
	}
	
	/**
	 * 从request获取分页大小
	 * @return getPageSize("pageSize")
	 */
	protected int getPageSize()
	{
		return getPageSize("pageSize");
	}

	protected int getPageSize(int default_page_size)
	{
		return getPageSize("pageSize", default_page_size);
	}
	
	protected int getPageSize(String p)
	{
		return getPageSize(p, DEFAULT_PAGE_SIZE);
	}

	protected int getPageSize(String p, int default_page_size)
	{
		int i = getInt(p, default_page_size);
		if (i > 0)
		{
			return i;
		}
		return default_page_size;
	}

	/**
	 * 从request获取当前页数
	 * @return getPageNo("page")
	 */
	protected int getPageNo()
	{
		return getPageNo("page");
	}
	
	protected int getPageNo(String p)
	{
		int i = getInt(p, 1);
		if (i > 0)
		{
			return i;
		}
		return 1;
	}
	
	protected int getInt(String p, int defaultValue)
	{
		if (StringUtils.isEmpty(p))
		{
			return defaultValue;
		}
		String s = request.getParameter(p);
		if (StringUtils.isEmpty(s))
		{
			return defaultValue;
		}
		try
		{
			return Integer.parseInt(s);
		}
		catch (Exception e)
		{
		}
		return defaultValue;
	}

	
	protected String sendRedirect(String url, HttpServletResponse response)
	{
		try
		{
			response.sendRedirect(url);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	


}
