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

import com.dao.EssayDao;
import com.entity.Essay;
import com.entity.PageBean;
import com.google.gson.Gson;

/**
 * ͬѧ���������
 */
@WebServlet("/essay")
public class EssayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EssayServlet() {
        super();
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
//    	��дservice����
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	String opt=request.getParameter("opt");
    	if("showData".equals(opt)){
    		doShowData(request, response);
    	}else if("addData".equals(opt)){
    		doAddData(request, response);
    	}else if("getData".equals(opt)){
    		doGetData(request, response);
    	}else if("removeData".equals(opt)){
    		doRemoveData(request, response);
    	}    	
    	else{
    		response.sendRedirect("error.html");
    	}
    }
    /*��ѯ��������*/
	protected void doShowData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ǰҳ��
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		//ģ����ѯ
		String res=request.getParameter("selectData");
		PageBean page = new PageBean();
		page.setShowNum(8);//ÿҳ��ʾ����������
		page.setCpage(cpage);// ���õ�ǰ�ڼ�ҳ
		// 3:���ú�̨�������з�ҳ��ѯ����
		page = new EssayDao().selectData(page,res);
		// :�����ݱ����ڻỰ��
		request.setAttribute("pageBean", page);
		request.setAttribute("selectData", res);
		request.getRequestDispatcher("iframes/broadDept/showEssay.jsp").forward(request,response);
	}
	/* �������*/
	protected void doAddData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����û�����
		String etitle = request.getParameter("etitle");
		String econtent = request.getParameter("econtent");

		// ������ǰʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ

		// ��װ���ݵ�ʵ������
		Essay e = new Essay(0, "XXX",etitle,econtent,df.format(new Date()));

		// ����Dao����ִ����Ӳ���
		PrintWriter out = response.getWriter();
		if (new EssayDao().addData(e)) {
			out.println(1);
		} else {
			out.println(0);
		}
		out.close();
	}
  /*��ѯָ��id����*/
	protected void doGetData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ҫ��ѯ��id
		int id=Integer.parseInt(request.getParameter("id"));
		Essay e=new EssayDao().getData(id);
		// �����ݻ�Ӧ����ҳ
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(e));
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
		if (new EssayDao().removeData(id)) {
			out.println(1);
		} else {
			out.println(0);
		}
		out.close();
	}

}
