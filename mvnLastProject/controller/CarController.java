package com.sierraclass.mvnLastProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.sierraclass.mvnLastProject.entity.Car;
import com.sierraclass.mvnLastProject.service.CarService;

@RestController
@RequestMapping ("/cars")
public class CarController {
	
	@Autowired
	private CarService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getCar(){
		return new ResponseEntity<Object>(service.getCars(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getCar(@PathVariable Long id){
		try {
			return new ResponseEntity <Object>(service.getCarById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createCar(@RequestBody Car car){
		return new ResponseEntity<Object>(service.createCar(car), HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateCar(@RequestBody Car car, @PathVariable Long id){
		try {
			return new ResponseEntity<Object>(service.updateCar(car, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update product.", HttpStatus.BAD_REQUEST);	
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCar(@PathVariable Long id) {
		try {
			service.removeCar(id);
			return new ResponseEntity<Object>("Successfully deketed product with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
		return new ResponseEntity<Object>("Unable to delete product.", HttpStatus.BAD_REQUEST);
	}
}
}
