package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Lecture;
import com.entity.PageBean;


public class ShowLect {
	/**
	 * ��Ѱ�������ʵķ���
	 * @return
	 */
	public PageBean Showtblist(PageBean bean){
		Connection conn = BaseDao.getConnection();//�������ݿ�
		PreparedStatement ps = null;//����Ԥ�������
		ResultSet rs = null;//�������������
		try {
			String sql="select * from(select  row_number() over(order by lid desc) rn,* from Lecture)r where r.rn>(("+bean.getCpage()+"-1)*"+bean.getShowNum()+") and r.rn<=("+bean.getCpage()*bean.getShowNum()+")";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();//ִ�����
			List list = new ArrayList(); 
			while(rs.next()){
				Lecture p = new Lecture();
				p.setLid(rs.getInt(2));
				p.setPname(rs.getString(3));
				p.setLname(rs.getString(4));
				p.setLdatatime(rs.getString(5));
				list.add(p);
			}
			bean.setPageList(list);
			sql="select count(*) from Lecture";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				//��������������װ��PageBean
				bean.setAllNum(rs.getInt(1));
			}
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		return null;
	}
	/**
	 * ɾ����Ϣ�ķ���
	 * @param property
	 * @return
	 */
	
	 public boolean delete(int id){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("delete Lecture where lid="+id);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, null);
		}
		return false;
	}
	/**
	 * �����Ʒ�ķ���
	 * @param property
	 * @return
	 */
	public boolean addLect(Lecture lecture){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into Lecture values(?,?,?)");
			ps.setString(1,lecture.getPname());
			ps.setString(2,lecture.getLname());
			ps.setString(3,lecture.getLdatatime());
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, null);
		}
		return false;
	} 
}
