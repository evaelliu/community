package com.evael.community.account.management.domain.exception;

import com.evael.community.common.exception.ApplicationException;

public class TenantNotExistException extends ApplicationException {
    String tenantCode;
    public TenantNotExistException(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    @Override
    public String toString() {
        return "TenantNotExistException{" +
                "tenantCode='" + tenantCode + '\'' +
                '}';
    }
}
