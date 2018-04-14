package com.pomoravskivrbaci.cinemareservations.testing.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.Friendship;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.service.FriendshipService;
import com.pomoravskivrbaci.cinemareservations.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipServiceTesting {

	@Autowired
	private FriendshipService friendshipService;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void findAll(){
		List<Friendship> friendships=friendshipService.findAll();
		assertNotEquals(friendships.size(),0);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void deleteById(){
		int sizeBeforeDelete=friendshipService.findAll().size();
		
		friendshipService.deleteById(82L);
		friendshipService.deleteById(83L);
		int sizeAfterDelete=friendshipService.findAll().size();
		
		assertNotEquals(sizeBeforeDelete,sizeAfterDelete);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void update(){
		Friendship friendship=friendshipService.findOne(1L);
		friendship.setAccepted(true);
		Friendship updated=friendshipService.update(friendship, 1L);
		assertEquals(friendship.isAccepted(),updated.isAccepted());

	}
	@Test
	public void findByUserAndFriend(){
		User user=userService.findUserById(3L);
		User friend=userService.findUserById(5L);
		Friendship friendship=friendshipService.findByUserAndFriend(user, friend);
		assertNotNull(friendship);
	}
}
