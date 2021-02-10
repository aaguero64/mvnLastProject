package com.sierraclass.mvnLastProject.entity;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sierraclass.mvnLastProject.util.CarLevel;

@Entity
public class Car {
	private Long id;
	private String make;
	private String model;
	private double miles;
	private String year;
	private double pricebyday;
	private CarLevel level;
	
	@JsonIgnore
	private Set<Rent> rents;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
		public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public double getMiles() {
		return miles;
	}
	
	public void setMiles(double miles) {
		this.miles = miles;
	}
//	public Date getYear() {
//		return year;
//	}
//	public void setYear(Date year) {
//		this.year = year;
//	}
	//public double getPrice() {
	//	return price;
	//}
	//public void setPrice(double price) {
	//	this.price = price;
	//}
	
	@JoinColumn
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "rent_car",
			joinColumns = @JoinColumn(name = "rentId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "carId", referencedColumnName = "id"))
	public Set<Rent> getRents(){
		return rents;
	}
	public void setRents(Set<Rent> rents) {
		this.rents = rents;
	}
	public CarLevel getLevel() {
		return level;
	}
	public void setLevel(CarLevel level) {
		this.level = level;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public double getPricebyday() {
		return pricebyday;
	}
	public void setPricebyday(double pricebyday) {
		this.pricebyday = pricebyday;
	}
	
	
	

}
