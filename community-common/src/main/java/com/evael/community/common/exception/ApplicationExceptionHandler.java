package com.evael.community.common.exception;

import com.evael.community.common.dto.ResponseDTO;

import javax.servlet.http.HttpServletResponse;

public interface ApplicationExceptionHandler {

    Class getHandlerExceptionType();

    ResponseDTO handlerException(HttpServletResponse response, ApplicationException e);

}
