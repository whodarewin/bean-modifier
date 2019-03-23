package com.hc.bean.conf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hc.bean.checker.Const;
import com.hc.bean.checker.param.SpecialBeanCheckParam;
import com.hc.bean.checker.param.SumCheckParam;
import com.hc.bean.checker.parser.SpecilaBeanCheckParamParser;
import com.hc.bean.checker.parser.SumCheckParamParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//TODO:修改类名
public class CheckParamConfListener implements ConfListener<ThreeLevelParam> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckParamConfListener.class);

    //TODO:全局使用一个就行了
    private ObjectMapper mapper = new ObjectMapper();

    private SpecilaBeanCheckParamParser beanParser = new SpecilaBeanCheckParamParser();
    private SumCheckParamParser sumCheckParamParser = new SumCheckParamParser();

    private ThreeLevelParam checkParam;
    private int version = 0;

    @Override
    public void onChange(String data) {
        LOGGER.info("change check param {}",data);
        if(StringUtils.isEmpty(data)){
            checkParam = null;
            return;
        }
        Map<String,Map<String,Object>> params = null;
        try {
            params = mapper.readValue(data,new TypeReference<Map<String,Map<String,Object>>>(){});
        } catch (IOException e) {
            LOGGER.error(" parse conf error {}",data);
            LOGGER.error(" parse conf error ",e);
            return;
        }
        ThreeLevelParam checkParam = new ThreeLevelParam();

        for(Map.Entry<String,Map<String,Object>> entry : params.entrySet()){
            //资源key
            String resourceType = entry.getKey();
            Map<String,Object> pairs = entry.getValue();

            for(Map.Entry<String,Object> pair : pairs.entrySet()){
                //check类型key
                String checkType = pair.getKey();
                try {
                    //check 数据
                    String value = mapper.writeValueAsString(pair.getValue());
                    switch (checkType) {
                        case Const.BEAN_CHECK:
                            List<SpecialBeanCheckParam> beanParam = beanParser.parse(value);
                            checkParam.put(resourceType, checkType, beanParam);
                            break;
                        case Const.SUM_CHECK:
                            List<SumCheckParam> sumParam = sumCheckParamParser.parse(value);
                            checkParam.put(resourceType, checkType, sumParam);
                            break;
                        default:
                            LOGGER.error("no check type defined {}", checkType);
                    }
                }catch (Exception e){
                    LOGGER.error("parse conf error",e);
                    LOGGER.error("parse conf error {} {}",checkType,pair.getValue());
                }
            }

            this.checkParam = checkParam;
        }
        version++;
    }


    @Override
    public ThreeLevelParam get() {
        return checkParam;
    }

    public int getVersion() {
        return version;
    }
}
