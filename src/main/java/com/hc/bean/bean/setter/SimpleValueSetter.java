package com.hc.bean.bean.setter;

import com.hc.bean.PathUtil;
import com.hc.bean.bean.getter.IValueGetter;
import com.hc.bean.bean.getter.SimpleValueGetter;
import com.hc.bean.bean.getter.ValueGetterFactory;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
* @Description:    简单的对bean取值然后set新的值,每一个待修改的配置都有一个setter对应
* @Author:         hancongcong
* @CreateDate:     18-5-28 下午5:22
* @Version:        1.0
*/
public class SimpleValueSetter implements IValueSetter{
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleValueGetter.class);
    private static final String PREFIX = "set";
    private IValueGetter getter;
    private String name;
    private String setMethodName;

    public SimpleValueSetter(String path) {
        String parentPath = PathUtil.getParentPath(path);
        String propertyName = PathUtil.getLastPath(path);
        this.getter = ValueGetterFactory.getValueGetter(parentPath);
        this.name = propertyName;
        setMethodName = PREFIX + name.substring(0, 1).toUpperCase() +
                name.substring(1, name.length());
    }

    @Override
    public void set(Object bean, Object value) throws Exception {
        Object innerBean = getter.get(bean);
        if(innerBean == null || value == null){
            return;
        }

        Field field = innerBean.getClass().getDeclaredField(name);
        if (!field.getType().isAssignableFrom(value.getClass())) {
            throw new Exception("field not match:"+field.getType().getName()+"-"+value.getClass().getName());
        }

        Method method = MethodUtils.getAccessibleMethod(innerBean.getClass(), setMethodName, field.getType());
        if (method == null) {
            LOGGER.error("method is null ,method name is {}", setMethodName);
            return;
        }

        method.invoke(innerBean, value);
    }
}
