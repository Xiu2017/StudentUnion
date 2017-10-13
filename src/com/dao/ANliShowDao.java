package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.PageBean;
import com.entity.sponsor;


public class ANliShowDao {
	/**
	 * ��Ѱ�������ʵķ���
	 * @return
	 */
	public PageBean Showtblist(PageBean bean){
		Connection conn = BaseDao.getConnection();//�������ݿ�
		PreparedStatement ps = null;//����Ԥ�������
		ResultSet rs = null;//�������������
		try {
			String sql="select * from(select  row_number() over(order by sid desc) rn,* from sponsor)r where r.rn>(("+bean.getCpage()+"-1)*"+bean.getShowNum()+") and r.rn<=("+bean.getCpage()*bean.getShowNum()+")";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();//ִ�����
			List list = new ArrayList(); 
			while(rs.next()){
				sponsor p = new sponsor();
				p.setSid(rs.getInt(2));
				p.setSevent(rs.getString(3));
				p.setThings(rs.getString(4));
				p.setDatatime(rs.getString(5));
				p.setSname(rs.getString(6));
				p.setResult(rs.getString(7));
				list.add(p);
			}
			bean.setPageList(list);
			sql="select count(*) from sponsor";
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
			ps = conn.prepareStatement("delete sponsor where sid="+id);
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
	 * �����Ϣ�ķ���
	 * @param property
	 * @return
	 */
	public boolean addGoods(sponsor sponsor){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into sponsor values(?,?,?,?,?)");
			ps.setString(1,sponsor.getSevent());
			ps.setString(2,sponsor.getThings());
			ps.setString(3,sponsor.getDatatime());
			ps.setString(4,sponsor.getSname());
			ps.setString(5,sponsor.getResult());
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
