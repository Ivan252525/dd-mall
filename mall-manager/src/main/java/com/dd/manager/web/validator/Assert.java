package com.dd.manager.web.validator;


import com.dd.manager.web.annotation.ParamCheck;
import com.dd.manager.web.exception.BaseException;
import com.dd.manager.web.exception.message.ErrorInfo;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据校验
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {
    
    /**
     * 字段空值校验
     * @author Shao.x
     * @date 2018年8月31日
     * @param obj
     * @throws Exception
     */
    public static void fieldCheck(Object obj) throws Exception{
        Class<?> clzss = obj.getClass(); 
        Field[] dfs = clzss.getDeclaredFields(); 
        Field[] fs = clzss.getFields();
        for (Field f : fs) { 
            f.setAccessible(true); 
            Object val = f.get(obj); 
            ParamCheck check = (ParamCheck) f.getAnnotation(ParamCheck.class);
            if(check != null && check.notNull()) {
                if (val == null) { 
                	throw new BaseException(ErrorInfo.MISS_PARAM);
                } 
            }
        }
        for (Field f : dfs) { 
            f.setAccessible(true); 
            Object val = f.get(obj); 
            ParamCheck check = (ParamCheck) f.getAnnotation(ParamCheck.class);
            if(check != null && check.notNull()) {
                if (val == null) {
                    throw new BaseException(ErrorInfo.MISS_PARAM);
                } 
            }
        }
    }

}
