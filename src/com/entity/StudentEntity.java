package com.entity;

public class StudentEntity {
	private String sid;  //ѧ��id
	private String sno;  //ѧ��
	private String sname;  //����
	private String spwd;  //����
	private String scid;  //�����༶��id
	private String scname;  //�����༶������
	private String sdid;  //�������ŵ�id
	private String sdname;  //�������ŵ�����
	public StudentEntity() {
		super();
	}
	public StudentEntity(String sid, String sno, String sname, String spwd,
			String scid, String scname, String sdid, String sdname) {
		super();
		this.sid = sid;
		this.sno = sno;
		this.sname = sname;
		this.spwd = spwd;
		this.scid = scid;
		this.scname = scname;
		this.sdid = sdid;
		this.sdname = sdname;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
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
	public String getSpwd() {
		return spwd;
	}
	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}
	public String getScid() {
		return scid;
	}
	public void setScid(String scid) {
		this.scid = scid;
	}
	public String getScname() {
		return scname;
	}
	public void setScname(String scname) {
		this.scname = scname;
	}
	public String getSdid() {
		return sdid;
	}
	public void setSdid(String sdid) {
		this.sdid = sdid;
	}
	public String getSdname() {
		return sdname;
	}
	public void setSdname(String sdname) {
		this.sdname = sdname;
	}
}
