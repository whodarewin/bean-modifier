package com.hc.bean.checker.param;

import com.hc.bean.bean.getter.IValueGetter;
import com.hc.bean.bean.getter.ValueGetterFactory;

import java.io.Serializable;

public class BeanCheckerParam implements Serializable {
    //filed name : simple name,name:foreach:name,dynamic:
    private IValueGetter valueGetter;
    private String path;
    //final match rule
    private Object value;
    //是否是OR关系
    //private boolean isOr = false;
    //match type bigger lower container
    //TODO:type转换成IMatcher
    private String type;

    public BeanCheckerParam(IValueGetter valueGetter, String path, Object value, String type) {
        this.valueGetter = valueGetter;
        this.path = path;
        this.value = value;
        this.type = type;
    }

    public IValueGetter getValueGetter() {
        return valueGetter;
    }

    public void setValueGetter(IValueGetter valueGetter) {
        this.valueGetter = valueGetter;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static BeanCheckerParam of(BeanCheckerParamModel model){
        String actionPattern = model.getPath();
        IValueGetter actionGetter = ValueGetterFactory.getValueGetter(actionPattern);
        return new BeanCheckerParam(actionGetter,
                model.getPath(), model.getValue(), model.getType());

    }

    @Override
    public String toString() {
        return "BeanCheckerParam{" +
                "path='" + path + '\'' +
                ", value=" + value +
                ", type='" + type + '\'' +
                '}';
    }
}
