package com.pomoravskivrbaci.cinemareservations.testing.controller;

import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.CITY;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.EMAIL;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.ID;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.NAME;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.PASSWORD;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.PHONE;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.SURNAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class InvitationControllerTesting {
	private static final String URL="/invitationController";
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
	@Transactional
	@Rollback(true)
	public void accept()throws Exception{
		User user=new User(ID,EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		
		 MockHttpSession mockHttpSession = new MockHttpSession(); 
		 mockHttpSession.setAttribute("loggedUser", user);
		 this.mockMvc.perform(get(URL+"/accept/1").session(mockHttpSession).requestAttr("loggedUser", user)).andExpect(status().is3xxRedirection());
	
	}
	@Test
	@Transactional
	@Rollback(true)
	public void reject()throws Exception{
		User user=new User(ID,EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		
		 MockHttpSession mockHttpSession = new MockHttpSession(); 
		 mockHttpSession.setAttribute("loggedUser", user);
		 this.mockMvc.perform(get(URL+"/reject/1").session(mockHttpSession).requestAttr("loggedUser", user)).andExpect(status().is3xxRedirection());
	
	}
}
