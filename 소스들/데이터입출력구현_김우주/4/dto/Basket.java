package dto;

public class Basket {
	private	String bnum;
	private	String id;
	private	String pcode;
	private	int bamount;
	private	int price;
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

}
