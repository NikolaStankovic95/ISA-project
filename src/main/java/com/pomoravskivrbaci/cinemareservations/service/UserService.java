package com.pomoravskivrbaci.cinemareservations.service;


import com.pomoravskivrbaci.cinemareservations.model.User;

public interface UserService {

	User findUserByEmailAndPassword(String email, String password);
	
	User findUserByEmail(String email);
	
	User createUser(User user);
	
	int setFixedActivatedFor(boolean activated,String email);
}
