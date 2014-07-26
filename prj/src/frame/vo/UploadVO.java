package frame.vo;

public class UploadVO {
	private String id;
	private int gNo;
	private int uNo;
	
	public UploadVO() {
	}

	public UploadVO(String id, int gNo, int uNo) {
		super();
		this.id = id;
		this.gNo = gNo;
		this.uNo = uNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getgNo() {
		return gNo;
	}

	public void setgNo(int gNo) {
		this.gNo = gNo;
	}

	public int getuNo() {
		return uNo;
	}

	public void setuNo(int uNo) {
		this.uNo = uNo;
	}

	@Override
	public String toString() {
		return "UploadVO [id=" + id + ", gNo=" + gNo + ", uNo=" + uNo + "]";
	}
}
