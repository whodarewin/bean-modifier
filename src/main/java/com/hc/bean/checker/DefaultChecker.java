package com.hc.bean.checker;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class DefaultChecker implements IResourceChecker {
    private List<IResourceChecker> resourceCheckers = new ArrayList<>();

    public void register(IResourceChecker checker){
        resourceCheckers.add(checker);
    }
    @Override
    public List<CheckResult> check(List videoMetas) {
        List<CheckResult> ret = new ArrayList<>();
        for(IResourceChecker resourceChecker : resourceCheckers){
            List<CheckResult> result = resourceChecker.check(videoMetas);
            if(result.size() == 1 && result.get(0).isPass()){
                continue;
            }else{
                ret.addAll(result);
            }
        }
        if(ret.size() == 0) {
            return Lists.newArrayList(new CheckResult(true, CheckResult.SUCCESS));
        }else{
            return ret;
        }
    }

    public List<IResourceChecker> getResourceCheckers() {
        return resourceCheckers;
    }

    public void setResourceCheckers(List<IResourceChecker> resourceCheckers) {
        this.resourceCheckers = resourceCheckers;
    }
}
