package model;

public class reviewVO {

	private int no;
	private String productName;
	private String memberID;
	private String review;

	public reviewVO() {
		super();
	}

	public reviewVO(String productName, String memberID, String review) {
		super();
		this.productName = productName;
		this.memberID = memberID;
		this.review = review;
	}

	public reviewVO(int no, String productName, String memberID, String review) {
		super();
		this.no = no;
		this.productName = productName;
		this.memberID = memberID;
		this.review = review;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	
}
