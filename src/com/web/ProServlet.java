package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ShowPro;
import com.entity.PageBean;
import com.entity.Propaganda;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/ProServlet")
public class ProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opt = request.getParameter("opt");
		if("Showpro".equals(opt)){
			doShowpro(request,response);
		}else if("addpro".equals(opt)){
			doaddpro(request,response);
		}else if("delete".equals(opt)){
			delete(request,response);
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*�����ݿ��ȡ�����б�*/
	protected void doShowpro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cpage=Integer.parseInt(request.getParameter("cpage"));
		//����һ����ҳ��PageBean
		PageBean bean=new PageBean();
		bean.setCpage(cpage);//���õ�ǰ�ڼ�ҳ
		
		//���ú�̨�������з�ҳ��ѯ����
		bean=new ShowPro().Showtblist(bean);
		request.setAttribute("pageBean", bean);
		request.getRequestDispatcher("iframes/publicityDept/Propaganda.jsp").forward(request, response);
	}
	/*�����ݿ�ɾ����ӦID������*/
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		int param=Integer.parseInt(request.getParameter("param"));
	
		if(new ShowPro().delete(param)){
			out.print(1);
		}else{
			out.print(0);
		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*�����Ʒ��Ϣ�����ݿ���*/
	protected void doaddpro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		//��ȡ��������
		String Pname = request.getParameter("Pname");
		String Content=request.getParameter("Content");
		String Pdatatime = request.getParameter("Pdatatime");
		//������ֵ��װ��Propertyʵ�������
		Propaganda pro = new Propaganda(0, Pname, Content ,Pdatatime);
		if(new ShowPro().addpro(pro)){
			out.print(1);
		}else{
			out.print(0);
		}
	}

}
