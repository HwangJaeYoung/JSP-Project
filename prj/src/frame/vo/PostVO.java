package frame.vo;

import java.sql.Date;

public class PostVO {
	private int uNo;
	private String id;
	private int gNo;
	private String content;
	private Date uploadDate;
	private String file;
	private int likeCount;
	private String secret;
	private String notice;
	
	public PostVO() {
	}
	
	public PostVO(int uNo) {
		super();
		this.uNo = uNo;
	}
	
	public PostVO(int uNo, int like){
		super();
		this.uNo = uNo;
		this.likeCount = like+1;
	}
	
	public PostVO(int uNo, String id, int gNo, String content, Date uploadDate,
			String file, int likeCount, String secret, String notice) {
		super();
		this.uNo = uNo;
		this.id = id;
		this.gNo = gNo;
		this.content = content;
		this.uploadDate = uploadDate;
		this.file = file;
		this.likeCount = likeCount;
		this.secret = secret;
		this.notice = notice;
	}
	
	public PostVO(int uNo, String content, String file) {
		super();
		this.uNo = uNo;
		this.content = content;
		this.file = file;
	}
	
	public PostVO(String id, int gNo, String content, String file, int likeCount, String secret, String notice) {
		super();
		this.id = id;
		this.gNo = gNo;
		this.content = content;
		this.file = file;
		this.likeCount = likeCount;
		this.secret = secret;
		this.notice = notice;
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

	public int getgNo() {
		return gNo;
	}

	public void setgNo(int gNo) {
		this.gNo = gNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}


	@Override
	public String toString() {
		return "PostVO [uNo=" + uNo + ", id=" + id + ", gNo=" + gNo
				+ ", content=" + content + ", uploadDate=" + uploadDate
				+ ", file=" + file + ", likeCount=" + likeCount + ", secret="
				+ secret + ", notice=" + notice + "]";
	}

	
}
