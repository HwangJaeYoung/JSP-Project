package frame.vo;

import java.sql.Date;

public class CustomerVO {
    private String id;
    private String pwd;
    private String name; // 사용자의 이름
    private Date birthday;
    private String sex;
    private String phone;
    private String address;
    private String email; 
    private String file;
    private String c_status;
    
    public CustomerVO() {
	}

    public CustomerVO(String id, String pwd, String name, Date birthday,
			String sex, String phone, String address, String email,
			String file){
    	
    	super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birthday = birthday;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.file = file;
    }

	public CustomerVO(String id, String pwd, String phone, String address,
			String email, String file) {
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.file = file;
	}

	public CustomerVO(String id, String pwd, String name, Date birthday,
			String sex, String phone, String address, String email,
			String file, String c_status) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birthday = birthday;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.file = file;
		this.c_status = c_status;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getC_status() {
		return c_status;
	}

	public void setC_status(String c_status) {
		this.c_status = c_status;
	}

	@Override
	public String toString() {
		return "CustomerVO [id=" + id + ", pwd=" + pwd + ", name=" + name
				+ ", birthday=" + birthday + ", sex=" + sex + ", phone="
				+ phone + ", address=" + address + ", email=" + email
				+ ", file=" + file + ", c_status=" + c_status + "]";
	}

	
	
}
