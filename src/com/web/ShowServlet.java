package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ShowDao;
import com.entity.PageBean;
import com.entity.Property;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opt = request.getParameter("opt");
		if("Showtb".equals(opt)){
			doShowtb(request,response);
		}else if("addGoods".equals(opt)){
			doaddGoods(request,response);
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*�����ݿ��ȡ�����б�*/
	protected void doShowtb(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cpage=Integer.parseInt(request.getParameter("cpage"));
		//����һ����ҳ��PageBean
		PageBean bean=new PageBean();
		bean.setCpage(cpage);//���õ�ǰ�ڼ�ҳ
		
		//���ú�̨�������з�ҳ��ѯ����
		bean=new ShowDao().Showtblist(bean);
		request.setAttribute("pageBean", bean);
		request.getRequestDispatcher("iframes/SECDept/Showtb.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*�����Ʒ��Ϣ�����ݿ���*/
	protected void doaddGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		//��ȡ��������
		String Goods = request.getParameter("Goods");
		int Num = Integer.parseInt(request.getParameter("Num"));
		float Price = Float.parseFloat(request.getParameter("Price"));
		float TPrice = Float.parseFloat(request.getParameter("TPrice"));
		String BuyTime = request.getParameter("BuyTime");
		//������ֵ��װ��Propertyʵ�������
		Property pro = new Property(0, Goods, Num, Price, TPrice, BuyTime);
		if(new ShowDao().addGoods(pro)){
			out.print(1);
		}else{
			out.print(0);
		}
	}

}
