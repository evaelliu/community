package com.evael.community.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Author jiyou
 * @Date  2016/6/11.
 */
@Aspect
public class SystemArchitecture {

    @Pointcut("within(com.evael.community.account.management.facade.rest..*)")
    public void inRestAPILayer() {
    }

    @Pointcut("within(com.evael.community.account.management.service..*..*..*)")
    public void inServiceLayer() {
    }

    @Pointcut("within(com.evael.community.account.management..*..*..*)")
    public void inInfrastructureLayer() {
    }
}

