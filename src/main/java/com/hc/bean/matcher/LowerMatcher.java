package com.hc.bean.matcher;


import com.hc.bean.annotation.Matcher;

@Matcher("lower")
public class LowerMatcher<VALUE extends Comparable,RULE extends Comparable> extends AbstractMatcher<VALUE,RULE> {

    @Override
    public boolean match(VALUE v, RULE r) {
        if(v == null && r == null){
            return false;
        }else if(v == null || r == null){
            throw new RuntimeException("null can not compare" + v.toString() + r.toString());
        }
        return v.compareTo(r) < 0;
    }
}
