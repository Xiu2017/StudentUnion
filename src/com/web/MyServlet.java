package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StuDao;
import com.entity.AdmCla;
import com.entity.AdmStuInfo;
import com.entity.AssistantInfo;
import com.entity.BookInfo;
import com.entity.BookStateInfo;
import com.google.gson.Gson;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyServlet() {
        super();
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String opt = request.getParameter("opt");
    	switch (opt) {
		case "selBook":
			doSelBook(request, response);  //��ѯ�鼮
			break;
		case "loanBook":
			loanBook(request, response);  //����
			break;
		case "returnBook":
			returnBook(request, response);  //����
			break;
		case "viewLog":
			viewLog(request, response);  //�軹��¼
			break;
		case "addBook":
			addBook(request, response);  //����鼮
			break;
		case "selAss":
			selAss(request, response);  //��ѯ����
			break;
		case "insAss":
			insAss(request, response);  //�������
			break;
		case "selcla":
			selcla(request, response);  //��ѯ�༶
			break;
		case "delAss":
			delAss(request, response);  //ɾ������
			break;
		case "updAss":
			updAss(request, response);  //�޸�����
			break;
		case "admClaSel":
			admClaSel(request, response);
			break;
		case "admClaIns":
			admClaIns(request, response);
			break;
		case "admClaUpd":
			admClaUpd(request, response);
			break;
		case "admStu":
			admStu(request, response);
			break;
		case "admStuCla":
			admStuCla(request, response);
			break;
		case "admStuDep":
			admStuDep(request, response);
			break;
		case "admStuAdd":
			admStuAdd(request, response);
			break;
		case "admStuUpd":
			admStuUpd(request, response);
			break;
			
		default:
			break;
		}
    }

	/**
	 * ��ѯ�鼮
	 */
	protected void doSelBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String page = request.getParameter("page");
		List<BookInfo> list = new StuDao().selBook(name, page);
		PrintWriter out = response.getWriter();
		if(list.size() > 0 && list != null){
			out.println(new Gson().toJson(list));
		} else{
			out.println(0);
		}
		out.close();
	}
	
	/**
	 * ����
	 */
	protected void loanBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookno = request.getParameter("no");
		String stuno = request.getParameter("sname");
		String outtime = request.getParameter("outtime");
		PrintWriter out = response.getWriter();
		if(new StuDao().loanBook(new BookStateInfo(bookno, null, stuno, null, outtime, null, null,null))){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}
	
	/*
	 * ����
	 */
	protected void returnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ret = request.getParameter("ret");
		PrintWriter out = response.getWriter();
		if(new StuDao().returned(ret)){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

	//�鿴��־
	protected void viewLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String log = request.getParameter("log");
		String lpage = request.getParameter("lpage");
		List<BookStateInfo> list = new StuDao().viewLog(log,lpage);
		PrintWriter out = response.getWriter();
		if(list != null && list.size() > 0){
			out.println(new Gson().toJson(list));
		} else{
			out.println(0);
		}
		out.close();
	}

	//����鼮
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bno = request.getParameter("bno");
		String bname = request.getParameter("bname");
		String bauthor = request.getParameter("bauthor");
		PrintWriter out = response.getWriter();
		if(new StuDao().addBook(new BookInfo(bno, bname, bauthor, null, null, null))){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

	//��ѯ������Ϣ
	protected void selAss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selAss = request.getParameter("val");
		String page = request.getParameter("page");
		List<AssistantInfo> list = new StuDao().selAss(selAss, page);
		PrintWriter out = response.getWriter();
		if(list != null && list.size() > 0){
			out.println(new Gson().toJson(list));
		} else{
			out.println(0);
		}
		out.close();
	}

	//����������Ϣ
	protected void insAss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String cla = request.getParameter("class");
		PrintWriter out = response.getWriter();
		if(new StuDao().insAss(new AssistantInfo(null, no, null, null,cla, null))){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}
	
	//��ѯ��ѡ�༶,����������Ϣʱ
	protected void selcla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(new StuDao().selCla()));
		out.close();
	}

	//ɾ��������Ϣ
	protected void delAss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("aid");
		PrintWriter out = response.getWriter();
		if(new StuDao().delAss(aid)){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

	//�޸�������Ϣ
	protected void updAss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("aid");
		String asno = request.getParameter("asno");
		String aclass = request.getParameter("aclass");
		PrintWriter out = response.getWriter();
		if(new StuDao().updAss(new AssistantInfo(aid, asno, null, null, aclass, null))){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

	//����Ա����༶  ��ѯ
	protected void admClaSel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		List<AdmCla> list = new StuDao().admClaSel(page);
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(list));
		out.close();
	}

	//����Ա����༶  ����
	protected void admClaIns(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname = request.getParameter("name");
		PrintWriter out = response.getWriter();
		if(new StuDao().admClaIns(cname)){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

	//����Ա����༶  �޸�
	protected void admClaUpd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("name");
		PrintWriter out = response.getWriter();
		if(new StuDao().admClaUpd(new AdmCla(cid, cname, null, null))){
			out.println(1);
		} else{
			out.println(0);	
		}
		out.close();
	}

	//����Ա����ѧԱ��ȡ�༶
	protected void admStuCla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(new StuDao().admStuCla()));
		out.close();
	}

	//����Ա����ѧԱ��ȡ����
	protected void admStuDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(new StuDao().admStuDep()));
		out.close();
	}

	//����Ա����ѧԱ��ѯѧ����Ϣ
	protected void admStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sname = request.getParameter("name");
		String sclass = request.getParameter("class");
		String sdepartment = request.getParameter("department");
		String spage = request.getParameter("page");
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(new StuDao().admStu(new AdmStuInfo(null, sname, null, sclass, sdepartment, spage))));
		out.close();
	}

	//����Ա����ѧԱ       ���ѧ��
	protected void admStuAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");
		String spwd = request.getParameter("spwd");
		String scname = request.getParameter("scname");
		String sdname = request.getParameter("sdname");
		PrintWriter out = response.getWriter();
		if(new StuDao().admStuAdd(new AdmStuInfo(sno, sname, spwd, scname, sdname, null))){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

	//����Ա����ѧԱ       �޸�ѧ��
	protected void admStuUpd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");
		String spwd = request.getParameter("spwd");
		String scname = request.getParameter("scname");
		String sdname = request.getParameter("sdname");
		PrintWriter out = response.getWriter();
		if(new StuDao().admStuUpd(new AdmStuInfo(sno, sname, spwd, scname, sdname, null))){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

}
