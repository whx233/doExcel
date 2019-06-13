package com.myexcel.doExcel.util;

import java.util.HashMap;

/**
 * 数据传输对象
 */
public class HashDto extends HashMap {
    private int code;
    private String message;
    private static HashDto hashDto = null;

    public static HashDto init(){
        hashDto = new HashDto();
        return hashDto;
    }

    public static HashDto init(String key,Object value){
        hashDto = new HashDto();
        hashDto.put(key,value);
        return hashDto;
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.put("code",code);
        this.code = code;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.put("message",message);
        this.message = message;
    }
}
