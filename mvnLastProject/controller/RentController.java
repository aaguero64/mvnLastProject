  package com.sierraclass.mvnLastProject.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sierraclass.mvnLastProject.entity.Rent;
import com.sierraclass.mvnLastProject.service.RentService;
import com.sierraclass.mvnLastProject.util.RentStatus;

//Rents will be created by the program on the CLIENT number that you want and will add the Rent to and the 
//CAR that you will create before.
@RestController
@RequestMapping("clients/{id}/rents")
public class RentController {
	
	@Autowired
	private RentService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAllRent(){
		return new ResponseEntity<Object>(service.getRents(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createClient(@RequestBody Set<Long> carIds, @PathVariable Long id){
		try {
			return new ResponseEntity<Object>(service.submitNewRent(carIds, id), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}	
		
	@RequestMapping(value="/{rentId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateRent(@RequestBody Rent rent, @PathVariable Long rentId){
		try {
			if (rent.getStatus().equals(RentStatus.CANCELLED)) {
				return new ResponseEntity<Object>(service.cancelRent(rentId), HttpStatus.OK);
			}else if (rent.getStatus().equals(RentStatus.RENTED)) {
				return new ResponseEntity<Object>(service.rentedRent(rentId), HttpStatus.OK);	
			}
			return new ResponseEntity<Object>("Invalid update request", HttpStatus.BAD_REQUEST);
			}catch (Exception e) {
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);	
			}
	}
	
		
			
}
