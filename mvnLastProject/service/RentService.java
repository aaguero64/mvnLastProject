package com.sierraclass.mvnLastProject.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sierraclass.mvnLastProject.entity.Car;
import com.sierraclass.mvnLastProject.entity.Client;
import com.sierraclass.mvnLastProject.entity.Rent;
import com.sierraclass.mvnLastProject.repository.CarRepository;
import com.sierraclass.mvnLastProject.repository.ClientRepository;
import com.sierraclass.mvnLastProject.repository.RentRepository;
//import com.sierraclass.mvnLastProject.util.CarLevel;
import com.sierraclass.mvnLastProject.util.RentStatus;

@Service
public class RentService {

		private static final Logger Logger = LogManager.getLogger(RentService.class);
		
		//private final int RENT_DAYS = 2;
		
		@Autowired
		private RentRepository repo;
		
		@Autowired
		private ClientRepository clientRepo;
		
		@Autowired
		private CarRepository carRepo;
		
		public Iterable<Rent> getRents() {
			return repo.findAll();
		}
		
		public Rent submitNewRent(Set<Long> carIds, Long clientId) throws Exception {
			try {
				Client client = clientRepo.findOne(clientId);
				Rent rent = initializeNewRent(carIds, client);
				return repo.save(rent);
			} catch (Exception e) {
				Logger.error("Exeption occurred while trying to create new order for customer: " + clientId, e);
				throw e;
			}
		}
		
		public Rent cancelRent(Long rentId) throws Exception {
			try {
				Rent rent = repo.findOne(rentId);
				rent.setStatus(RentStatus.CANCELLED);
				return repo.save(rent);
			} catch (Exception e) {
				Logger.error("Exeption occurred while trying to cancel order: " + rentId, e);
				throw new Exception ("unable to update order. ");
			}
		}
		
		public Rent rentedRent(Long rentId) throws Exception{
			try {
				Rent rent = repo.findOne(rentId);
				rent.setStatus(RentStatus.RETURNED);
				return repo.save(rent);
			} catch (Exception e) {
				Logger.error("Exception occured while trying to complete order: " + rentId, e);
				throw new Exception ("unable to update order. ");
			}
		}
		
		
		
		private Rent initializeNewRent(Set<Long> carIds, Client client) {
			Rent rent = new Rent();
			rent.setCars(convertToCarSet(carRepo.findAll(carIds)));
			rent.setReserved(LocalDate.now());
			//rent.setRented(LocalDate.now().plusDays(RENT_DAYS));
			rent.setClient(client);
			//rent.setInvoiceAmount(calculateRentTotal(rent.getCars(), car.getLevel()));
			rent.setStatus(RentStatus.RENTED);
			addRentToCars(rent); 
			return rent;
			
		}
		
		private void addRentToCars(Rent rent) {
			Set<Car> cars = rent.getCars();
			for (Car car : cars) {
				car.getRents().add(rent);
			}
		}
		
		
		private Set<Car> convertToCarSet(Iterable<Car> iterable){
			Set<Car> set = new HashSet<Car>();
			for (Car car : iterable) {
				set.add(car);
			}
			return set;
		}
		
//		private double calculateRentTotal(Set<Car> cars, CarLevel level) {
//			double total = 0;
//			for(Car car : cars) {
//				total += car.getPrice();				
//			}
//			return total - total * level.getDiscount();
//		}
		
}
