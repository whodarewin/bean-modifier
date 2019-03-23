package com.hc.bean.matcher;


import com.hc.bean.annotation.Matcher;

@Matcher("notEquals")
public class NotEqualsMatcher<VALUE extends Comparable,RULE extends Comparable> extends AbstractMatcher<VALUE,RULE> {

    @Override
    public boolean match(VALUE v, RULE r) {
        return v.compareTo(r) != 0;
    }

}
