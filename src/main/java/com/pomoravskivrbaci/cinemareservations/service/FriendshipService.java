package com.pomoravskivrbaci.cinemareservations.service;

import com.pomoravskivrbaci.cinemareservations.model.Friendship;
import com.pomoravskivrbaci.cinemareservations.model.User;

public interface FriendshipService {

	Friendship findByUserAndFriend(User user,User friend);
	Friendship update(Friendship friendship,Long id);
	void deleteById(Long id);
	
}
