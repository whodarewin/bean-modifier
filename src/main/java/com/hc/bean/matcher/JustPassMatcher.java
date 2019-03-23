package com.hc.bean.matcher;


import com.hc.bean.annotation.Matcher;

@Matcher("pass")
public class JustPassMatcher extends AbstractMatcher {

    @Override
    public boolean match(Object v, Object r) {
        return true;
    }
}
