package com.evael.community.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ExceptionHandlerContext {
	private Map<Class,ApplicationExceptionHandler > exceptionClass2Handler;
	
	public void registry(Class exceptionClass, ApplicationExceptionHandler handler){
		if(exceptionClass2Handler==null){
			exceptionClass2Handler= new HashMap<>();
		}
		exceptionClass2Handler.put(exceptionClass, handler);
	}
	
	public ApplicationExceptionHandler getHandler(Class exceptionClass){
		return exceptionClass2Handler.get(exceptionClass);
	}

	public boolean hasHandler(Class exceptionClass) {
		return exceptionClass2Handler.containsKey(exceptionClass);
	}
}
