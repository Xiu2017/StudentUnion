package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.PageBean;
import com.entity.Tb_Club;

/**
 * ���Ų����������ݿ����
 * @author Administrator
 */
public class ClubDeptDao {
	//�Ǽ��в�ѯ�������ŵķ���
	public static List getClubInfo(){
		//����dao�ڵĲ�ѯ��������ȡ���˴���conn��ps��rs������
		 Object[] obj=new BaseDao().AllSelect("select * from dp_clubDept");
		 //���ղ�ѯ�귵�صĽ����
		 ResultSet rs=(ResultSet) obj[2];
		 Connection conn=(Connection) obj[0];
		 PreparedStatement ps=(PreparedStatement) obj[1];
			try {
				//��������������浽�����в�����
				List list=new ArrayList();
				while(rs.next()){
					Tb_Club c=new Tb_Club();
					c.setClubid(rs.getInt(1));
					c.setClubname(rs.getString(2));
					list.add(c);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				BaseDao.closeDB(conn, ps, rs);
			}
		return null;
	}
	
	//�����ݿ������ӵǼ����ݵķ���
	public static boolean insertClub_dengji(int peoplenum,int peoplecomenum,int peoplenocome,String thetime,int aid){
		return new BaseDao().updateDb("insert into dp_clubDept_attendance values(?,?,?,?,?)", new Object[]{peoplenum,peoplecomenum,peoplenocome,thetime,aid});
	}
	
	//��ѯ�з�ҳ��ѯ
	public static PageBean getsomeClub(PageBean bean){
		//����dao�ڵĲ�ѯ��������ȡ���˴���conn��ps��rs������
		 Object[] obj=new BaseDao().AllSelect("select * from (select ROW_NUMBER() over(order by thetime desc)aid,* from dp_clubDept dc join dp_clubDept_attendance dca on dca.aclubid=dc.clubid)r where r.aid>(("+bean.getCpage()+"-1)*"+bean.getShowNum()+") and r.aid<=("+bean.getCpage()+"*"+bean.getShowNum()+")");
		 //���ղ�ѯ�귵�صĽ����
		 ResultSet rs=(ResultSet) obj[2];
		 Connection conn=(Connection) obj[0];
		 PreparedStatement ps=(PreparedStatement) obj[1];
			try {
				//��������������浽�����в�����
				List list=new ArrayList();
				while(rs.next()){
					Tb_Club c=new Tb_Club();
					c.setClubname(rs.getString(3));
					c.setAttid(rs.getInt(4));
					c.setPeoplenum(rs.getInt(5));
					c.setPeoplecomenum(rs.getInt(6));
					c.setPeoplenocome(rs.getInt(7));
					c.setThetime(rs.getString(8).substring(0, 10));
					list.add(c);
				}
				bean.setPageList(list);
				ps=conn.prepareStatement("select count(*) from  dp_clubDept dc join dp_clubDept_attendance dca on dca.aclubid=dc.clubid");
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
	
	//��ѯҳ��ɾ����¼�ķ���
	public static boolean removeRow(int attid){
		return new BaseDao().updateDb("delete from dp_clubDept_attendance where attid=?", new Object[]{attid});
	}
	
	//��������������ģ����ѯ
	public static PageBean getClubtoname(PageBean bean,String clubname){
		//����dao�ڵĲ�ѯ��������ȡ���˴���conn��ps��rs������
		 Object[] obj=new BaseDao().AllSelect("select * from (select ROW_NUMBER() over(order by thetime desc)aid,* from dp_clubDept dc join dp_clubDept_attendance dca on dca.aclubid=dc.clubid where clubname like '%"+clubname+"%')r where r.aid>(("+bean.getCpage()+"-1)*"+bean.getShowNum()+") and r.aid<=("+bean.getCpage()+"*"+bean.getShowNum()+")");
		 //���ղ�ѯ�귵�صĽ����
		 ResultSet rs=(ResultSet) obj[2];
		 Connection conn=(Connection) obj[0];
		 PreparedStatement ps=(PreparedStatement) obj[1];
			try {
				//��������������浽�����в�����
				List list=new ArrayList();
				while(rs.next()){
					Tb_Club c=new Tb_Club();
					c.setClubname(rs.getString(3));
					c.setAttid(rs.getInt(4));
					c.setPeoplenum(rs.getInt(5));
					c.setPeoplecomenum(rs.getInt(6));
					c.setPeoplecomenum(rs.getInt(7));
					c.setThetime(rs.getString(8).substring(0, 10));
					list.add(c);
				}
				bean.setPageList(list);
				ps=conn.prepareStatement("select count(*) from  dp_clubDept dc join dp_clubDept_attendance dca on dca.aclubid=dc.clubid where clubname like '%"+clubname+"%'");
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
}
