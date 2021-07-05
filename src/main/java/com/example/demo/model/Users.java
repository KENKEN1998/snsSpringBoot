package com.example.demo.model;

public class Users {
	private int id;
	private String name;
	private String password;
	private String imgpath;
	
	
	public Users() {
	}

	public Users(int id, String name, String password, String imgpath) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.imgpath = imgpath;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	

}
