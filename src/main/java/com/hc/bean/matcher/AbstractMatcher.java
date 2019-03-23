package com.hc.bean.matcher;


import com.hc.bean.annotation.Matcher;

public abstract class AbstractMatcher<VALUE,RULE> implements IMatcher<VALUE,RULE> {

    private final String NAME;

    public AbstractMatcher() {
        Matcher matcher = this.getClass().getAnnotation(Matcher.class);
        if(matcher != null){
            this.NAME = matcher.value();
        }else {
            this.NAME = null;
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

}
