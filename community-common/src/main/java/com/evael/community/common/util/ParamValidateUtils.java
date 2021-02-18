package com.evael.community.common.util;

import com.google.common.base.Preconditions;
import com.evael.community.common.exception.InvalidParamException;

/**
 * @Author jiyou
 * @Date  2016/3/28.
 */
public class ParamValidateUtils {
    public static void checkNotNull(Object reference, String errorMessage) {
        try{
            if (errorMessage!=null)
                Preconditions.checkNotNull(reference, errorMessage);
            else
                Preconditions.checkNotNull(reference);
        }catch (NullPointerException e){
            throw new InvalidParamException(errorMessage);
        }
    }

    public static void checkNotNull(Object reference) {
        checkNotNull(reference, null);
    }

    public static void checkArgument(boolean expression, String errorMessage) {
        try{
            if (errorMessage!=null)
                Preconditions.checkArgument(expression, errorMessage);
            else
                Preconditions.checkArgument(expression);
        }catch (IllegalArgumentException e){
            throw new InvalidParamException(errorMessage);
        }
    }

    public static void checkArgument(boolean expression) {
        checkArgument(expression, null);
    }
}
