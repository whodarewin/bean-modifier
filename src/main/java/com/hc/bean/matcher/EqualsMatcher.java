package com.hc.bean.matcher;


import com.hc.bean.annotation.Matcher;

@Matcher("equals")
public class EqualsMatcher <VALUE extends Comparable,RULE extends Comparable> extends AbstractMatcher<VALUE,RULE> {

    @Override
    public boolean match(VALUE v, RULE r) {
        if(v == null && r ==null){
            return true;
        }else if(v == null || r == null){
            return false;
        }else {
            return v.compareTo(r) == 0;
        }
    }
}
