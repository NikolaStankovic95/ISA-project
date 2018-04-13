package com.pomoravskivrbaci.cinemareservations.testing.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pomoravskivrbaci.cinemareservations.TestUtil;
import com.pomoravskivrbaci.cinemareservations.constants.UserConstants;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.model.UserRole;

import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.EMAIL;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.PASSWORD;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.NAME;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.SURNAME;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.PHONE;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.CITY;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.NEW_EMAIL;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationControllerTesting {

	private static final String URL="/registrationController";
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
	public void RegistrationFail() throws Exception{
		User user=new User(EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		String json = TestUtil.json(user);
		this.mockMvc.perform(post(URL+"/register").contentType(contentType).content(json)).andExpect(status().is4xxClientError());
	}
	@Test
	@Transactional
	@Rollback(true)
	public void Registration() throws Exception{
		User user=new User(NEW_EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		String json = TestUtil.json(user);
		this.mockMvc.perform(post(URL+"/register").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	@Test
	public void Login() throws Exception{
		User user=new User(EMAIL,PASSWORD);
		String json = TestUtil.json(user);
		this.mockMvc.perform(post(URL+"/login").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	@Test
	public void LoginFail() throws Exception{
		User user=new User(EMAIL,PASSWORD);
		String json = TestUtil.json(user);
		System.out.println(json);
		this.mockMvc.perform(post(URL+"/login").contentType(contentType).content(json)).andExpect(status().is4xxClientError());
	}
	@Test
	@Transactional
	@Rollback(true)
	public void Activation()throws Exception{
		
		this.mockMvc.perform(get(URL+"/activation/1")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/Login.html"));
	}
	
}
