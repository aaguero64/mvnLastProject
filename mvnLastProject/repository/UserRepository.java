package com.sierraclass.mvnLastProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.sierraclass.mvnLastProject.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	public User findByUsername(String username);
}
