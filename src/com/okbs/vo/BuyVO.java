package com.okbs.vo;

public class BuyVO {
	private	String onum;
	private	String id;
	private	String pcode;
	private	String tel;
	private	String addr;
	private	int amount;
	private	int price;
	private	String ename;
	private	String ecode;
	private	String status;
	private	String odate;
	private	String pname;
	private	String pdesc;
	private	int pamount;
	private String ccode;
	private String img;
	public String getOnum() {
		return onum;
	}
	public void setOnum(String onum) {
		this.onum = onum;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public int getPamount() {
		return pamount;
	}
	public void setPamount(int pamount) {
		this.pamount = pamount;
	}
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "BuyVO [onum=" + onum + ", id=" + id + ", pcode=" + pcode
				+ ", tel=" + tel + ", addr=" + addr + ", amount=" + amount
				+ ", price=" + price + ", ename=" + ename + ", ecode=" + ecode
				+ ", status=" + status + ", odate=" + odate + ", pname="
				+ pname + ", pdesc=" + pdesc + ", pamount=" + pamount
				+ ", ccode=" + ccode + ", img=" + img + "]";
	}
	
	
}
