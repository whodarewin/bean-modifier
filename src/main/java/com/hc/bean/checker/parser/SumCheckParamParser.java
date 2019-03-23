package com.hc.bean.checker.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hc.bean.IParser;
import com.hc.bean.bean.getter.IValueGetter;
import com.hc.bean.bean.getter.ValueGetterFactory;
import com.hc.bean.checker.param.BeanCheckerParam;
import com.hc.bean.checker.param.BeanCheckerParamModel;
import com.hc.bean.checker.param.SumCheckParam;
import com.hc.bean.checker.param.SumCheckParamModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SumCheckParamParser implements IParser<List<SumCheckParam>> {
    private ObjectMapper mapper =  new ObjectMapper();
    public List<SumCheckParam> parse(String json) throws IOException {
        List<SumCheckParamModel> params = mapper.readValue(json,
                mapper.getTypeFactory().constructParametricType(List.class, SumCheckParamModel.class));

        List<SumCheckParam> ret = new ArrayList<>();

        for(SumCheckParamModel model : params){
            BeanCheckerParamModel beanCheckerParamModel = model.getCondition();
            long count = model.getCount();

            IValueGetter valueGetter = ValueGetterFactory.getValueGetter(beanCheckerParamModel.getPath());

            BeanCheckerParam condition = new BeanCheckerParam(valueGetter,
                    beanCheckerParamModel.getPath(),beanCheckerParamModel.getValue(),beanCheckerParamModel.getType());

            SumCheckParam sumCheckParam = new SumCheckParam();
            sumCheckParam.setCondition(condition);
            sumCheckParam.setCount(count);
            ret.add(sumCheckParam);
        }

        return ret;
    }
}
