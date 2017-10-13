package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BroadNoticeDao;
import com.dao.NoticeDao;
import com.entity.Notice;
import com.entity.PageBean;
import com.google.gson.Gson;

/**
 * Ѱ�����º�ʧ���ѯ
 */
@WebServlet("/notice")
public class BroadNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BroadNoticeServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/* ��дservice�����û��Զ��巢������ */
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String opt = request.getParameter("opt");
		if ("selectData".equals(opt)) {
			doSelectData(request, response);
		} else if ("addData".equals(opt)) {
			doAddData(request, response);
		} else if ("removeData".equals(opt)) {
			doRemoveData(request, response);
		} else if ("getData".equals(opt)) {
			doGetData(request, response);
		} else if ("upData".equals(opt)) {
			doUpDate(request, response);
		} else {
			response.sendRedirect("error.html");
		}
	}
	/**
	 * ģ����ѯ
	 */
	protected void doSelectData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ǰҳ��
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		//��ǰ����
		int ctype = Integer.parseInt(request.getParameter("ctype"));
		//ģ����ѯ
		String res=request.getParameter("selectData");
		
		PageBean page = new PageBean();
		page.setShowNum(3);//ÿҳ��ʾ����������
		page.setCpage(cpage);// ���õ�ǰ�ڼ�ҳ
		// 3:���ú�̨�������з�ҳ��ѯ����
		page = new BroadNoticeDao().selectData(page,res,ctype);
		// :�����ݱ����ڻỰ��
		request.setAttribute("pageBean", page);
		request.setAttribute("selectData", res);
		request.setAttribute("ctype", ctype);
		request.getRequestDispatcher("iframes/broadDept/showNotice.jsp").forward(request,response);
	}

	/**
	 * ����µ�����
	 */
	protected void doAddData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �����û�����
		String ncontent = request.getParameter("ncontent");
		//���յ�ǰ�������
		int ctype = Integer.parseInt(request.getParameter("ctype"));
		// ������ǰʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ

		// ��װ���ݵ�ʵ������
		Notice n = new Notice(0, "XXX", ncontent,df.format(new Date()),ctype);

		// ����Dao����ִ����Ӳ���
		PrintWriter out = response.getWriter();
		if (new BroadNoticeDao().addData(n)) {
			out.println(1);
		} else {
			out.println(0);
		}
		out.close();
	}

	/**
	 * ɾ���Ĺ���
	 * */
	protected void doRemoveData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �����û�����
		int id = Integer.parseInt(request.getParameter("id"));
		// ����Dao����ִ����Ӳ���
		PrintWriter out = response.getWriter();
		if (new BroadNoticeDao().removeData(id)) {
			out.println(1);
		} else {
			out.println(0);
		}
		out.close();
	}

	/**
	 * ��ѯָ��id
	 */
	protected void doGetData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �����û�����
		int id = Integer.parseInt(request.getParameter("id"));

		// ȥ��dao���Ҷ�Ӧ���û�����
		Notice n = new BroadNoticeDao().getUserById(id);
		// �����ݻ�Ӧ����ҳ
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(n));
		out.close();

	}

	/**
	 * �޸�ָ��id��ֵ
	 */
	protected void doUpDate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �����û�����
		int id = Integer.parseInt(request.getParameter("id"));
		String ncontent = request.getParameter("ncontent");
		int ctype = Integer.parseInt(request.getParameter("ctype"));
		
		// ��װ���ݵ�ʵ����
		Notice n = new Notice(id, null, ncontent, null,ctype);

		// ����Dao����ִ����Ӳ���
		PrintWriter out = response.getWriter();
		if (new BroadNoticeDao().upData(n)) {
			out.println(1);
		} else {
			out.println(0);
		}
		out.close();
	}
	
}
