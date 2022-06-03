package com.yang.jk.utils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @auther yhjStart
 * @create 2022-03-27 21:47
 */
public class ConvertUtil {
    public static <T,R> List<R> map(List<T> list, Function<T,R> fuc) {
        return list.stream().map(fuc).collect(Collectors.toList());
    }
}
