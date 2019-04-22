package com.stackroute.moviecruiserauthenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.moviecruiserauthenticationservice.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	User findByUserIdAndPassword(String userId,String password);

}
