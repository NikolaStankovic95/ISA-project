package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pomoravskivrbaci.cinemareservations.model.Friendship;
import com.pomoravskivrbaci.cinemareservations.model.User;



public interface UserService {

	List<User> findAll();

	User findUserByEmailAndPassword(String email, String password);

	User findUserByEmail(String email);

	List<User> findAllUsersExceptLogged(Long id);

	User findUserById(Long id);

	User createUser(User user);

	User update(User u, Long id);

	int setFixedActivatedFor(boolean activated, Long id);

	List<Friendship> findFriends(User id, boolean accepted);

	List<Friendship> findFriendRequests(User id, boolean accepted);
	
	Page<User> findUsers(String name,String surname, Pageable pageable);
}
