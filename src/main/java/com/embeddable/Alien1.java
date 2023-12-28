package com.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "alien1_table")
public class Alien1 {
	
	@Id
	private int aid;
	private AlienName objectAname;
	private String color;
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public AlienName getAname() {
		return objectAname;
	}
	public void setAname(AlienName objectAname) {
		this.objectAname = objectAname;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Alien1 [aid=" + aid + ", objectAname=" + objectAname + ", color=" + color + "]";
	}

	
	

}
