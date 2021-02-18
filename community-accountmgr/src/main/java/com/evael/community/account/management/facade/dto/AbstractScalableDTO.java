package com.evael.community.account.management.facade.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractScalableDTO {
    @Setter @Getter
    private Map<String,Object> extraParams;

    public Object put(String key,Object value){
        if(null == extraParams){
            extraParams = new HashMap<>();
        }
        return extraParams.put(key, value);
    }

    public Object get(String key){
        if(null == extraParams){
            return null;
        }
        return extraParams.get(key);
    }
}
