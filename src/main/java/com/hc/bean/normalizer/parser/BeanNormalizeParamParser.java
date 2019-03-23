package com.hc.bean.normalizer.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.hc.bean.IParser;
import com.hc.bean.bean.getter.IValueGetter;
import com.hc.bean.bean.getter.ValueGetterFactory;
import com.hc.bean.bean.setter.IValueSetter;
import com.hc.bean.bean.setter.ValueSetterFactory;
import com.hc.bean.checker.param.BeanCheckerParam;
import com.hc.bean.normalizer.DataNormalizerFactory;
import com.hc.bean.normalizer.IDataNormalizer;
import com.hc.bean.normalizer.param.BeanNormalizeParam;
import com.hc.bean.normalizer.param.BeanNormalizeParamModel;
import com.hc.bean.normalizer.param.SpecialBeanNormalizerParam;
import com.hc.bean.normalizer.param.SpecialBeanNormalizerParamModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeanNormalizeParamParser implements IParser<List<SpecialBeanNormalizerParam>> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<SpecialBeanNormalizerParam> parse(String value) throws IOException {
        List<SpecialBeanNormalizerParamModel> models = objectMapper.readValue(value,new TypeReference<List<SpecialBeanNormalizerParamModel>>(){});

        List<SpecialBeanNormalizerParam> ret = new ArrayList<>(models.size());
        for(SpecialBeanNormalizerParamModel model : models){
            List<BeanNormalizeParam> actions = getBeanNormalizeParams(model.getActions());
            BeanCheckerParam condition = BeanCheckerParam.of(model.getCondition());
            SpecialBeanNormalizerParam param = new SpecialBeanNormalizerParam();
            param.setActions(actions);
            param.setCondition(condition);
            ret.add(param);
        }

        return ret;
    }

    private List<BeanNormalizeParam> getBeanNormalizeParams(List<BeanNormalizeParamModel> actions) {
        List<BeanNormalizeParam> ret = Lists.newArrayList();
        for(BeanNormalizeParamModel model : actions){
            ret.add(getBeanNormalizeParam(model));
        }
        return ret;
    }

    private BeanNormalizeParam getBeanNormalizeParam(BeanNormalizeParamModel param) {
        IValueGetter valueGetter = ValueGetterFactory.getValueGetter(param.getFromPath());

        IValueSetter setter = ValueSetterFactory.create(param.getToPath());

        IDataNormalizer normalizer = DataNormalizerFactory.createNormalizer(param);

        BeanNormalizeParam ret = new BeanNormalizeParam();
        ret.setParams(param.getParams());
        ret.setFromPath(param.getFromPath());
        ret.setToPath(param.getToPath());
        ret.setType(param.getType());

        ret.setNormalizer(normalizer);
        ret.setGetter(valueGetter);
        ret.setSetter(setter);

        return ret;
    }
}
