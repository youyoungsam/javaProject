package model;

public class memberShipVO {
	
	private String memberID;
	private String memberPassWord;
	private String memberName;
	private String memberEmail;
	
	public memberShipVO() {
		super();
	}
	
	public memberShipVO(String memberID) {
		super();
		this.memberID = memberID;
	}

	public memberShipVO(String memberID, String memberPassWord, String memberName, String memberEmail) {
		super();
		this.memberID = memberID;
		this.memberPassWord = memberPassWord;
		this.memberName = memberName;
		this.memberEmail = memberEmail;

	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getMemberPassWord() {
		return memberPassWord;
	}

	public void setMemberPassWord(String memberPassWord) {
		this.memberPassWord = memberPassWord;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	

}
