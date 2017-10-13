package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.AdmCla;
import com.entity.AdmStuInfo;
import com.entity.AssistantInfo;
import com.entity.BookInfo;
import com.entity.BookStateInfo;


public class StuDao {
	
	/**
	 * ��¼
	 * @param uname
	 * @param upwd
	 * @return
	 */
	public String login(String uname, String upwd){
		String sql = "select spwd,sdid from su_student where (sno = '"+uname+"' or sname = '"+uname+"') and spwd = '"+upwd+"'";
		Object[] obj =  new BaseDao().sel(sql);
		ResultSet rs = (ResultSet) obj[0];
		try {
			while(rs.next()){
				String pwd = rs.getString(1);
				if(pwd.equals(upwd)){
					return rs.getString(2);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeDB((Connection)obj[2],(PreparedStatement)obj[1],rs);
		}
		return null;
	}
	
	/**
	 * ��ѯ�鼮
	 * @param str
	 * @return
	 */
	public List<BookInfo> selBook(String str, String page){
		String sql = "declare @nu int exec usp_pag '"+str+"','"+str+"',"+page+",12,@nu output";
		Object[] obj =  new BaseDao().sel(sql);
		ResultSet rs = (ResultSet) obj[0];
		List<BookInfo> list = new ArrayList<BookInfo>();
		try {
			while(rs.next()){
				list.add(new BookInfo(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), null, rs.getString(7)));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeDB((Connection)obj[2],(PreparedStatement)obj[1],rs);
		}
		return null;
	}
	
	//����鼮
	public boolean loanBook(BookStateInfo bookStateInfo){
		String sql = "DECLARE @bookno int, @stuno int, @outtime datetime set @bookno = (select l_b_id from lifeDep_book_info where l_b_no = ?) set @stuno = (select [sid] from su_student where sno = ?) set @outtime = ? set @outtime = (case @outtime when '' then GETDATE() end) if(select l_b_state from lifeDep_book_info where l_b_id = @bookno) = 1 begin insert into lifeDep_book_state_info values(@bookno,@stuno,@outtime,default,default) end";
		return new BaseDao().update(sql, bookStateInfo.getBno(), bookStateInfo.getStuno(), bookStateInfo.getOuttime());
	}
	
	//�黹�鼮
	public boolean returned(String str){
		String sql = "DECLARE @l_b_no_t varchar(50) set @l_b_no_t = ? if(select l_b_state from lifeDep_book_info where l_b_no = @l_b_no_t) = 2 begin update lifeDep_book_info set l_b_state = 1 where l_b_no = @l_b_no_t end";
		return new BaseDao().update(sql, str);
	}
	

	/**
	 * ��ѯ��־�������鼮�Ľ軹��¼��
	 */
	public List<BookStateInfo> viewLog(String str, String lpage){
		String sql = "declare @oooo int exec usp_log '"+str+"',"+lpage+",10,@oooo output";
		Object[] obj =  new BaseDao().sel(sql);
		ResultSet rs = (ResultSet) obj[0];
		List<BookStateInfo> list = new ArrayList<>();
		try {
			while(rs.next()){
				list.add(new BookStateInfo(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(10)));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeDB((Connection)obj[2],(PreparedStatement)obj[1],rs);
		}
		return null;
	}
	
	//����鼮
	public boolean addBook(BookInfo bookInfo){
		String sql = "insert into lifeDep_book_info values(?,?,?,default)";
		return new BaseDao().update(sql, bookInfo.getNo(), bookInfo.getName(), bookInfo.getAuthor());
	}
	
	//��ѯ������Ϣ
	public List<AssistantInfo> selAss(String str, String page){
		String sql = "declare @nu int exec usp_ass '"+str+"',"+page+",12,@nu output";
		Object[] obj =  new BaseDao().sel(sql);
		ResultSet rs = (ResultSet) obj[0];
		List<AssistantInfo> list = new ArrayList<>();
		try {
			while(rs.next()){
				list.add(new AssistantInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7)));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeDB((Connection)obj[2],(PreparedStatement)obj[1],rs);
		}
		return null;
	}
	
	//�޸�������Ϣ
	public boolean updAss(AssistantInfo assinfo){
		String sql = "update Assistant set asno = (select top 1 s.[sid] from Assistant a, su_student s where s.sno = ?), acno = (select top 1 c.cid from Assistant a, su_class c where c.cname = ?) where aid = ?";
		return new BaseDao().update(sql, assinfo.getAsno(), assinfo.getAclass(), assinfo.getAid());
	}
	
	//����������Ϣ
	public boolean insAss(AssistantInfo assinfo){
		String sql = "insert into Assistant values((select [sid] from su_student where sno = ?), (select cid from su_class where cname = ?))";
		return new BaseDao().update(sql, assinfo.getAsno(), assinfo.getAclass());
	}
	
	//ɾ��������Ϣ
	public boolean delAss(String str){
		String sql = "delete from Assistant where aid = ?";
		return new BaseDao().update(sql, str);
	}
	
	//��ѯ��ѡ�༶
	public String[] selCla(){
		int claCount = 23;  //����༶����
		String sql = "select cname from su_class";
		Object[] obj =  new BaseDao().sel(sql);
		ResultSet rs = (ResultSet) obj[0];
		String[] strs = new String[claCount];
		try {
			while(rs.next()){
				strs[rs.getRow()-1] = rs.getString(1);
			}
			return strs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeDB((Connection)obj[2],(PreparedStatement)obj[1],rs);
		}
		return null;
	}
	
	/**
	 * ��ѯģ��
	 * @return
	 */
	public List<Object > test(){
		String sql = "";
		Object[] obj =  new BaseDao().sel(sql);
		ResultSet rs = (ResultSet) obj[0];
		List<Object > list =new ArrayList<>();
		try {
			while(rs.next()){
				
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeDB((Connection)obj[2],(PreparedStatement)obj[1],rs);
		}
		return null;
	}
	
	//����Ա��ѯ�༶
		public List<AdmCla> admClaSel(String page){
			String sql = "declare @c int exec adm_claInfo "+page+",12,@c output";
			Object[] obj =  new BaseDao().sel(sql);
			ResultSet rs = (ResultSet) obj[0];
			List<AdmCla> list =new ArrayList<AdmCla>();
			try {
				while(rs.next()){
					list.add(new AdmCla(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5)));
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				BaseDao.closeDB((Connection)obj[2],(PreparedStatement)obj[1],rs);
			}
			return null;
		}
		
		//����Ա�����༶
		public boolean admClaIns(String cname){
			String sql = "if (select COUNT(cid) from su_class where cname = ?) = 0 insert into su_class values(?)";
			return new BaseDao().update(sql, cname, cname);
		}
		
		//����Ա�޸İ༶
		public boolean admClaUpd(AdmCla ac){
			String sql = "update su_class set cname = ? where cid = ?";
			return new BaseDao().update(sql, ac.getCname(), ac.getCid());
		}
		
		//����Աѧ�������ȡ�༶
		public List<String> admStuCla(){
			String sql = "select * from su_class";
			Object[] obj =  new BaseDao().sel(sql);
			ResultSet rs = (ResultSet) obj[0];
			List<String> list =new ArrayList<>();
			try {
				while(rs.next()){
					list.add(rs.getString(2));
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				BaseDao.closeDB((Connection)obj[2],(PreparedStatement)obj[1],rs);
			}
			return null;
		}
		
		//����Աѧ�������ȡ����
		public List<String> admStuDep(){
			String sql = "select * from su_dept";
			Object[] obj =  new BaseDao().sel(sql);
			ResultSet rs = (ResultSet) obj[0];
			List<String> list =new ArrayList<>();
			try {
				rs.next();//��������Ա
				while(rs.next()){
					list.add(rs.getString(2));
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				BaseDao.closeDB((Connection)obj[2],(PreparedStatement)obj[1],rs);
			}
			return null;
		}
		
		//����Ա��ѯѧ����Ϣ
		public List<AdmStuInfo> admStu(AdmStuInfo v){
			String sql = "declare @sum int exec adm_stuInfo '"+v.getSname()+"', '"+v.getScname()+"', '"+v.getSdname()+"',"+v.getSpage()+",12,@sum output";
			Object[] obj =  new BaseDao().sel(sql);
			ResultSet rs = (ResultSet) obj[0];
			List<AdmStuInfo> list =new ArrayList<>();
			try {
				while(rs.next()){
					list.add(new AdmStuInfo(rs.getString(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7)));
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				BaseDao.closeDB((Connection)obj[2],(PreparedStatement)obj[1],rs);
			}
			return null;
		}
		
		//����Ա�޸�ѧ����Ϣ
		public boolean admStuUpd(AdmStuInfo as){
			String sql = "update su_student set sname = ?, spwd = ?, scid = (select cid from su_class where cname = ?), sdid = (select did from su_dept where dname = ?) where sno = ?";
			return new BaseDao().update(sql, as.getSname(), as.getSpassword(), as.getScname(), as.getSdname(), as.getSno());
		}
		
		//����Ա   ���ѧ��
		public boolean admStuAdd(AdmStuInfo as){
			String sql = "insert into su_student values(?,?,?,(select cid from su_class where cname = ?),(select did from su_dept where dname = ?)) ";
			return new BaseDao().update(sql, as.getSno(), as.getSname(), as.getSpassword(), as.getScname(), as.getSdname()); 
		}
		
}
