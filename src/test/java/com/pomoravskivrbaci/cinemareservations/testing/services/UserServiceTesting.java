package com.pomoravskivrbaci.cinemareservations.testing.services;

import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.CITY;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.EMAIL;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.ID;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.NAME;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.PASSWORD;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.PHONE;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.SURNAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.pomoravskivrbaci.cinemareservations.model.UserRole;
import com.pomoravskivrbaci.cinemareservations.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTesting {
	 @Autowired
	    private UserService userService;

		@Test
		public void findAllUsers() {
	        List<User> users = userService.findAll();
	        assertNotNull(users);
	        assertTrue(!users.isEmpty());

		}

	    @Test
	    public void findUserById() {
	        User user = userService.findUserById(3L);
	        assertNotNull(user);
	    }
	    
	    @Test
	    @Transactional
	    @Rollback(true)
	    public void registerUser(){
	    	  
	    	User user=new User(EMAIL,PASSWORD,NAME,
	    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
	    	userService.createUser(user);
	    	User registered=userService.findUserById(user.getId());
	    	assertNotNull(registered);
	    	assertEquals(registered.getEmail(),user.getEmail());
	    	
	    }
	   
	   @Test
	   public void findUserByEmailAndPassword(){
		   User user=userService.findUserByEmailAndPassword(EMAIL, PASSWORD);
		   assertNotNull(user);
		   assertEquals(user.getEmail(),EMAIL);
	   }
	   @Test
	   public void findUserByNameAndSurnameIgnorableCaseContaining(){
		   List<User> users= userService.findUserByNameAndSurnameIgnorableCaseContaining(NAME, SURNAME);
		   assertNotNull(users);
		   assertNotEquals(users.size(),0);
	   }
	   @Test
	   public void findUserByNameIgnorableCaseContaining(){
		   List<User> users= userService.findUserByNameIgnorableCaseContaining(NAME);
		   assertNotNull(users);
		   assertNotEquals(users.size(),0);
	   }
	   @Test
	   public void findUserBySurnameIgnorableCaseContaining(){
		   List<User> users= userService.findUserBySurnameIgnorableCaseContaining(SURNAME);
		   assertNotNull(users);
		   assertNotEquals(users.size(),0);
	   }
	   @Test
	   @Transactional
	   @Rollback(true)
	   public void updateUser(){
			User user=userService.findUserById(ID);
		    user.setCity("Beograd");
		    User updated=userService.update(user, ID);
		    assertNotNull(updated);
		    assertEquals(updated.getCity(),"Beograd");
	   }
	   @Test
	   public void findFriends(){
			 
	   	User user=userService.findUserById(ID);
		   List<Friendship> friends= userService.findFriends(user,true);
		   assertNotNull(friends);
		   assertNotEquals(friends.size(),0);
	 
	   }
}
