package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.NoticeDao;
import com.entity.NoticeEntity;
import com.entity.StudentEntity;

@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   public NoticeServlet() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����opt�����ж���Ҫ���õķ���
		String opt = request.getParameter("opt");
		switch(opt){
			case "getNotice"  : getNotice(request,response); break;
			case "addNotice"  : addNotice(request,response); break;
			case "setNotice"  : setNotice(request,response); break;
			case "editNotice" : editNotice(request,response); break;
			case "delNotice" : delNotice(request,response); break;
		}
	}

	protected void getNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ȡ����ǰ�û���ɫ����ǰҳ����
		StudentEntity s = (StudentEntity) request.getSession().getAttribute("info");
		String role = s.getSdid();  //���ű��
		int page = Integer.parseInt(request.getParameter("page"));  //��ǰҳ��
		
		//���ݽ�ɫ�͵�ǰҳ��ȡ����Ӧҳ���Ĺ���
		List list = new NoticeDao().getNotice(role,page,null);
		
		//ȡ��������ҳ��
		int allpage = new NoticeDao().getCount(role,null);
		
		//����ȡ���Ĺ��桢��ҳ������ǰҳ����ת�򵽹���ҳ��
		request.setAttribute("notice", list);
		request.setAttribute("allpage", allpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("iframes/liveDept/notice.jsp").forward(request, response);
	}

	protected void addNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��session�л�ȡ�û���Ϣ
		StudentEntity s = (StudentEntity) request.getSession().getAttribute("info");
		String role = s.getSdid();  //���ű��
		String name = s.getSname();  //����
		
		String text = request.getParameter("text");  //��������
		String title = request.getParameter("title");  //�������
		String region = request.getParameter("region"); //��������
		
		//���浽ʵ������
		NoticeEntity n = new NoticeEntity(null,name,null,null,title,text,role,null,region);
		PrintWriter out = response.getWriter();
		
		//�ж��Ƿ񷢲��ɹ�
		if(new NoticeDao().addNotice(n)){
			out.print("finish");
		}else{
			out.print("");
		}
		out.close();
	}

	//��ȡ��ǰ�û������Ĺ���
	protected void setNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��session�л�ȡ�û���Ϣ
		StudentEntity s = (StudentEntity) request.getSession().getAttribute("info");
		String role = s.getSdid();  //���ű��
		String npublisher = s.getSname();  //������
		
		int page = Integer.parseInt(request.getParameter("page"));  //��ǰҳ��

		//���ݽ�ɫ�͵�ǰҳ��ȡ����Ӧҳ���Ĺ���
		List list = new NoticeDao().getNotice(role,page,npublisher);
		
		//ȡ��������ҳ��
		int allpage = new NoticeDao().getCount(role,npublisher);
		
		//����ȡ���Ĺ��桢��ҳ������ǰҳ����ת�򵽹���ҳ��
		request.setAttribute("notice", list);
		request.setAttribute("allpage", allpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("iframes/liveDept/updateN.jsp").forward(request, response);
	}

	//�༭����
	protected void editNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");  //��������
		String title = request.getParameter("title");  //�������
		String nid = request.getParameter("nid");  //����id
		
		//�ж��Ƿ���³ɹ�
		PrintWriter out = response.getWriter();
		if(new NoticeDao().editNotice(text, title, nid)){
			out.print("finish");
		}else{
			out.print("");
		}
		out.close();
	}

	//ɾ������
	protected void delNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nid = request.getParameter("nid");  //����id
		
		//�ж��Ƿ�ɾ���ɹ�
		PrintWriter out = response.getWriter();
		if(new NoticeDao().delNotice(nid)){
			out.print("finish");
		}else{
			out.print("");
		}
		out.close();
	}

	protected void bak(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
