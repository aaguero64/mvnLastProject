package com.sierraclass.mvnLastProject.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sierraclass.mvnLastProject.util.RentStatus;

@Entity
public class Rent {
	private Long id;
	private LocalDate reserved;
	private LocalDate rented;
	private LocalDate returned;
	private double invoiceAmount;
	private RentStatus status;
	private Set<Car> cars;
	
	@JsonIgnore
	private Client client;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getRented() {
		return rented;
	}
	
	public void setRented(LocalDate rented) {
		this.rented = rented;
	}
	
	public LocalDate getReturned() {
		return returned;
	}
	
	public void setReturned(LocalDate returned) {
		this.returned = returned;
	}
	
	public double getInvoiceAmount() {
		return invoiceAmount;
	}
	
	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	
	@ManyToMany(mappedBy = "rents")
	public Set<Car> getCars() {
		return cars;
	}
	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	
	@ManyToOne
	@JoinColumn(name = "clientId")
	public Client getClient() {
		return client;			
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	public RentStatus getStatus() {
		return status;
	}
	
	public void setStatus(RentStatus status) {
		this.status = status;
	}
	public LocalDate getReserved() {
		return reserved;
	}
	public void setReserved(LocalDate reserved) {
		this.reserved = reserved;
	}
	

}
