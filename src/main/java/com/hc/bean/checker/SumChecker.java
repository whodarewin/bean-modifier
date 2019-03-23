package com.hc.bean.checker;


import com.google.common.collect.Lists;
import com.hc.bean.checker.param.SumCheckParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class SumChecker implements IResourceChecker {
    private static final Logger LOGGER = LoggerFactory.getLogger(SumChecker.class);
    private List<SumCheckParam> sumCheckParams;
    private BeanChecker beanChecker = new BeanChecker();

    public SumChecker(List<SumCheckParam> sumCheckParams) {
        this.sumCheckParams = sumCheckParams;
    }

    @Override
    public List<CheckResult> check(List objs) {
        List<CheckResult> ret = new ArrayList<>();
        //1 统计
        AtomicLong[] stat = new AtomicLong[sumCheckParams.size()];
        for(int i = 0; i < stat.length; i++){
            stat[i] = new AtomicLong();
        }
        objs.forEach((v) -> {

            for(int i = 0; i < sumCheckParams.size(); i ++){
                SumCheckParam param = sumCheckParams.get(i);
                try {
                    if(beanChecker.checkField(v,param.getCondition())){
                        stat[i].getAndIncrement();
                    }
                } catch (Exception e) {
                    LOGGER.error("check field error",e);
                }
            }

        });

        for(int i = 0; i < sumCheckParams.size(); i ++){
            SumCheckParam param = sumCheckParams.get(i);
            long minCount = param.getCount();
            long count = stat[i].get();
            if(count < minCount){
                ret.add( new CheckResult(false,"expected:"+param.toString() + "\n" + "real count:" + count));
            }
        }
        if(ret.size()  == 0) {
            return Lists.newArrayList(new CheckResult(true, CheckResult.SUCCESS));
        }else{
            return ret;
        }
    }
}
