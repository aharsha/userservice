package com.stackroute.activitystream.userservice;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.stackroute.activitystream.model.User;


@EntityScan(basePackages={"com.stackroute.activitystream.userservice"})
@ComponentScan("com.stackroute.activitystream")
@SpringBootApplication
@EnableAutoConfiguration
public class UserServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
//@Bean
//	public User getUser()
//	{
//		return new User();
//	}
	/*@Bean
	public SessionFactory getSessionFactory(HibernateEntityManagerFactory hibernateEntityManagerFactory)
	{
		
		SessionFactory sessionFactory=hibernateEntityManagerFactory.getSessionFactory();
		
		
return sessionFactory;
	}
	*/
}
