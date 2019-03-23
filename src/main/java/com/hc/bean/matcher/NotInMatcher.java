package com.hc.bean.matcher;


import com.hc.bean.annotation.Matcher;

import java.util.Collection;

@Matcher("notIn")
public class NotInMatcher<VALUE,RULE extends Collection> extends AbstractMatcher<VALUE,RULE> {

    @Override
    public boolean match(VALUE v, RULE r) {
        return !r.contains(v);
    }
}
