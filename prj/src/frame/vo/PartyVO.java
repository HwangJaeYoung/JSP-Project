package frame.vo;

public class PartyVO {
	private String id;
	private int gNo;
	private String status;
	
	public PartyVO() {
	}

	public PartyVO(String id, int gNo, String status) {
		super();
		this.id = id;
		this.gNo = gNo;
		this.status = status;
	}
	
	public PartyVO(String id, int gNo) {
		super();
		this.id = id;
		this.gNo = gNo;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PartyVO [id=" + id + ", gNo=" + gNo + ", status=" + status
				+ "]";
	}
}
