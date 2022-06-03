package com.yang.jk.enhance;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * @auther yhjStart
 * @create 2022-03-28 16:38
 */
public class MyLambdaQw<T> extends LambdaQueryWrapper<T> {
    public MyLambdaQw<T> like(Object val, SFunction<T,?>... func) {
//        @SafeVarargs
//        public final MpLambdaQueryWrapper<T> like(Object val, SFunction<T, ?>... funcs) {
//            if (val == null) return this;
//            String str = val.toString();
//            if (str.length() == 0) return this;
//            return (MpLambdaQueryWrapper<T>) nested((w) -> {
//                for (SFunction<T, ?> func : funcs) {
//                    w.like(func, str).or();
//                }
//            });
//        }
        if (val==null) return this;
        String str = val.toString().trim();
        if (str.length()==0 )return this;
       return (MyLambdaQw<T>) nested(w->{
            for (SFunction<T, ?> tsFunction : func) {
                w.like(tsFunction,str).or();
            }
        });

    }
}
