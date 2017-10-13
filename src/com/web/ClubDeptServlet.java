package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ClubDeptDao;
import com.entity.PageBean;
import com.google.gson.Gson;

@WebServlet("/ClubDeptServlet")
public class ClubDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ClubDeptServlet() {
        super();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String opt=request.getParameter("opt");
    	switch(opt){
    	case "clubselect"://����ǵǼ�ҳ��Ĳ�ѯ����
    		doSelectClub(request, response);break;
    	case "shetuandengji"://�Ǽǵķ���
    		doinsertclub(request, response);break;
    	case "clubtoselect"://��ѯҳ��Ĳ�ѯ����
    		doSelect(request, response);break;
        case "toremove"://ɾ����¼�ķ���
        	doRemove(request, response);break;
        case "clubname_to_select"://ͨ����������ѯ�ķ���
        	doname_select(request, response);break;
    	}
    }
    
	protected void doSelectClub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//�����ݿ���ȡ�������������
		List list=new ClubDeptDao().getClubInfo();
		PrintWriter out=response.getWriter();
		if(list == null){
			out.print(0);
		}else{
		//������תΪjson������ʽ����ҳ��
			out.print(new Gson().toJson(list));
		}
	}
	
	protected void doinsertclub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��������洢�������Ĳ���
		//��ǰ�жϵǼǵĵڼ���
		int thistr=Integer.parseInt(request.getParameter("thistr"));
		PrintWriter out=response.getWriter();
		//�Ǽǵ�����id
		int clubid=Integer.parseInt(request.getParameter("clubid"));
		//�Ǽǵ�������
		int peoplenum=Integer.parseInt(request.getParameter("peoplenum"));
		//�Ǽǵ�ʵ������
		int peoplecomenum=Integer.parseInt(request.getParameter("peoplecomenum"));
		//�Ǽǵ�ȱ������
		int peoplenocome=0;
		try{
		peoplenocome=Integer.parseInt(request.getParameter("peoplenocome"));
		}catch(Exception ex){
			
		}
		
		//�жϣ�����������������Χ�ڲ��������
		if(peoplecomenum <= peoplenum && peoplecomenum >= 0 && (peoplenum-peoplecomenum) == peoplenocome && peoplenocome >= 0 && peoplenum != 0){
			//��ȡ��ǰʱ��
			Date thetime = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datetime = sdf.format(thetime);
			
			if(new ClubDeptDao().insertClub_dengji(peoplenum, peoplecomenum,peoplenocome, datetime, clubid)){
				out.print("{\"success\":true,\"row\":\""+thistr+"\"}");
			}
		}else{//��������������ݲ��淶��ֱ�Ӹ�����Ӧ
			out.print("{\"success\":false,\"row\":\""+thistr+"\"}");
		}
		
	}

	protected void doSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ȡ��Ҫ��ѯ�ڼ�ҳ������
			int cpage=Integer.parseInt(request.getParameter("cpage"));
		//����һ����ҳ��PageBean
			PageBean bean=new PageBean();
			bean.setCpage(cpage);//���õ�ǰ�ڼ�ҳ
			bean=new ClubDeptDao().getsomeClub(bean);
		//��bean���浽������
			request.setAttribute("bean", bean);
		//ת��ҳ��
			request.getRequestDispatcher("iframes/clubDept/���Ų�ѯ.jsp").forward(request, response);
			
	}
		//��ѯҳɾ����¼�Ĵ���
	protected void doRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
		//ȡ��Ҫɾ������
			int attid=Integer.parseInt(request.getParameter("attid"));
		//���÷���ɾ����¼
			if(new ClubDeptDao().removeRow(attid)){
				out.print(1);
			}else{
				out.print(0);
			}		
	}
	
	//���ݴ��������������ֽ���ģ����ѯ
	protected void doname_select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
	//ȡ��Ҫ��ѯ��������
		String clubname=request.getParameter("clubname");
		int cpage=Integer.parseInt(request.getParameter("cpage"));
		PageBean bean=new PageBean();
		bean.setCpage(cpage);//���õ�ǰ�ڼ�ҳ
		bean=new ClubDeptDao().getClubtoname(bean,clubname);
	//��bean���浽������
		request.setAttribute("clubname", clubname);
		request.setAttribute("bean", bean);
		//ת��ҳ��
		request.getRequestDispatcher("iframes/clubDept/����ģ����ѯ.jsp").forward(request, response);
		
}
}
