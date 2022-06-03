package com.yang.jk.utils;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

/**
 * @auther yhjStart
 * @create 2022-03-28 18:43
 */
public class DateForMatter {
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface Date2Millis {}
    @Date2Millis
    public static Long date2Millis(Date date) {
        if (date==null) return null;
        return date.getTime();
    }
}
