package com.entity;

public class DormEntity {
	private String id;  //���
	private String building; //����
	private String number; //���Һ�
	private String cid;  //�༶���
	private String cname;  //�༶����
	private String standarda;  //���ֱ�׼a������ǽ�棩
	private String standardb;  //���ֱ�׼b����Ʒ��
	private String standardc;  //���ֱ�׼c����ȫ��
	private String standardd;  //���ֱ�׼d�����̣�
	private String mark;  //�÷�
	private String liable;  //������
	private String date;  //����
	public DormEntity() {
		super();
	}
	public DormEntity(String id, String building, String number, String cid,
			String cname, String standarda, String standardb, String standardc,
			String standardd, String mark, String liable, String date) {
		super();
		this.id = id;
		this.building = building;
		this.number = number;
		this.cid = cid;
		this.cname = cname;
		this.standarda = standarda;
		this.standardb = standardb;
		this.standardc = standardc;
		this.standardd = standardd;
		this.mark = mark;
		this.liable = liable;
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getStandarda() {
		return standarda;
	}
	public void setStandarda(String standarda) {
		this.standarda = standarda;
	}
	public String getStandardb() {
		return standardb;
	}
	public void setStandardb(String standardb) {
		this.standardb = standardb;
	}
	public String getStandardc() {
		return standardc;
	}
	public void setStandardc(String standardc) {
		this.standardc = standardc;
	}
	public String getStandardd() {
		return standardd;
	}
	public void setStandardd(String standardd) {
		this.standardd = standardd;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getLiable() {
		return liable;
	}
	public void setLiable(String liable) {
		this.liable = liable;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
}
