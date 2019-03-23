package com.hc.bean.checker;

import com.google.common.collect.Table;
import com.hc.bean.checker.param.SpecialBeanCheckParam;
import com.hc.bean.checker.param.SumCheckParam;
import com.hc.bean.conf.CheckParamConfListener;
import com.hc.bean.conf.IConfigrationSource;
import com.hc.bean.conf.ThreeLevelParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获得不同类别的资源的check逻辑
 */
public class ResourceCheckFactory{
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceCheckFactory.class);

    private CheckParamConfListener listener = new CheckParamConfListener();
    private int version;
    private Map<String,DefaultChecker> type2checker = new HashMap<>();
    public ResourceCheckFactory(IConfigrationSource source) {
        init(source);
    }
    private void init(IConfigrationSource source) {

        source.addListener(listener);
        //2 生成每个资源的checker
        ThreeLevelParam checkParam = listener.get();
        version = listener.getVersion();
        if(checkParam == null){
            LOGGER.warn("check param is null");
            return;
        }
        resetChecker(checkParam);
    }

    private void resetChecker(ThreeLevelParam checkParam){
        Table<String,String,Object> param = checkParam.getCheckParam();
        for(Map.Entry<String,Map<String,Object>> entry : param.rowMap().entrySet()){
            String resourceKey = entry.getKey();
            DefaultChecker defaultChecker = new DefaultChecker();
            for(Map.Entry<String,Object> inner : entry.getValue().entrySet()){
                String checkType = inner.getKey();
                IResourceChecker checker = getChecker(checkType,inner.getValue());
                if(checker != null) {
                    defaultChecker.register(checker);
                }
            }
            type2checker.put(resourceKey,defaultChecker);
        }
    }

    private IResourceChecker getChecker(String checkType, Object value) {
        switch (checkType){
            case Const.BEAN_CHECK:
                return new PropertyChecker((List<SpecialBeanCheckParam>) value);
            case Const.SUM_CHECK:
                return new SumChecker((List<SumCheckParam>) value);
            default :
                LOGGER.error("no checker defined {}",checkType);
            return null;
        }
    }

    public IResourceChecker getResourceChecker(String type){
        if(version != listener.getVersion()){
            resetChecker(listener.get());
        }
        return type2checker.get(type);
    }
}
