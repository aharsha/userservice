
//Author Harsha
//controller for User Module
package com.stackroute.activitystream.userservice;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.activitystream.dao.UserDao;
import com.stackroute.activitystream.model.User;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.Link;

@RestController
@RequestMapping("/user")
@EnableWebMvc

public class UserController {
	
	
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private HttpSession httpSession;
	
	
	//========================================================================
	@PostMapping("/addUser")
	public ResponseEntity<User> addNewUser(@RequestBody User user)
	{
		
		try
		{
			
		boolean registerStatus=userDao.addUser(user);
		if(registerStatus==true)
		{
		return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<User>(user,HttpStatus.NOT_ACCEPTABLE);
		}
		}
		
		catch(Exception exception )
		{
			
			return new ResponseEntity<User>(user,HttpStatus.NOT_ACCEPTABLE);
				
		}
		}
	
	//=================================================================
			@PostMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		
		try
		{
			
		boolean registerStatus=userDao.updateUser(user);
		if(registerStatus==true)
		{
		return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
		}
		}
		
		catch(Exception exception )
		{
			
			return new ResponseEntity<User>(user,HttpStatus.NOT_ACCEPTABLE);
				
		}
		}
	
			
			
	//=========================================================
			
			
			@PostMapping("/deleteUser/{email}")
			public ResponseEntity<String> deleterUser(String email)
			{
				
				try
				{
					
				boolean deleterStatus=userDao.deleteUser(email);
				if(deleterStatus==true)
				{
					return new ResponseEntity<String>("deleted successfully",HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<String>("Not deleted successfully",HttpStatus.OK);
					}
				}
				
				catch(Exception exception )
				{
					
					return new ResponseEntity<String>("error",HttpStatus.NOT_ACCEPTABLE);
						
				}
				}
			
					
			
			
	//Applying hateous ->add user email as link to each user
	//=============================================
	@GetMapping("/getAllUsers")
	public List getAllUser() {
		
		List<User> allUsers=userDao.getAllUsers();
		for(User user:allUsers)
		{
			Link link=linkTo(UserController.class).slash(user.getEmail()).withSelfRel();
			user.add(link);
		}
		return allUsers;
	}
	
	//==================================================
	@GetMapping("/getUser/{email}")
	public ResponseEntity<User> getUserWithId(@PathVariable("email") String email) {
		
		User user;
		System.out.println(email+"at controller");
		
		 user=userDao.getUserWithId(email);
		if(user!=null)
		{
		return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<User>(user,HttpStatus.NO_CONTENT);
		}
		}
		

	
	
	
	//====================================================================
	
	//This method should return user home object.
	//No use if it returns just login success OR failed.
	//Once UserHome object created use it as return type.
	  @PostMapping("/login")
		public ResponseEntity<String> login(@RequestBody User user)
	{
		  
		boolean login=userDao.validateUser(user.getEmail(),user.getPassword());
		
		if(login==true)
		{
			
			httpSession.setAttribute("login",true);
			httpSession.setAttribute("email",user.getEmail());
			
		return new ResponseEntity<String>("login success",HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("login failed",HttpStatus.UNAUTHORIZED);
	}
	
//==========================================================================	
	/*
	  @GetMapping("/logout/{email}")
	public ResponseEntity<Boolean> logout(HttpServletResponse res,@PathVariable("email") String email) {

		//httpSession.invalidate();

		
		 final String SECRET = "ThisIsASecret";
		   final String TOKEN_PREFIX = "Bearer";
		   final String HEADER_STRING = "Authorization";
		String JWT = Jwts.builder()
		        .setSubject(email)
		        .setExpiration(new Date(System.currentTimeMillis()))
		        .signWith(SignatureAlgorithm.HS512, SECRET)
		        .compact();
		    res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	
		
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}
		*/
 
	  /*
	  @GetMapping("/logout")
		public ResponseEntity<Boolean> logout() {

			
			
			return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
		}
*/
	  
	  
}
