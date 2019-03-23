package com.hc.bean.normalizer.param;



import com.hc.bean.bean.getter.IValueGetter;
import com.hc.bean.bean.setter.IValueSetter;
import com.hc.bean.normalizer.IDataNormalizer;

import java.io.Serializable;
import java.util.List;

/**
* @Description:
* @Author:         hancongcong
* @CreateDate:     18-5-28 下午5:59
* @Version:        1.0
*/
public class BeanNormalizeParam implements Serializable {
    private List params;
    private String type;
    private String fromPath;
    private String toPath;

    private IDataNormalizer normalizer;
    private IValueGetter getter;
    private IValueSetter setter;

    public List getParams() {
        return params;
    }

    public void setParams(List params) {
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IValueGetter getGetter() {
        return getter;
    }

    public void setGetter(IValueGetter getter) {
        this.getter = getter;
    }

    public IValueSetter getSetter() {
        return setter;
    }

    public void setSetter(IValueSetter setter) {
        this.setter = setter;
    }

    public IDataNormalizer getNormalizer() {
        return normalizer;
    }

    public void setNormalizer(IDataNormalizer normalizer) {
        this.normalizer = normalizer;
    }

    public String getFromPath() {
        return fromPath;
    }

    public void setFromPath(String fromPath) {
        this.fromPath = fromPath;
    }

    public String getToPath() {
        return toPath;
    }

    public void setToPath(String toPath) {
        this.toPath = toPath;
    }
}
