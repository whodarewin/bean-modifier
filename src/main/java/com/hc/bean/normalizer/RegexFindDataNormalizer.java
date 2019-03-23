package com.hc.bean.normalizer;


import com.hc.bean.annotation.DataNormalizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@DataNormalizer("regex_find")
public class RegexFindDataNormalizer extends AbstractDataNormalizer<String,Object> {
    private String pattern ;

    public RegexFindDataNormalizer(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Object normalize(String bean) throws Exception {
        if(bean == null){
            return exec(bean);
        }
        Pattern pattern = Pattern.compile(this.pattern);
        Matcher matcher = pattern.matcher(bean);
        if(matcher.find()){
            return exec(matcher.group());
        }else{
            //find nothing
            return exec(null);
        }
    }
}
