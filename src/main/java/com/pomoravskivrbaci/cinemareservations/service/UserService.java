package com.pomoravskivrbaci.cinemareservations.service;


import com.pomoravskivrbaci.cinemareservations.model.User;

public interface UserService {

	User findUserByEmailAndPassword(String email, String password);
	
	User findUserById(Long id);
	
	User createUser(User user);
	
	int setFixedActivatedFor(boolean activated,Long id);
}
