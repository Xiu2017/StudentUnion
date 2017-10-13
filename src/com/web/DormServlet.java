package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DormDao;
import com.entity.DormEntity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/DormServlet")
public class DormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DormServlet() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��дservice����������opt�������ö�Ӧ�ķ���
		String opt = request.getParameter("opt");
		switch(opt){
			case "getDormInfo" : getDormInfo(request,response);break;
			case "addDormInfo" : addDormInfo(request,response);break;
		}
	}

	//��ȡ���ҵǼ���Ϣ
	protected void getDormInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ҳ����
		String building = request.getParameter("building");  //����
		String floor = request.getParameter("floor");  //¥��
		String ajax   = request.getParameter("ajax");  //�ж��Ƿ�Ϊajax����
		String date = request.getParameter("date");
		
		//���û�ȡ���ҵǼ���Ϣ�ķ���
		List dorminfo = new DormDao().getDormInfo(building, floor, date);
		
		//������ҵǼ���Ϣ�����ڣ����ȡ���Ұ༶��Ϣ
		List dormclass = null;
		if(dorminfo.size() == 0){
			dormclass = new DormDao().getDormClass(building, floor);
		}
		
		//����˷���Ϊajax�������õģ����������ת��Ϊ�ַ���������Ӧajax��������������
		if("true".equals(ajax)){
			PrintWriter out = response.getWriter();
			if(dorminfo.size() == 0){
				out.print(new Gson().toJson(dormclass));
			}else{
				out.print(new Gson().toJson(dorminfo));
			}
			return;
		}
		
		//�������Ұ༶��Ϣ�����ҵǼ���Ϣ������¥�㣬��ת������ҳ
		request.setAttribute("dormclass", dormclass);
		request.setAttribute("dorminfo", dorminfo);
		request.setAttribute("building", building);
		request.getRequestDispatcher("iframes/liveDept/insertDormInfo.jsp").forward(request, response);
	}

	//������ҵǼ���Ϣ
	protected void addDormInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡjson�ַ���
		String jsonstr = request.getParameter("jsonstr");
		//��jsonת��Ϊ����
		JsonObject obj = new JsonParser().parse(jsonstr).getAsJsonObject();
		//��ȡ����¥�㡢������
		String building = obj.get("building").getAsString();
		int floor = obj.get("floor").getAsInt();
		String liable = obj.get("liable").getAsString();
		String date = obj.get("date").getAsString();
		//������ɱ��
		int finish = 0;
		
		//����json���󣬲��洢��list����
		List list = new ArrayList();
		for(int i = 1; i <= 15; i++){
			DormEntity d = new DormEntity();
			JsonArray j = obj.get("num"+i).getAsJsonArray();
			d.setBuilding(building);
			d.setNumber(""+(floor*100+i));
			d.setCid(j.get(0).getAsString());
			d.setCname(j.get(1).getAsString());
			d.setStandarda(j.get(2).getAsString());
			d.setStandardb(j.get(3).getAsString());
			d.setStandardc(j.get(4).getAsString());
			d.setStandardd(j.get(5).getAsString());
			d.setMark(j.get(6).getAsString());
			d.setLiable(liable);
			list.add(d);
		}
		
		//����List���飬��������ӻ��߸��·�����ȡ���������Ƿ���ڣ�
		if(new DormDao().getDormInfo(building,""+floor,date).size() == 0){
			for(int i = 0; i < 15; i++){
				if(new DormDao().addDormInfo((DormEntity)list.get(i),date)){
					finish++;
				}
			}
		}else{
			for(int i = 0; i < 15; i++){
				if(new DormDao().updateDormInfo((DormEntity)list.get(i),date)){
					finish++;
				}
			}
		}
		
		//����ǳɹ��Ĵ������ظ���ҳ
		PrintWriter out = response.getWriter();
		out.println(finish);
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
