package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.PageBean;
import com.entity.Propaganda;


public class ShowPro {
	/**
	 * ��Ѱ������Ϣ�ķ���
	 * @return
	 */
	public PageBean Showtblist(PageBean bean){
		Connection conn = BaseDao.getConnection();//�������ݿ�
		PreparedStatement ps = null;//����Ԥ�������
		ResultSet rs = null;//�������������
		try {
			String sql="select * from(select  row_number() over(order by pid desc) rn,* from Propaganda)r where r.rn>(("+bean.getCpage()+"-1)*"+bean.getShowNum()+") and r.rn<=("+bean.getCpage()*bean.getShowNum()+")";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();//ִ�����
			List list = new ArrayList(); 
			while(rs.next()){
				Propaganda p = new Propaganda();
				p.setPid(rs.getInt(2));
				p.setPname(rs.getString(3));
				p.setContent(rs.getString(4));
				p.setPdatatime(rs.getString(5));
				list.add(p);
			}
			bean.setPageList(list);
			sql="select count(*) from Propaganda";
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
			ps = conn.prepareStatement("delete Propaganda where pid="+id);
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
	public boolean addpro(Propaganda propaganda){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into Propaganda values(?,?,?)");
			ps.setString(1,propaganda.getPname());
			ps.setString(2,propaganda.getContent());
			ps.setString(3,propaganda.getPdatatime());
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
