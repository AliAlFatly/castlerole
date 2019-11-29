package com.example.Castlerole.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String username;
	@Column
	@JsonIgnore
	private String password;
	@Column
	private Date joinDate;
	@Column
	private int xCoordinate;
	@Column
	private int yCoordinate;
	@Column
	private int wood;
	@Column
	private int iron;
	@Column
	private int stone;
	@Column
	private int food;
	@Column
	private int troops;

	public void setId(long id) {
		this.id = id;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public void setXCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setYCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public void setIron(int iron) {
		this.iron = iron;
	}

	public void setStone(int stone) {
		this.stone = stone;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public void setTroops(int troops) {
		this.troops = troops;
	}

	public long getId() {
		return id;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public int getWood() {
		return wood;
	}

	public int getIron() {
		return iron;
	}

	public int getStone() {
		return stone;
	}

	public int getFood() {
		return food;
	}

	public int getTroops() {
		return troops;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}