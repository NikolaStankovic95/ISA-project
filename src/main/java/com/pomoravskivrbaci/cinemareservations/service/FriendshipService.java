package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Friendship;
import com.pomoravskivrbaci.cinemareservations.model.User;

public interface FriendshipService {

	Friendship findOne(Long id);
	List<Friendship> findAll();
	Friendship findByUserAndFriend(User user,User friend);
	Friendship update(Friendship friendship,Long id);
	void deleteById(Long id);
	
}
