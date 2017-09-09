package com.stackroute.activitystream.userservice;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.dao.UserDao;
import com.stackroute.activitystream.model.User;




@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=UserServiceApplication.class)
public class UserServiceApplicationTests {

	@Autowired
	UserDao userDao;
	
	
	
	
	//@Test
	public void addUserSuccessTestCase() 
	{

		 User user=new User();
		user.setEmail("baba@gmail.com");
		user.setMobile("9652983089");
user.setPassword("babaBABA@1234");

user.setUsername("baba");
userDao.addUser(user);
	}

	public void addUserFailTestCase() 
	{

		 User user=new User();
		user.setEmail("baba@gmail.com");
		user.setMobile("9652983089");
user.setPassword("babaBABA@1234");

user.setUsername("baba");
userDao.addUser(user);
	}

	
	//@Test
	public void deleteUserSuccessTestCase() 
	{
		assertEquals("success",true,userDao.deleteUser("radha@gmail.com"));

	}
	//@Test
	public void deleteUserFailureTestCase() 
	{

		assertEquals("success",false,userDao.deleteUser("xyz@gmail.com"));

	}

	

@Test
public void getAllUsersTestCase() 
{

	 
List<User> users= userDao.getAllUsers();

		for (User user : users)
		{
			System.out.println("user name  = "+user.getEmail());
		}
}

//@Test
public void validateUserSuccessTestCase() 
{
assertEquals("success",true,userDao.validateUser("harsha@gmail.com","harSHA@12345"));

	
}

//@Test
public void validateUserFailureTestCase() 
{
assertEquals("success",false,userDao.validateUser("harsha@gmail.com","harsha@1235"));

	
}





//@Test
public void getUserWithIdTestCase() 
{

	 
User user= userDao.getUserWithId("harsha@gmail.com");

assertEquals("success","harsha@gmail.com",user.getEmail());
			
		
}
}


