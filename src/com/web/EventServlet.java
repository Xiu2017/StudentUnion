package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Showevent;
import com.entity.PageBean;
import com.entity.event;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opt = request.getParameter("opt");
		if("Showevent".equals(opt)){
			doShowevent(request,response);
		}else if("addevent".equals(opt)){
			doaddevent(request,response);
		}else if("delete".equals(opt)){
			delete(request,response);
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*�����ݿ��ȡ�����б�*/
	protected void doShowevent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cpage=Integer.parseInt(request.getParameter("cpage"));
		//����һ����ҳ��PageBean
		PageBean bean=new PageBean();
		bean.setCpage(cpage);//���õ�ǰ�ڼ�ҳ
		
		//���ú�̨�������з�ҳ��ѯ����
		bean=new Showevent().Showtblist(bean);
		request.setAttribute("pageBean", bean);
		request.getRequestDispatcher("iframes/ANliDept/Event.jsp").forward(request, response);
	}
	/*�����ݿ�ɾ����ӦID������*/
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		int param=Integer.parseInt(request.getParameter("param"));
	
		if(new Showevent().delete(param)){
			out.print(1);
		}else{
			out.print(0);
		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*�����Ʒ��Ϣ�����ݿ���*/
	protected void doaddevent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		//��ȡ��������
		String Sevent = request.getParameter("Sevent");
		String Ethings =request.getParameter("Ethings");
		String Ename=request.getParameter("Ename");
		String Datatime = request.getParameter("Datatime");
		//������ֵ��װ��Propertyʵ�������
		event pro = new event(0, Sevent, Ethings, Ename ,Datatime);
		if(new Showevent().addevent(pro)){
			out.print(1);
		}else{
			out.print(0);
		}
	}

}
