package frame.vo;

import java.sql.Date;

public class CommentsVO {
	private int cNo;
	private int uNo;
	private String id;
	private String content;
	private Date regdate;
	
	public CommentsVO() {
	}

	public CommentsVO(int cNo, String content){
		super();
		this.cNo = cNo;
		this.content = content;
	}

	public CommentsVO(int uNo, String user, String content)
	{
		super();
		this.uNo = uNo;
		this.id = user;
		this.content = content;
		
	}
	
	public CommentsVO(int cNo, int uNo, String id, String content, Date regdate) {
		super();
		this.cNo = cNo;
		this.uNo = uNo;
		this.id = id;
		this.content = content;
		this.regdate = regdate;
	}


	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public int getuNo() {
		return uNo;
	}

	public void setuNo(int uNo) {
		this.uNo = uNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "CommentsVO [cNo=" + cNo + ", uNo=" + uNo + ", id=" + id
				+ ", content=" + content + ", regdate=" + regdate + "]";
	}
	
	
}
