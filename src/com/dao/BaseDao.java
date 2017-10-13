package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	
	//�������ݿ�����
	static{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//�������ݿ�
	public static Connection getConnection(){
		String sqlUrl = "jdbc:sqlserver://localhost:1433;databaseName=StudentUnion";
		try {
			return DriverManager.getConnection(sqlUrl,"sa","123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//�ر����ݿ�
	public static void closeDB(Connection conn, PreparedStatement ps, ResultSet rs){
		
			try {
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			try {
				if(ps != null)ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			try {
				if(rs != null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * ������ִ�ж����ݿ���ӣ�ɾ�����޸ĵķ���
	 * @param sql Ҫִ�е�SQL��䣬֧��ռλ��
	 * @param obj ���SQL��ռλ����ͨ��obj���鴫��ռλ����Ӧ������
	 * @return
	 */
	public static boolean updateDb(String sql,Object[] obj){
		Connection conn=getConnection();//��ȡ���ݿ�����
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);//����һ��Ԥ����������
			//���������ռλ����Ӧ��ֵ����˵��ʹ����ռλ��
			if(obj!=null&&obj.length>0){
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i+1, obj[i]);
				}
			}
			if(ps.executeUpdate()>0)//ִ���޸�
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB(conn, ps, null);
		}
		return false;
	}
	
	/**
	 * ���в�ѯ�����Ե����������
	 */
	public static Object[] AllSelect(String sql){
		Connection conn=getConnection();//��ȡ���ݿ�����
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			return new Object[]{conn,ps,rs};
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * �޸�
	 */
	public boolean update(String sql, Object... obj){
		PreparedStatement ps = null;
		Connection conn = BaseDao.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < obj.length; i++){
				ps.setObject(i+1, obj[i]);
			}
			if(ps.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeDB(conn, ps, null );
		}
		return false;
	}
	
	/**
	 * ��ѯ
	 */
	public Object[] sel(String sql){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = BaseDao.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			Object[] obj = new Object[]{rs, ps, conn};
			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
