package com.service;

import java.util.List;

import com.dao.CheckDao;
import com.entity.Checkdata;

public class DeptService {
	/**
	 * �������в���
	 * @return
	 */
	public List getDeptlist(){
		return new CheckDao().getDep();
	}
	/**
	 * ��Ӳ��ų�������
	 * @return
	 */
	public boolean getaddCheckdata(Checkdata cd){
		return new CheckDao().addCheckdata(cd);
	}
	/**
	 * ��ʱ�������ѯ
	 */
	public List getCheckservlet(String mytime){
		return new CheckDao().getCheckservelt(mytime);
	}
}
