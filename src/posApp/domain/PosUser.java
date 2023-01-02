package posApp.domain;

import java.util.Date;

public class PosUser {
	private int pos_user_idx;
	private String name;
	private String id;
	private String pass; 
	private Date regdate;
	
	public int getPos_user_idx() {
		return pos_user_idx;
	}
	public void setPos_user_idx(int pos_user_idx) {
		this.pos_user_idx = pos_user_idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
