package com.stackroute.activitystream.userservice;



import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.activitystream.dao.UserDao;
import com.stackroute.activitystream.model.User;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;


import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserServiceApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT,classes=UserServiceApplication.class)
public class UserControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	@Mock
	UserDao userDao;
		
	@InjectMocks
	private UserController userController;
	@Before
	public void initMock() throws Exception 
	{
		
	  //  this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		
		//this.mockMvc=MockMvcBuilders.standaloneSetup(UserController.class).build();
		this.mockMvc=MockMvcBuilders.standaloneSetup(userController).build();
	}
//================================================================
	//@Test
	public void addUsersTestCase()throws Exception
	{
		
	    this.mockMvc.perform(post("http://localhost:3001/addUser")).andExpect(status().isOk());
	    	}
	
//================================================================	
	//@Test
	public void getAllUsersTestCase()throws Exception
	{
	    this.mockMvc.perform(get("http://localhost:3001/getAllUsers")).andExpect(status().isOk());
	    	}
//=================================================================
	//@Test
	public void getUserTestCase()throws Exception
	{
	    this.mockMvc.perform(get("http://localhost:3001/getUser/harsha@gmail.com")).andExpect(status().isOk());
	    	}
	
//	==========================
	
	//@Test
    public void testCaseForGetUser()throws Exception
    {
          User user=new User("dimple@gmail.com","pass@1234","Dimple Baid","9998881213");
          /*user.setEmail_id("vinu@gmail.com");
          user.setMobile("8991112321");
          user.setUsername("Vinu Kumar");
          user.setPassword("you@12345");*/
         
           when(userDao.getUserWithId("dimple@gmail.com")).thenReturn(user);
         
           this.mockMvc.perform(get("/getUser/dimple@gmail.com")).andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
          .andExpect(jsonPath("$.email_id", is("dimple@gmail.com")))
      .andExpect(jsonPath("$.mobile", is("9998881213")));
         
    }
  
	//=========
	@Test
	public void getUserTestCaseWithJson()throws Exception
	{
		 User user=new User("harsha@gmail.com","Harsha","9652983089","harSHA@12345");
         
//	 User user=new User();
//		 user.setEmail("harsha@gmail.com");
//		 user.setMobile("9652983089");
//		 user.setUsername("Harsha");
//		 user.setPassword("harSHA@12345");
		 
		 when(userDao.getUserWithId("harsha@gmail.com")).thenReturn(user);
		 
		 
		
		 this.mockMvc.perform(get("/user/getUser/harsha@gmail.com.")).andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		 .andExpect(jsonPath("$.email", is("harsha@gmail.com")))
         .andExpect(jsonPath("$.mobile", is("9652983089")));
		
	}
//===================================================================	
	//@Test
	public void login_PositiveTestCase() throws Exception {
		 User user=new User("dimple@gmail.com","pass@1234","Dimple Baid","9998881213");
	        user.setEmail("harsha@gmail.com");
		user.setPassword("harSHA@12345");
		
		mockMvc.perform(post("http://localhost:3001/login").content(asJsonString(user)).contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
	}
	
	
	//@Test
		public void login_NegativeTestCase() throws Exception {
			 User user=new User("dimple@gmail.com","pass@1234","Dimple Baid","9998881213");
		        user.setEmail("harsha@gmail.com");
			user.setPassword("harSHA@12334567");
			
			mockMvc.perform(post("http://localhost:3001/login").content(asJsonString(user)).contentType(MediaType.APPLICATION_JSON)
					  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
			
		}
	
	private static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	      
	        return mapper.writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
	
}
