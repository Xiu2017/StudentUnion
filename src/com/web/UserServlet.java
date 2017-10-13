package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.entity.StudentEntity;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserServlet() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��дservice����������opt�������ö�Ӧ�ķ���
		String opt = request.getParameter("opt");
		switch(opt){
			case "updatePwd"  : updatePwd(request,response); break;
			case "logout"     : logout(request,response); break;
		}
	}

	//�޸�����
	protected void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ȡ��ѧ�š�ԭ���롢������
		StudentEntity s = (StudentEntity) request.getSession().getAttribute("info");
		String sno = s.getSno();
		String pwd = request.getParameter("pwd");
		String npwd = request.getParameter("npwd");
		
		//ִ�������޸Ĳ���
		int rs = new UserDao().updatePwd(sno, pwd, npwd);
		
		
		//��������޸ĳɹ�������»Ự�е�����
		if(rs == 1){
			s.setSpwd(npwd);
			request.getSession().setAttribute("info",s);
		}
		
		
		//�����޸Ľ�����ͻ���
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.close();
	}

	//ע����¼
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���session����
		request.getSession().setAttribute("info",null);
		
		//�ض��򵽵�½����
		PrintWriter out = response.getWriter();
		out.print("<script>top.location.href='/StudentUnion'</script>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
