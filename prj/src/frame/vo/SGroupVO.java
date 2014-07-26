package frame.vo;

public class SGroupVO {
	private int gNo;
    private String gName;
    private String leaderId;
    private String groimg;
    
    public SGroupVO() {
	}
  
	public SGroupVO(int gNo, String gName, String leaderId) {
		super();
		this.gNo = gNo;
		this.gName = gName;
		this.leaderId = leaderId;
	}
	
	public SGroupVO(String gName, String fileName, int gNo) {
		super();
		this.gNo = gNo;
		this.gName = gName;
		this.groimg = fileName;
	}

	public SGroupVO(String title, String leaderId, String fileName)
	{
		this.gName = title;
		this.leaderId = leaderId;
		this.groimg = fileName;
	}

	public SGroupVO(int gNo, String gName, String leaderId, String groimg) {
		super();
		this.gNo = gNo;
		this.gName = gName;
		this.leaderId = leaderId;
		this.groimg = groimg;
	}

	public int getgNo() {
		return gNo;
	}

	public void setgNo(int gNo) {
		this.gNo = gNo;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public String getGroimg() {
		return groimg;
	}

	public void setGroimg(String groimg) {
		this.groimg = groimg;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	@Override
	public String toString() {
		return "SGroupVO [gNo=" + gNo + ", gName=" + gName + ", leaderId="
				+ leaderId + "]";
	}
}
