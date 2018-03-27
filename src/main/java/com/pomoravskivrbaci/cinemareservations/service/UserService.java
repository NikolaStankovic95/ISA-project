package com.pomoravskivrbaci.cinemareservations.service;


import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Friendship;
import com.pomoravskivrbaci.cinemareservations.model.User;

public interface UserService {

	List<User> findAll();
	
	User findUserByEmailAndPassword(String email, String password);
	
	User findUserByEmail(String email);
	
	
	User findUserById(Long id);
	
	User createUser(User user);
	
	User update(User u,Long id);
	int setPasswordFor(String pass,Long id);

	int updateUser(Long id, String email, String city, String name,
			String surname, String number);

	int setFixedActivatedFor(boolean activated,Long id);
	
	List<Friendship> findFriends(User id,boolean accepted);
	

	List<Friendship> findFriendRequests(User id,boolean accepted);
	
}
