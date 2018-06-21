package com.javapex.context.support;

import com.javapex.beans.factory.support.DefaultBeanFactory;
import com.javapex.beans.factory.xml.XmlBeanDefinitionReader;
import com.javapex.context.ApplicationContext;
import com.javapex.core.io.Resource;
import com.javapex.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private ClassLoader beanClassLoader;
    private DefaultBeanFactory defaultBeanFactory = null;

    public AbstractApplicationContext(String configFile) {
        defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = this.getResourceByPath(configFile);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        defaultBeanFactory.setBeanClassLoader(this.getBeanClassLoader());//TODO 有什么问题？
    }

    public Object getBean(String beanID) {
        return defaultBeanFactory.getBean(beanID);
    }

    protected abstract Resource getResourceByPath(String path);

    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }
}