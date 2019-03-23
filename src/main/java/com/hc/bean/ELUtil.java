package com.hc.bean;

public class ELUtil {
    public static String formatEL(String elExpress){
        if(elExpress.startsWith("${")){
            elExpress = elExpress.substring(2,elExpress.length());
        }
        if(elExpress.endsWith("}")){
            elExpress = elExpress.substring(0,elExpress.length()-1);
        }
        return elExpress;
    }

    public static String formatSPEL(String elExpress){
        if(elExpress.startsWith("#{")){
            elExpress = elExpress.substring(2,elExpress.length());
        }
        if(elExpress.endsWith("}")){
            elExpress = elExpress.substring(0,elExpress.length()-1);
        }
        return elExpress;
    }
}
