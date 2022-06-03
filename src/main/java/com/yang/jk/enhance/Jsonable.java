package com.yang.jk.enhance;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @auther yhjStart
 * @create 2022-03-29 15:21
 */
public abstract class Jsonable {
    public String JsonString() throws  Exception {
        ObjectMapper mapper = new ObjectMapper();
        /**
         * 不序列化空值
         */
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(this);
    }
}
