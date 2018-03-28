package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Friendship;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.repository.userRepository;


@Service
public class UserServiceImp implements UserService {

	@Autowired 
	private userRepository userRepository;

	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.findUserByEmailAndPassword(email, password);
	}
	
	
	
	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
		
	}


	@Override
	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		System.out.println("Mail from FIND "+id);
		return userRepository.findUserById(id);
	}


	@Override
	public int setFixedActivatedFor(boolean activated, Long id) {
		// TODO Auto-generated method stub
		System.out.println("Mail from message "+id);
		return userRepository.setFixedActivatedFor(activated, id);
	}


	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}


	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findUserByEmail(email);
	}


	@Override
	public User update(User u,Long id) {
		// TODO Auto-generated method stub
		u.setId(id);
		return userRepository.save(u);
	}
	@Override
	public List<Friendship> findFriends(User id,boolean accepted) {
		// TODO Auto-generated method stub
		return userRepository.findFriends(id,accepted);
	}



	@Override
	public List<Friendship> findFriendRequests(User id, boolean accepted) {
		// TODO Auto-generated method stub
		return userRepository.findFriendRequests(id, accepted);
	}



	@Override
	public List<User> findUserByNameAndSurnameIgnorableCaseContaining(String name, String surname) {
		// TODO Auto-generated method stub
		return userRepository.findUserByNameAndSurnameIgnoreCaseContaining(name, surname);
	}



	@Override
	public List<User> findUserByNameIgnorableCaseContaining(String name) {
		// TODO Auto-generated method stub
		return userRepository.findUserByNameIgnoreCaseContaining(name);
	}
	


	@Override
	public List<User> findUserBySurnameIgnorableCaseContaining(String surname) {
		// TODO Auto-generated method stub
		return userRepository.findUserBySurnameIgnoreCaseContaining(surname);
	}
	@Override
	public int updateUser(Long id, String email, String city, String name,
			String surname, String number) {
		// TODO Auto-generated method stub
		return userRepository.updateUser(id,email,city,name,surname,number);
	}
	@Override
	public int setPasswordFor(String pass, Long id) {
		// TODO Auto-generated method stub
		return  userRepository.setPasswordFor(pass, id);
	}
}
