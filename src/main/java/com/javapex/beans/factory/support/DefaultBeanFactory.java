package com.javapex.beans.factory.support;

import com.javapex.beans.BeanDefinition;
import com.javapex.beans.factory.BeanCreationException;
import com.javapex.beans.factory.BeanFactory;
import com.javapex.beans.factory.config.ConfigurableBeanFactory;
import com.javapex.util.ClassUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory,BeanDefinitionRegistry,ConfigurableBeanFactory {
    private ClassLoader beanClassLoader;
    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    public DefaultBeanFactory() {

    }

    public BeanDefinition getBeanDefinition(String beanID) {
        return this.beanDefinitionMap.get(beanID);
    }

    public void registerBeanDefinition(String beanID, BeanDefinition bd) {
        this.beanDefinitionMap.put(beanID, bd);
    }

    public Object getBean(String beanID) {
        BeanDefinition beanDefinition = this.getBeanDefinition(beanID);
        if (beanDefinition == null){
            throw new BeanCreationException("BeanDefinition does not exist");
        }
        ClassLoader classLoader = this.getBeanClassLoader();
        String beansClassName = beanDefinition.getBeansClassName();
        try {
            Class<?> clz = classLoader.loadClass(beansClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for "+ beansClassName +" failed",e);
        }

    }

    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }
}
