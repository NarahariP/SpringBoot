package com.hari.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;


public class Todo {

	private int id;
	private String user;
	@Size(min = 6, message = "Please enter at least 6 characters.")
	private String desc;
	private Date targetDate;
	@Value("false")
	private boolean done;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public Todo(int id, String user, String desc, Date targetDate, boolean done) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.done = done;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", user=" + user + ", desc=" + desc + ", targetDate=" + targetDate + ", done="
				+ done + "]";
	}
	
}
