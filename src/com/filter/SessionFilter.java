package com.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.entity.StudentEntity;

@WebFilter("/SessionFilter")
public class SessionFilter implements Filter {
	private String encoding;
    public SessionFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//�������
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset="+encoding);
		
		//�Ự״̬����
		HttpServletRequest hreq = (HttpServletRequest) request;    
		StudentEntity s = (StudentEntity) hreq.getSession().getAttribute("info");
		if(s != null){
			hreq.getSession().setAttribute("sname", s);
		}else{
			PrintWriter out = response.getWriter();
			out.print("<h1>��ҳ�ѹ���</h1><a href='javascript:top.location.href=\"/StudentUnion\"'>���µ�¼</a>");
			out.close();
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}

}
