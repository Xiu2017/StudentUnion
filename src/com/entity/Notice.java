package com.entity;
/**
 * Ѱ��ʵ����
 * @author Administrator
 *
 */
public class Notice {
	private int nid;  //���id
	private String nname;  //�û���
	private String ncontent;  //����
	private String ntime;  //������ǰʱ��
	private int ntid;  //������ǰʱ��
	
	
	
	public Notice() {
		super();
	}

	public Notice(int nid, String nname, String ncontent, String ntime, int ntid) {
		super();
		this.nid = nid;
		this.nname = nname;
		this.ncontent = ncontent;
		this.ntime = ntime;
		this.ntid = ntid;
	}

	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNtime() {
		return ntime;
	}
	public void setNtime(String ntime) {
		this.ntime = ntime;
	}
	public int getNtid() {
		return ntid;
	}
	public void setNtid(int ntid) {
		this.ntid = ntid;
	}
	
	
	
}
