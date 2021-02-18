package com.evael.community.aspect;

/**
 * @Author jiyou
 * @Date  2016/6/2.
 */

import com.evael.community.common.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AfterThrowingAdvice {

    @AfterThrowing(pointcut = "com.evael.community.aspect.SystemArchitecture.inInfrastructureLayer()", throwing = "ex")
    public void doInfrastructureLogActions(Exception ex) {
        if (ex instanceof ApplicationException) {
            log.error( "  system error for " + ex.getMessage(), ex);
        }else {
            log.error( " general error ", ex);
        }
    }

    @AfterThrowing(pointcut = "com.evael.community.aspect.SystemArchitecture.inServiceLayer()", throwing = "ex")
    public void doServiceLogActions(Exception ex) {
        if (ex instanceof ApplicationException) {
            log.error( "  business error for " + ex.getMessage(), ex);
        }else {
            log.error( " general error ", ex);
        }
    }
}
