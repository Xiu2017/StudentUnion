package com.entity;

public class AdmStuInfo {
	
	private String sno;//ѧ��
	private String sname;//����
	private String spassword;//����
	private String scname;//�༶
	private String sdname;//����
	private String spage;
	public String getSpage() {
		return spage;
	}
	public void setSpage(String spage) {
		this.spage = spage;
	}
	public AdmStuInfo() {
		super();
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSpassword() {
		return spassword;
	}
	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}
	public String getScname() {
		return scname;
	}
	public void setScname(String scname) {
		this.scname = scname;
	}
	public String getSdname() {
		return sdname;
	}
	public void setSdname(String sdname) {
		this.sdname = sdname;
	}
	public AdmStuInfo(String sno, String sname, String spassword, String scname, String sdname, String spage) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.spassword = spassword;
		this.scname = scname;
		this.sdname = sdname;
		this.spage = spage;
	}
	
}
