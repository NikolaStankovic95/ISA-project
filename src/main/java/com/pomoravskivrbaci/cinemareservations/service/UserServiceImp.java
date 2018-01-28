package com.pomoravskivrbaci.cinemareservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
