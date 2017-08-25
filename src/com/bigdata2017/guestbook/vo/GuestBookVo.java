package com.bigdata2017.guestbook.vo;

public class GuestBookVo {

	private int number;
	private String name;
	private String password;
	private String content;
	private String date;
	public int getNumber() {
		return number;
	}
	public void setNumber(int no) {
		this.number = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "GuestBookVo [number=" + number + ", name=" + name + ", password=" + password + ", content=" + content
				+ ", date=" + date + "]";
	}
	
	
}
