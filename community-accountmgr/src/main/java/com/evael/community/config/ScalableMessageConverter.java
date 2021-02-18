package com.evael.community.config;

import com.alibaba.fastjson.JSON;
import com.evael.community.account.management.facade.dto.AbstractScalableDTO;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ScalableMessageConverter extends AbstractHttpMessageConverter<AbstractScalableDTO> {


    public ScalableMessageConverter() {
        super(MediaType.ALL);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // 表明只处理AbstractScalableDTO类型的参数。
        return AbstractScalableDTO.class.isAssignableFrom(clazz);
    }

    /**
     * 重写readlntenal 方法，处理请求的数据。代码表明我们处理由“-”隔开的数据，并转成 AbstractScalableDTO类型的对象。
     */
    @Override
    protected AbstractScalableDTO readInternal(Class<? extends AbstractScalableDTO> clazz,
                                               HttpInputMessage inputMessage) throws IOException,
            HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
        AbstractScalableDTO abstractHashMapDTO = JSON.parseObject(temp,clazz);
        Map map  = JSON.parseObject(temp);
        Method[] methods = clazz.getMethods();
        for (Method method : methods){
            String methodName = method.getName();
            if(methodName.startsWith("set")){
                String field = methodName.substring(3);
                field = field.toLowerCase().charAt(0) + field.substring(1);
                map.remove(field);
            }
        }
        abstractHashMapDTO.setExtraParams(map);
        return abstractHashMapDTO;
    }

    /**
     * 重写writeInternal ，处理如何输出数据到response。
     */
    @Override
    protected void writeInternal(AbstractScalableDTO abstractHashMapDTO,
                                 HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        Map<String, Object> res = new HashMap<>();
        res.putAll(abstractHashMapDTO.getExtraParams());
        Method[] methods = abstractHashMapDTO.getClass().getMethods();
        for (Method method : methods){
            String methodName = method.getName();
            if(methodName.startsWith("get")){
                String field = methodName.substring(3);
                field = field.toLowerCase().charAt(0) + field.substring(1);
                try {
                    Object o = method.invoke(abstractHashMapDTO);
                    if(null!=o){
                        res.put(field,o);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        outputMessage.getBody().write(JSON.toJSONBytes(res));
    }
}
