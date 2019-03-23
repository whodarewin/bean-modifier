package com.hc.bean;


import org.apache.commons.lang3.StringUtils;

/**
* @Description: 对path的操作工具
* @Author:         hancongcong
* @CreateDate:     18-5-28 下午7:03
* @Version:        1.0
*/
public class PathUtil {
    public static final char SPLITER = '.';
    public static final char ACTION_SPLITER = ':';

    public static String getParentPath(String path){
        if(StringUtils.isEmpty(path)){
            return null;
        }
        if(!StringUtils.contains(path,SPLITER)){
            return "";
        }
        return StringUtils.substringBeforeLast(path,String.valueOf(SPLITER));
    }

    public static String getLastPath(String path){
        if(StringUtils.isEmpty(path)){
            return null;
        }
        if(!StringUtils.contains(path,SPLITER)){
            return path;
        }
        return StringUtils.substringAfterLast(path,String.valueOf(SPLITER));
    }
}
