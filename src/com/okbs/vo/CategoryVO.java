package com.okbs.vo;

public class CategoryVO {
	private String ftcate;//카테고리 대분류 앞 두자리
	private String cgroup;//대분류 카테고리명
	private String frcate;//세부분류 총 네자리
	private String cname;//세부분류 카테고리명
	private String pcode;
	public String getFtcate() {
		return ftcate;
	}
	public void setFtcate(String ftcate) {
		this.ftcate = ftcate;
	}
	public String getCgroup() {
		return cgroup;
	}
	public void setCgroup(String cgroup) {
		this.cgroup = cgroup;
	}
	public String getFrcate() {
		return frcate;
	}
	public void setFrcate(String frcate) {
		this.frcate = frcate;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	@Override
	public String toString() {
		return "CategoryVO [ftcate=" + ftcate + ", cgroup=" + cgroup
				+ ", frcate=" + frcate + ", cname=" + cname + ", pcode="
				+ pcode + "]";
	}
	
	
}
