package com.hc.bean.normalizer;

import com.google.common.collect.Table;
import com.hc.bean.checker.Const;
import com.hc.bean.conf.IConfigrationSource;
import com.hc.bean.conf.NormalizeParamConfListener;
import com.hc.bean.conf.ThreeLevelParam;
import com.hc.bean.normalizer.param.SpecialBeanNormalizerParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description:    根据resource的type获得normalizer
* @Author:         hancongcong
* @CreateDate:     18-5-29 下午4:35
* @Version:        1.0
*/
public class NormalizerFactory implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(NormalizerFactory.class);
    private NormalizeParamConfListener listener = new NormalizeParamConfListener();
    private Map<String,INormalizer> type2checker = new HashMap<>();
    private int version;
    public NormalizerFactory(IConfigrationSource source) {
        init(source);
    }
    private void init(IConfigrationSource source) {
        source.addListener(listener);

        ThreeLevelParam checkParam = listener.get();
        this.version = listener.getVersion();
        if(checkParam == null){
            return;
        }
        reset(checkParam);

    }

    private void reset(ThreeLevelParam checkParam){
        Table<String,String,Object> param = checkParam.getCheckParam();
        for(Map.Entry<String,Map<String,Object>> entry : param.rowMap().entrySet()){
            String resourceKey = entry.getKey();
            for(Map.Entry<String,Object> inner : entry.getValue().entrySet()){
                String checkType = inner.getKey();
                INormalizer normalizer = getNormalizer(checkType,inner.getValue());
                type2checker.put(resourceKey,normalizer);
            }
        }
    }

    private INormalizer getNormalizer(String checkType, Object value) {
        switch (checkType){
            case Const.BEAN_NORMALIZE:
                return new DefaultNormalizer((List<SpecialBeanNormalizerParam>) value);
            default :
                LOGGER.error("no checker defined {}",checkType);
                return null;
        }
    }

    public INormalizer getNormalizer(String resourceType){
        if(version != listener.getVersion()){
            reset(listener.get());
        }
        return type2checker.get(resourceType);
    }
}
