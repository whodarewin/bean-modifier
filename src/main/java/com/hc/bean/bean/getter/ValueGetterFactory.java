package com.hc.bean.bean.getter;

import org.apache.commons.lang3.StringUtils;

public class ValueGetterFactory {

    public static IValueGetter getValueGetter(String config) {
        if(StringUtils.isEmpty(config)){
            return SelfValueGetter.INSTANCE;
        }
        config = config.trim();
        //1 查看是以什么开头
        //2 如果是以foreach开头，则截取到.
        //3 如果是以EL开头，则截取到}
        //4 如果是以map开头，则截取到.
        IValueGetter headGetter = null;
        IValueGetter tailGetter = null;
        while(config != null){
            if(config.startsWith("foreach")){
                int index = config.indexOf(".");
                if(index == -1){
                    index = config.length();
                }
                String pattern = config.substring(0,index);
                String[] foreachConfig = StringUtils.split(pattern, ':');

                IValueGetter nextF = new CollectionValueGetter(foreachConfig[1]);
                if (headGetter == null) {
                    headGetter = nextF;
                } else {
                    tailGetter.setNext(nextF);
                }
                tailGetter = nextF;

                if(index == config.length()){
                    config = null;
                }else {
                    config = config.substring(index + 1);
                }

            }else if(config.startsWith("EL")){
                int index = config.indexOf("}");
                if(index == -1){
                    throw new RuntimeException("EL must close with },expression is "+ config);
                }
                String pattern  = config.substring(0,index +1);
                String[] elConfig = StringUtils.split(pattern,":");

                IValueGetter nextEL = new ELValueGetter(elConfig[1]);
                if (headGetter == null) {
                    headGetter = nextEL;
                } else {
                    tailGetter.setNext(nextEL);
                }
                tailGetter = nextEL;

                if(index == config.length()-1){
                    config = null;
                }else{
                    config = config.substring(index + 2);
                }
            }else if(config.startsWith("SPEL")){
                int index = config.indexOf("@}");
                if(index == -1){
                    throw new RuntimeException("SPEL must close with @},expression is "+ config);
                }
                String pattern  = config.substring(0,index + 2);
                String[] elConfig = StringUtils.split(pattern,":");

                IValueGetter nextEL = new SPELValueGetter(elConfig[1]);
                if (headGetter == null) {
                    headGetter = nextEL;
                } else {
                    tailGetter.setNext(nextEL);
                }
                tailGetter = nextEL;

                if(index == config.length()-2){
                    config = null;
                }else{
                    config = config.substring(index + 3);
                }
            }else if(config.startsWith("map")){
                int index = config.indexOf(".");
                if(index == -1){
                    index = config.length();
                }
                String pattern = config.substring(0,index);
                String[] mapConfig = StringUtils.split(pattern, ':');

                IValueGetter nextM = new MapValueGetter(mapConfig[1]);
                if (headGetter == null) {
                    headGetter = nextM;
                } else {
                    tailGetter.setNext(nextM);
                }
                tailGetter = nextM;

                if(index == config.length()){
                    config = null;
                }else{
                    config = config.substring(index + 1);
                }
            }else{
                int index = config.indexOf(".");
                if(index == -1){
                    index = config.length();
                }
                String pattern = config.substring(0,index);

                IValueGetter nextM = new SimpleValueGetter(pattern);
                if (headGetter == null) {
                    headGetter = nextM;
                } else {
                    tailGetter.setNext(nextM);
                }
                tailGetter = nextM;

                if(index == config.length()){
                    config = null;
                }else{
                    config = config.substring(index +1);
                }
            }
        }
        return headGetter;
    }
}
