package com.evael.community.aspect;

import com.evael.community.account.management.domain.exception.AccountNotExistException;
import com.evael.community.account.management.domain.exception.IncorrectUserPasswordException;
import com.evael.community.account.management.domain.exception.*;
import com.evael.community.common.errorhandle.IErrorCode;
import com.evael.community.common.dto.ResponseDTO;
import com.evael.community.common.exception.ApplicationException;
import com.evael.community.common.exception.InvalidParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Kai.Zhang on 2/17/15.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandlingControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseDTO handleError(HttpServletResponse response, Exception exception) throws Exception {
        ResponseDTO errorResponse = new ResponseDTO();
        errorResponse.setSuccess(false);
        if (exception instanceof ApplicationException) {
            if (exception instanceof AccountNotExistException){
                response.setStatus(404);
                errorResponse.setMessage("account not exist");
                errorResponse.setErrorCode(IErrorCode.ERROR_CODE_USER_NOT_EXIST);
                log.error(exception.toString());
            }
            if (exception instanceof IncorrectUserPasswordException){
                response.setStatus(401);
                errorResponse.setMessage("incorrect password");
                errorResponse.setErrorCode(IErrorCode.ERROR_CODE_CHANGE_PASSWORD_FAILED);
            }
            if (exception instanceof InvalidParamException){
                response.setStatus(400);
                errorResponse.setMessage(exception.toString());
                log.error("invalid api param :" + ((InvalidParamException) exception).getErrorMessage());
            }
        }else{
            response.setStatus(500);//internal server error
            log.error("Ops!", exception);
			errorResponse.setErrorCode(500);
            errorResponse.setMessage(exception.getMessage());
        }
        return errorResponse;
    }
}
