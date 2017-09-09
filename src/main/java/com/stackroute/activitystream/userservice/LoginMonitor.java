package com.stackroute.activitystream.userservice;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Aspect
@Component
public class LoginMonitor {
	public LoginMonitor() {
		
	}

	 private Logger logger = Logger.getLogger(getClass().getName());
	 @Before("execution(public * login(*))")
	    public void callBeforeLogin(JoinPoint joinPoint) {
	        logger.info("before Login======================================================");
	        logger.info("Method Invoked: " + joinPoint.getSignature().getName());
	        logger.info("Value Passed: " + joinPoint.getArgs()[0]);
	    }

	 @After("execution(public * login(*))")
	 public void callAfterLogin(JoinPoint joinPoint)  {
		 logger.info("After Login");
	        logger.info("Method Invoked: " + joinPoint.getSignature().getName());
	        logger.info("Value Passed: " + joinPoint.getArgs()[0]);

	 }
}
