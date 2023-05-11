package com.okbs.vo;

public class BasketVO {
	private String bnum;
	private String id;
	private String pcode;
	private String pname;
	private int bamount;
	private int price;
	private String img;
	
	
	public String getBnum() {
		return bnum;
	}
	public void setBnum(String bnum) {
		this.bnum = bnum;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getBamount() {
		return bamount;
	}
	public void setBamount(int bamount) {
		this.bamount = bamount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "BasketVO [bnum=" + bnum + ", id=" + id + ", pcode=" + pcode
				+ ", pname=" + pname + ", bamount=" + bamount + ", price="
				+ price + ", img=" + img + "]";
	}

	
}
