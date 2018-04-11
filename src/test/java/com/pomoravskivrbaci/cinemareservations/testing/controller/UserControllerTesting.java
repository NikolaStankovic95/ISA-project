package com.pomoravskivrbaci.cinemareservations.testing.controller;

import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.CITY;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.EMAIL;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.ID;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.NAME;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.PASSWORD;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.PHONE;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.SURNAME;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.model.UserRole;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTesting {

	private static final String URL="/userController";
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	public void userProfile()throws Exception{
		this.mockMvc.perform(get(URL+"/user/"+ID)).andExpect(view().name("forward:/user_profile.jsp"));
	}
	@Test
	public void notFriends()throws Exception{
		User user=new User(ID,EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		
		 MockHttpSession mockHttpSession = new MockHttpSession(); 
		 mockHttpSession.setAttribute("loggedUser", user);
		 	 this.mockMvc.perform(get(URL+"/notFriends").session(mockHttpSession).requestAttr("loggedUser", user)).andExpect(jsonPath("$.[*].id").value(hasItem(1)));
	}
	@Test
	public void LoggedUser()throws Exception{
		User user=new User(ID,EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		
		 MockHttpSession mockHttpSession = new MockHttpSession(); 
		 mockHttpSession.setAttribute("loggedUser", user);
		 this.mockMvc.perform(get(URL+"/loggedUser").session(mockHttpSession).requestAttr("loggedUser", user)).andExpect(jsonPath("$.id").value(ID));
	
	}
	@Test
	public void checkFriendRequest()throws Exception{
		User user=new User(ID,EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		
		 MockHttpSession mockHttpSession = new MockHttpSession(); 
		 mockHttpSession.setAttribute("loggedUser", user);
		 
		 	 this.mockMvc.perform(post(URL+"/checkFriendRequest").session(mockHttpSession).requestAttr("loggedUser", user).content("6")).andExpect(jsonPath("$.friend.id").value(ID));
	}
	@Test
	public void findFriendRequests()throws Exception{
		User user=new User(ID,EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		
		 MockHttpSession mockHttpSession = new MockHttpSession(); 
		 mockHttpSession.setAttribute("loggedUser", user);
		 
		 	 this.mockMvc.perform(post(URL+"/findUserFriendRequests").session(mockHttpSession).requestAttr("loggedUser", user)).andExpect(jsonPath("$.[*].friend.id").value(hasItem(4)));
	}
	@Test
	public void findUserFriend()throws Exception{
		User user=new User(ID,EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		
		 MockHttpSession mockHttpSession = new MockHttpSession(); 
		 mockHttpSession.setAttribute("loggedUser", user);
		 
		 	 this.mockMvc.perform(post(URL+"/findUserFriends").session(mockHttpSession).requestAttr("loggedUser", user)).andExpect(jsonPath("$.[*].friend.id").value(hasItem(5)));
	}
	@Test
	@Transactional
	@Rollback(true)
	public void deleteFriend()throws Exception{
		User user=new User(ID,EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		
		 MockHttpSession mockHttpSession = new MockHttpSession(); 
		 mockHttpSession.setAttribute("loggedUser", user);
		 
		 	 this.mockMvc.perform(post(URL+"/deleteFriend").session(mockHttpSession).requestAttr("loggedUser", user).content("stefan@gmail.com")).andExpect(status().isOk());
	
	}
	@Test
	@Transactional
	@Rollback(true)
	public void acceptFriendship()throws Exception{
		User user=new User(ID,EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		
		 MockHttpSession mockHttpSession = new MockHttpSession(); 
		 mockHttpSession.setAttribute("loggedUser", user);
		 
		 	 this.mockMvc.perform(post(URL+"/addFriend").session(mockHttpSession).requestAttr("loggedUser", user).content("marko@gmail.com")).andExpect(status().isOk());
	
	}
	@Test
	@Transactional
	@Rollback(true)
	public void sendFriendRequest()throws Exception{
		User user=new User(ID,EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		
		 MockHttpSession mockHttpSession = new MockHttpSession(); 
		 mockHttpSession.setAttribute("loggedUser", user);
		 
		 	 this.mockMvc.perform(post(URL+"/sendFriendRequest/1").session(mockHttpSession).requestAttr("loggedUser", user)).andExpect(status().isOk());
	
	}
	@Test
	@Transactional
	@Rollback(true)
	public void Activation()throws Exception{
		
		this.mockMvc.perform(get(URL+"/logout")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/Login.html"));
	}
}
