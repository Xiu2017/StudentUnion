package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CheckDao;
import com.entity.Checkdata;
import com.google.gson.Gson;
import com.service.DeptService;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //��дservice
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opt = request.getParameter("opt");
		if("selectName".equals(opt)){
			doselectName(request,response);
		}else if("adddata".equals(opt)){
			doadddata(request,response);
		}else if("CheckServlet".equals(opt)){
			doCheckServlet(request,response);
		}else if("CheckServletTwo".equals(opt)){
			doCheckServletTwo(request,response);
		}else if("deletedata".equals(opt)){
			dodeletedata(request,response);
		}else if("lastdate".equals(opt)){
			dolastdate(request,response);
		}
	}

    /**
     * //�����ݿ��в�ѯ���в��ŵ�servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	protected void doselectName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		List list = new com.service.DeptService().getDeptlist();
		out.print(new Gson().toJson(list));
	}


	/**
	 * �����Ʒ��Ϣ��servlet
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doadddata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��ȡ����ֵ
		PrintWriter out = response.getWriter();
		String dname = request.getParameter("dname");
		int allnum = Integer.parseInt(request.getParameter("allnum"));
		int num = Integer.parseInt(request.getParameter("num"));
		int lacknum = Integer.parseInt(request.getParameter("lacknum"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date nowTime = new java.util.Date();//��ȡ��ǰʱ��
		String ntime = formatter.format(nowTime);
		//���������ݷ�װ��Checkdataʵ������
		Checkdata cd = new Checkdata(0, dname, allnum, num, lacknum, ntime);
		//System.out.println(cd.getDname());
		//�����󱣴浽�Ự��
		if(new DeptService().getaddCheckdata(cd)){
			out.print("1");
		}else{
			out.print("0");
		}
	}
	
	/**
	 * ҳ���ʼ����ѯ���ڵ�servlet
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doCheckServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		//�����ݿ��в�ѯ���в���
		//��ȡ��ǰʱ��
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date nowTime = new java.util.Date();//��ȡ��ǰʱ��
		String ntime = formatter.format(nowTime);
		List list = new com.service.DeptService().getCheckservlet(ntime);
		out.print(new Gson().toJson(list));
	}
	
	/**
	 *  ��ʱ�������ѯ���ڵ�servlet
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doCheckServletTwo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		//�����ݿ��в�ѯ���в���
		String ntime = request.getParameter("param");
		List list = new com.service.DeptService().getCheckservlet(ntime);
		out.print(new Gson().toJson(list));
	}
	/**
	 * ɾ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void dodeletedata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		//��ȡ����
		int param = Integer.parseInt(request.getParameter("param"));
		//����Dao�㷽��
		if(new CheckDao().dodeletedata(param)){
			out.print(1);
		}else{
			out.print(0);
		}
	}

	protected void dolastdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String ntime = request.getParameter("mydate");
		List lastdata = new com.service.DeptService().getCheckservlet(ntime);
		out.print(new Gson().toJson(lastdata));
	}
}
