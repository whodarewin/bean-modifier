package com.hc.bean.checker.parser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hc.bean.IParser;
import com.hc.bean.bean.getter.IValueGetter;
import com.hc.bean.bean.getter.ValueGetterFactory;
import com.hc.bean.checker.param.BeanCheckerParam;
import com.hc.bean.checker.param.BeanCheckerParamModel;
import com.hc.bean.checker.param.SpecialBeanCheckParam;
import com.hc.bean.checker.param.SpecialBeanCheckParamModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpecilaBeanCheckParamParser implements IParser<List<SpecialBeanCheckParam>> {
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<SpecialBeanCheckParam> parse(String json) throws IOException {
        JavaType javaType = this.getJavaType(List.class,SpecialBeanCheckParamModel.class);
        List<SpecialBeanCheckParamModel> bparam = objectMapper.readValue(json, javaType);
        List<SpecialBeanCheckParam> ret = new ArrayList<>(bparam.size());
        for(SpecialBeanCheckParamModel model : bparam) {
            BeanCheckerParamModel conditionModel = model.getCondition();
            List<BeanCheckerParamModel> actionModels = model.getActions();
            List<BeanCheckerParam> actions = new ArrayList<>();
            for (BeanCheckerParamModel action : actionModels){

                String actionPattern = action.getPath();
                IValueGetter actionGetter = ValueGetterFactory.getValueGetter(actionPattern);
                BeanCheckerParam actionParam = new BeanCheckerParam(actionGetter,
                        action.getPath(), action.getValue(), action.getType());
                actions.add(actionParam);

            }
            String conditionPattern = conditionModel.getPath();
            IValueGetter conditionGetter = ValueGetterFactory.getValueGetter(conditionPattern);
            BeanCheckerParam conditonParam = new BeanCheckerParam(conditionGetter,
                    conditionModel.getPath(), conditionModel.getValue(), conditionModel.getType());
            SpecialBeanCheckParam param = new SpecialBeanCheckParam(conditonParam, actions);
            ret.add(param);

        }

        return ret;

    }

    private JavaType getJavaType(Class collectionClass,Class elementClasses){
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
