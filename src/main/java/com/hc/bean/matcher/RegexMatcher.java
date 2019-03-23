package com.hc.bean.matcher;


import com.hc.bean.annotation.Matcher;

import java.util.regex.Pattern;

@Matcher("regex")
public class RegexMatcher extends AbstractMatcher<String,String> {
    @Override
    public boolean match(String v, String r) {
        if(r == null){
            throw new RuntimeException("regex is null");
        }
        if(v == null){
            throw new RuntimeException("regex match v is null");
        }
        Pattern matcher = Pattern.compile(r);
        return matcher.matcher(v).matches();
    }
}
