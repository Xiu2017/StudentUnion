package com.entity;

import java.util.List;

/**
 * ��װ���з�ҳ��Ϣ�Ĺ�����
 * @author Administrator
 *
 */
public class PageBean {
	
	private int cpage=1;//��ǰҳ
	private int showNum=12;//Ĭ��ÿҳ��ʾ10��
	private int allNum=0;//���ж���������
	private int allPage=0;//���ж���ҳ
	
	private List pageList;//��װÿҳҪ��ʾ������

	public int getCpage() {
		return cpage;
	}

	public void setCpage(int cpage) {
		this.cpage = cpage;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
		//������allNumʱ�Զ�������ҳ��
		if(allNum%showNum==0)
			this.allPage=allNum/showNum;
		else
			this.allPage=allNum/showNum+1;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public List getPageList() {
		return pageList;
	}

	public void setPageList(List pageList) {
		this.pageList = pageList;
	}

	
	
}






