package model;

public class productionVO {
	
	// ==================== 제품 테이블 모델 ==================== // 
	
	private int no;
	private String productName;
	private String item;
	private String info;
	private String filename;
	private String filename2;
	private String filename3;
	
	public productionVO() {
		super();
	}

	
	public productionVO(String productName, String item, String info, String filename) {
		super();
		this.productName = productName;
		this.item = item;
		this.info = info;
		this.filename = filename;
	}


	public productionVO(int no, String productName, String item, String info, String filename) {
		super();
		this.no = no;
		this.productName = productName;
		this.item = item;
		this.info = info;
		this.filename = filename;
	}

	public productionVO(int no, String productName, String item, String info, String filename, String filename2,
			String filename3) {
		super();
		this.no = no;
		this.productName = productName;
		this.item = item;
		this.info = info;
		this.filename = filename;
		this.filename2 = filename2;
		this.filename3 = filename3;
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

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename2() {
		return filename2;
	}

	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}

	public String getFilename3() {
		return filename3;
	}

	public void setFilename3(String filename3) {
		this.filename3 = filename3;
	}

	
	
	
}
