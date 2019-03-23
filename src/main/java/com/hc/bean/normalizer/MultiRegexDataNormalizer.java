package com.hc.bean.normalizer;

import com.hc.bean.annotation.DataNormalizer;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@DataNormalizer("multi_regex")
public class MultiRegexDataNormalizer extends AbstractDataNormalizer<String,Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MultiRegexDataNormalizer.class);
    private static final String EXEC_NEXT_WHEN_ALL_MATCH = "execNextWhenAllMatch";
    private static final String NULL_IF_NOT_ALL_MATCH = "nullIfNotAllMatch";
    private static final String REGEX = "regex";
    private Map<String,String> f2t;
    private boolean execNextWhenAllMatch = true;
    private boolean nullIfNotAllMatch = true;

    public MultiRegexDataNormalizer(Map param) {
        this.f2t = (Map<String, String>) param.get(REGEX);
        this.execNextWhenAllMatch = param.get(EXEC_NEXT_WHEN_ALL_MATCH) == null ? true : (boolean) param.get(EXEC_NEXT_WHEN_ALL_MATCH);
        this.nullIfNotAllMatch = param.get(NULL_IF_NOT_ALL_MATCH) == null ? true : (boolean) param.get(NULL_IF_NOT_ALL_MATCH);
    }

    @Override
    public Object normalize(String bean) throws Exception {
        if(StringUtils.isEmpty(bean)){
            LOGGER.warn("null found,do nothing");
            return exec(bean);
        }
        if(MapUtils.isEmpty(f2t)){
            LOGGER.warn("multi regex Data normalizer no pattern was found,do nothing");
            return bean;
        }
        int matchCount = 0;
        for(Map.Entry<String,String> entry : f2t.entrySet()){
            Pattern pattern = Pattern.compile(entry.getKey());
            Matcher matcher = pattern.matcher(bean);
            if(matcher.find()){
                matchCount ++;
                bean = bean.replaceAll(entry.getKey(),entry.getValue());
            }
        }
        if(matchCount  == f2t.size() && execNextWhenAllMatch){
            return exec(bean);
        }else{
            if(nullIfNotAllMatch) {
                return null;
            }
            return bean;
        }


    }
}
