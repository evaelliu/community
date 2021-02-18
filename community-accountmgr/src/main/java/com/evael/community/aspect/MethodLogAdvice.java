package com.evael.community.aspect;

import com.evael.community.common.util.ThreadLocalContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class MethodLogAdvice {

    @Before("com.evael.community.aspect.SystemArchitecture.inRestAPILayer() && args(correlationId,..)")
    public void setCorrelationId(String correlationId) {
        if (ThreadLocalContext.get(ThreadLocalContext.CORRELATION_ID) == null) {
            ThreadLocalContext.put(ThreadLocalContext.CORRELATION_ID, correlationId);
        }
    }

    @Around("com.evael.community.aspect.SystemArchitecture.inServiceLayer()")
    public Object doServiceLogActions(ProceedingJoinPoint pjp) throws Throwable {
        return doLogAction(pjp);
    }

    @Around("com.evael.community.aspect.SystemArchitecture.inInfrastructureLayer()")
    public Object doInfrastructureLogActions(ProceedingJoinPoint pjp) throws Throwable {
        return doLogAction(pjp);
    }

    private Object doLogAction(ProceedingJoinPoint pjp) throws Throwable {
        String correlationId = (String) ThreadLocalContext.get(ThreadLocalContext.CORRELATION_ID);
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String className = pjp.getTarget().getClass().getSimpleName();
        if (pjp.getArgs()==null||pjp.getArgs().length==0){
            log.info("correlationId=" + correlationId + " className:"+className+" call method:"+ method.getName());
        }else{
            for(final Object argument : pjp.getArgs()){
                log.info("correlationId=" + correlationId + " className:"+className+" call method:"+ method.getName()+" parameter:" + argument);
            }
        }
        return pjp.proceed();
    }
}
