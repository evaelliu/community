package com.evael.community.common.exception;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.evael.community.common.dto.ResponseDTO;
import com.evael.community.common.errorhandle.IErrorCode;

@Component
@Slf4j
public class InvalidParamExceptionHandler extends DefaultApplicationExceptionHandler {
	@Override
	public Class getHandlerExceptionType() {
		return InvalidParamException.class;
	}

	@Override
	public ResponseDTO handlerException(HttpServletResponse response, ApplicationException e) {
		ResponseDTO errorResponse = new ResponseDTO();
		errorResponse.setSuccess(false);
		response.setStatus(400);
		errorResponse.setErrorCode(IErrorCode.ERROR_CODE_INVALID_PARAMETER);
		InvalidParamException invalidException = (InvalidParamException) e;
		errorResponse.setMessage(invalidException.getErrorMessage());
		log.error("invalid api param :" + e);
		return errorResponse;
	}
}
