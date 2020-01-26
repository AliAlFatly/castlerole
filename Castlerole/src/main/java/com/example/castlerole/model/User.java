package com.example.castlerole.model;

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
	private int coordinateX;
	@Column
	private int coordinateY;
	@Column
	private String pictureReference;
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

//	@OneToOne(cascade = CascadeType.ALL)
//	//@JoinColumn(name = "owner")
//	private City city;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, Date joinDate, int coordinateX, int coordinateY, String pictureReference, int wood, int iron, int stone, int food, int troops) {
		this.username = username;
		this.password = password;
		this.joinDate = joinDate;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.pictureReference = pictureReference;
		this.wood = wood;
		this.iron = iron;
		this.stone = stone;
		this.food = food;
		this.troops = troops;
	}

	public void setId(long id) {
		this.id = id;
	}


	public void setPictureReference(String pictureReference) {
		this.pictureReference = pictureReference;
	}

	public String getPictureReference() {
		return pictureReference;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public void setCoordinateX(int xCoordinate) {
		this.coordinateX = xCoordinate;
	}

	public void setCoordinateY(int yCoordinate) {
		this.coordinateY = yCoordinate;
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
		return coordinateX;
	}

	public int getyCoordinate() {
		return coordinateY;
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

//	public City getCity() {
//		return city;
//	}
//
//	public void setCity(City city) {
//		this.city = city;
//	}
}