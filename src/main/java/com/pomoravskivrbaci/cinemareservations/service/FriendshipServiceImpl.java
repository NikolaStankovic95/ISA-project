package com.pomoravskivrbaci.cinemareservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Friendship;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.repository.FriendshipRepository;

@Service
public class FriendshipServiceImpl implements FriendshipService {

	@Autowired
	private FriendshipRepository friendshipRepository;
	
	@Override
	public Friendship findByUserAndFriend(User user, User friend) {
		// TODO Auto-generated method stub
		return friendshipRepository.findByUserAndFriend(user, friend);
	}

	@Override
	public Friendship update(Friendship friendship, Long id) {
		
		// TODO Auto-generated method stub
		friendship.setId(id);
		return friendshipRepository.save(friendship);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		System.out.println("Brisanjeee");
		 friendshipRepository.deleteById(id);
	}

}
