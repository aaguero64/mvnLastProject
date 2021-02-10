package com.sierraclass.mvnLastProject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sierraclass.mvnLastProject.entity.Car;
import com.sierraclass.mvnLastProject.repository.CarRepository;

@Service
public class CarService {
	private static final Logger Logger = LogManager.getLogger(CarService.class);
	
	@Autowired
	private CarRepository repo;
	
	public Iterable<Car> getCars() {
		return repo.findAll();
	}
	
	public Car getCarById (Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			Logger.error("Exception occurred while trying to retrieve customer: \" + id, e");
			throw e;
		}
		
	}

	public Car createCar(Car car) {
		return repo.save(car);
	}
	
	public Car updateCar(Car car, Long id) throws Exception {
		try {
			Car oldCar = repo.findOne(id);
			oldCar.setMake(car.getMake());
			oldCar.setModel(car.getModel());
			oldCar.setMiles(car.getMiles());
			oldCar.setYear(car.getYear());
			oldCar.setLevel(car.getLevel());
			return repo.save(oldCar);
		} catch (Exception e) {
			Logger.error ("Exception occurd while trying to update product: " + id, e);
			throw new Exception ("Unable to update product");
		}
	}
	
	public void removeCar(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			Logger.error("Exception occurred while trying to delete product: " + id, e);
			throw new Exception("Unable to delete product.");
		}
	}
}
