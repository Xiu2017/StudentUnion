package com.entity;

public class NoticeEntity {
	private String nid;  //����id
	private String npublisher;  //������
	private String ndate;  //��������
	private String nweek;  //����
	private String ntitle;  //����
	private String ncontent;  //����
	private String ndid;  //�������ŵ�id
	private String ndname;  //�������ŵ�����
	private String nregion;  //��������dept��public��
	public NoticeEntity() {
		super();
	}
	public NoticeEntity(String nid, String npublisher, String ndate,
			String nweek, String ntitle, String ncontent, String ndid,
			String ndname, String nregion) {
		super();
		this.nid = nid;
		this.npublisher = npublisher;
		this.ndate = ndate;
		this.nweek = nweek;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.ndid = ndid;
		this.ndname = ndname;
		this.nregion = nregion;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getNpublisher() {
		return npublisher;
	}
	public void setNpublisher(String npublisher) {
		this.npublisher = npublisher;
	}
	public String getNregion() {
		return nregion;
	}
	public void setNregion(String nregion) {
		this.nregion = nregion;
	}
	public String getNdate() {
		return ndate;
	}
	public void setNdate(String ndate) {
		this.ndate = ndate;
	}
	public String getNweek() {
		return nweek;
	}
	public void setNweek(String nweek) {
		switch(nweek){
			case "1" : this.nweek = "������";break;
			case "2" : this.nweek = "����һ";break;
			case "3" : this.nweek = "���ڶ�";break;
			case "4" : this.nweek = "������";break;
			case "5" : this.nweek = "������";break;
			case "6" : this.nweek = "������";break;
			case "7" : this.nweek = "������";break;
			default : this.nweek = "����";break;
		}
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNdid() {
		return ndid;
	}
	public void setNdid(String ndid) {
		this.ndid = ndid;
	}
	public String getNdname() {
		return ndname;
	}
	public void setNdname(String ndname) {
		this.ndname = ndname;
	}
}
