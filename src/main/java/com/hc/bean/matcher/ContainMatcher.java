package com.hc.bean.matcher;


import com.hc.bean.annotation.Matcher;

import java.util.Collection;

@Matcher("contain")
public class ContainMatcher<VALUE,RULE> extends AbstractMatcher<VALUE,RULE> {

    @Override
    public boolean match(VALUE v, RULE r) {
        if(v == null){
            return false;
        }
        if(v instanceof String){
            return ((String) v).contains(r.toString());
        }else if(v instanceof Collection){
            return ((Collection) v).contains(r);
        }
        //TODO:自定义Exception
        throw new RuntimeException("class not support contain :"+v.getClass().getName());
    }
}
