package com.pomoravskivrbaci.cinemareservations;

import static org.junit.Assert.assertEquals;
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

import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.model.UserRole;
import com.pomoravskivrbaci.cinemareservations.repository.userRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CinemareservationsApplicationTests {

    @Autowired
    private userRepository userRepository;

	@Test
	public void findAllUsers() {
        List<User> users = userRepository.findAll();
        assertNotNull(users);
        assertTrue(!users.isEmpty());

	}

    @Test
    public void findUserById() {
        User user = userRepository.findUserById(3L);
        assertNotNull(user);
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void registerUser(){
    	  
    	User user=new User("nemanja.gavrilovic1995@gmail.com","1","Mirko",
    			"Mirkovic","Beograd",false,true,UserRole.USER,"12434329");
    	userRepository.save(user);
    	User registered=userRepository.findOne(user.getId());
    	assertNotNull(registered);
    	assertEquals(registered.getEmail(),user.getEmail());
    	
    }
    
}
