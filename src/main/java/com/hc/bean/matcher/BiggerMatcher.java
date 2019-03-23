package com.hc.bean.matcher;


import com.hc.bean.annotation.Matcher;

@Matcher("bigger")
public class BiggerMatcher<VALUE extends Comparable,RULE extends Comparable> extends AbstractMatcher<VALUE,RULE> {

    @Override
    public boolean match(VALUE v, RULE r) {
        if(v == null && r == null){
            return false;
        }else if(v == null || r == null){
            throw new RuntimeException("null can not compare" + v +"-"+ r);
        }
        return v.compareTo(r) > 0;
    }
}
