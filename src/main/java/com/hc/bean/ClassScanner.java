package com.hc.bean;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class ClassScanner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassScanner.class);
    public static <T> Set<Class<T>> findAnnotatedClasses(String scanPackage, Class<? extends Annotation>  annotation,Class<T> clazz)  {
        Preconditions.checkNotNull(annotation);
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(annotation));
        Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents(scanPackage);
        Set<Class<T>> ret = new HashSet<>(beanDefinitionSet.size());
        for(BeanDefinition beanDefinition : beanDefinitionSet){
            try {
                Class cls = Class.forName(beanDefinition.getBeanClassName());
                if(clazz.isAssignableFrom(cls)){
                    ret.add((Class<T>)cls);
                }
            } catch (ClassNotFoundException e) {
                LOGGER.warn("matcher class {} load failed",beanDefinition.getBeanClassName());
            }
        }
        return ret;
    }

}
