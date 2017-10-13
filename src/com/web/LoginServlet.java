package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LoginDao;
import com.entity.StudentEntity;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/StudentUnion");
	}
	//�û���¼
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���봦��
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ�˺�����
		String sno = request.getParameter("sno");
		String spwd = request.getParameter("spwd");
		
		//��ѯ�˺�����
		StudentEntity s = new LoginDao().doLogin(sno,spwd);
		
		//��������ڣ��򷵻ؿ�ֵ����¼ҳ�棬��ʾ�û��˺Ż��������
		if(s == null){
			PrintWriter out = response.getWriter();
			out.print("");
			out.close();
		//������ڣ��򱣴��û���Ϣ�����ض��򵽶�Ӧ���ŵ���ҳ
		}else{
			request.getSession().setAttribute("info", s);
			request.setAttribute("info", s);
			String url = null;
			switch(s.getSdid()){
				case "0" : url = "dept/admin.jsp";break;			//����Ա
				case "1" : url = "dept/student.jsp";break;   //ѧԱ
				case "2" : url = "dept/liveDept.jsp";break;  //���
				case "3" : url = "dept/inspectionDept.jsp";break;	//�ͼ첿
				case "4" : url = "dept/SECDept.jsp";break;   //���鲿
				case "5" : url = "dept/learnDept.jsp";break; //ѧϰ��
				case "6" : url = "dept/publicityDept.jsp";break;    //������
				case "7" : url = "dept/styleDept.jsp";break; //���岿
				case "8" : url = "dept/broadDept.jsp";break; //������
				case "9" : url = "dept/clubDept.jsp";break;  //���Ų�
				case "10" : url = "dept/ANliDept.jsp";break; //������
				
				default: url = "404.jsp";break;
			}
			response.sendRedirect(url);
		}
	}

}
