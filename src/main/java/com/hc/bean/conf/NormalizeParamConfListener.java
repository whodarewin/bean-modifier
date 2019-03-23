package com.hc.bean.conf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hc.bean.checker.Const;
import com.hc.bean.normalizer.param.SpecialBeanNormalizerParam;
import com.hc.bean.normalizer.parser.BeanNormalizeParamParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class NormalizeParamConfListener implements ConfListener<ThreeLevelParam> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckParamConfListener.class);

    //TODO:全局使用一个就行了
    private ObjectMapper mapper = new ObjectMapper();

    private BeanNormalizeParamParser beanParser = new BeanNormalizeParamParser();

    private ThreeLevelParam param;
    private int version;

    @Override
    public void onChange(String data) {
        LOGGER.info("change normalize param {}",data);
        if(StringUtils.isEmpty(data)){
            this.param = null;
            return;
        }
        Map<String, Map<String, Object>> params = null;
        try {
            params = mapper.readValue(data, new TypeReference<Map<String, Map<String, Object>>>() {
            });
        } catch (IOException e) {
            LOGGER.error(" parse conf error {}", data);
            LOGGER.error(" parse conf error ", e);
            return;
        }
        ThreeLevelParam param = new ThreeLevelParam();

        for (Map.Entry<String, Map<String, Object>> entry : params.entrySet()) {
            //资源key
            String resourceType = entry.getKey();
            Map<String, Object> pairs = entry.getValue();

            for (Map.Entry<String, Object> pair : pairs.entrySet()) {
                //check类型key
                String checkType = pair.getKey();
                try {
                    //check 数据
                    String value = mapper.writeValueAsString(pair.getValue());
                    switch (checkType) {
                        case Const.BEAN_NORMALIZE:
                            List<SpecialBeanNormalizerParam> beanParam = beanParser.parse(value);
                            param.put(resourceType, checkType, beanParam);
                            break;
                        default:
                            LOGGER.error("no check type defined {}", checkType);
                    }
                } catch (Exception e) {
                    LOGGER.error("parse conf error", e);
                    LOGGER.error("parse conf error {} {}", checkType, pair.getValue());
                }
            }

            this.param = param;
            version++;
        }
    }

    @Override
    public ThreeLevelParam get() {
        return param;
    }

    @Override
    public int getVersion() {
        return version;
    }

}
