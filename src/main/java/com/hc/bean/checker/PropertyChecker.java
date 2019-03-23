package com.hc.bean.checker;

import com.google.common.collect.Lists;
import com.hc.bean.checker.param.BeanCheckerParam;
import com.hc.bean.checker.param.SpecialBeanCheckParam;
import com.hc.bean.exception.MatcherNotDefinedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PropertyChecker implements IResourceChecker {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyChecker.class);
    private BeanChecker checker = new BeanChecker();

    private List<SpecialBeanCheckParam> specialBeanCheckParams;

    public PropertyChecker(List<SpecialBeanCheckParam> specialBeanCheckParams) {
        this.specialBeanCheckParams = specialBeanCheckParams;
    }

    @Override
    //TODO:处理异常
    public List<CheckResult> check(List objs)  {
        List<CheckResult> ret = new ArrayList<>();
        for(Object obj : objs){
            for(SpecialBeanCheckParam param : specialBeanCheckParams) {

                if (param == null) {
                    continue;
                } else {
                    try {
                        if(checker.checkField(obj,param.getCondition())) {
                            List<BeanCheckerParam> actions = param.getActions();
                            for(BeanCheckerParam action : actions) {
                                if (checker.checkField(obj, action)) {
                                    continue;
                                } else {
                                    ret.add(new CheckResult(false, "field:" + action.getPath()
                                                + " bean value:" + action.getValueGetter().get(obj) + " value:" + action.getValue() + " type:" + action.getType()
                                                + " not match"));
                                    LOGGER.warn("check failed,bean {} action {}",obj,action);
                                }
                            }
                        }
                    }  catch (MatcherNotDefinedException e) {
                        LOGGER.error("matcher not defined",e);
                        LOGGER.error("matcher not defined {}",e.getMessage());
                        ret.add(new CheckResult(false, "matcher " + e.getMessage() + "not defined"));
                    } catch (Exception e) {
                        LOGGER.error("check exception ",e);
                        ret.add(new CheckResult(false, "exception:" + e.getMessage() + "\n"+ obj));
                    }
                }

            }
        }
        if(ret.size() == 0){
            return Lists.newArrayList(new CheckResult(true,CheckResult.SUCCESS));
        }else{
            return ret;
        }

    }




}
