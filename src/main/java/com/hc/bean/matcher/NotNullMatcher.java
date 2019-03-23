package com.hc.bean.matcher;


import com.hc.bean.annotation.Matcher;

@Matcher("notNull")
public class NotNullMatcher extends AbstractMatcher {

    @Override
    public boolean match(Object v, Object r) {
        return v != null;
    }
}
