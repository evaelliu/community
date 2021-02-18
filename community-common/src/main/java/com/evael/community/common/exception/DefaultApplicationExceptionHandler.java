package com.evael.community.common.exception;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class DefaultApplicationExceptionHandler implements ApplicationExceptionHandler{

	@Autowired
	ExceptionHandlerContext exceptionHandlerContext;
	
	@PostConstruct
	protected void init(){
		exceptionHandlerContext.registry(getHandlerExceptionType(), this);
	}
}
