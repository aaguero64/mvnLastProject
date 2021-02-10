package com.sierraclass.mvnLastProject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sierraclass.mvnLastProject.entity.Client;
import com.sierraclass.mvnLastProject.repository.ClientRepository;

@Service
public class ClientService {
	
		private static final Logger Logger = LogManager.getLogger(ClientService.class);
		
		@Autowired
		private ClientRepository repo;
		
		public Client getClientById(Long id) throws Exception {
			try {
				return repo.findOne(id);
			} catch (Exception e) {
				Logger.error("Exception occurred while trying to retrieve customer: \" + id, e");
				throw e;
			}
		}
		
		public Iterable<Client> getClient(){
			return repo.findAll();
       }
		
		public Client createCliente(Client client) {
			return repo.save(client);
		}
		
		public Client updateClient(Client client, Long id) throws Exception{
			try {
				Client oldClient = repo.findOne(id);
				oldClient.setAddress(client.getAddress());
				oldClient.setFirstName(client.getFirstName());
				oldClient.setLastName(client.getLastName());
				oldClient.setEmail(client.getEmail());
				oldClient.setPhoneNumber(client.getPhoneNumber());
				return repo.save(oldClient);
			}catch (Exception e) {
				Logger.error("Exeption occured while trying to update customer: " + id, e );
				throw new Exception("Unable to update customer.");
			}
		}
		
		public void deleteClient(Long id) throws Exception {
			try {
				repo.delete(id);
			} catch (Exception e) {
				Logger.error("Exception occurred while trying to delete customer: " + id, e);
				throw new Exception("Unable to delete customer.");
			}
		}

}
