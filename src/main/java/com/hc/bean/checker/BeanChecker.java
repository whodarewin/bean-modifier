package com.hc.bean.checker;


import com.hc.bean.bean.getter.IValueGetter;
import com.hc.bean.checker.param.BeanCheckerParam;
import com.hc.bean.exception.MatcherNotDefinedException;
import com.hc.bean.matcher.IMatcher;
import com.hc.bean.matcher.MatcherFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BeanChecker implements Serializable {

    public boolean checkField(Object bean,BeanCheckerParam param)
            throws Exception {
        String type = param.getType();
        Object rule = param.getValue();
        IValueGetter valueGetter = param.getValueGetter();
        return checkField(bean,valueGetter,type,rule);
    }

    public boolean checkField(Object bean,IValueGetter valueGetter,String type,Object rule) throws Exception {
        IMatcher matcher = MatcherFactory.getMatcher(type);
        if(matcher == null){
            throw new MatcherNotDefinedException(type + " not defined");
        }
        Object value = valueGetter.get(bean);
        return matcher.match(value,rule);
    }
}
