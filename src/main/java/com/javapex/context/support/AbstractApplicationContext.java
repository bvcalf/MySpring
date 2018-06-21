package com.javapex.context.support;

import com.javapex.beans.factory.support.DefaultBeanFactory;
import com.javapex.beans.factory.xml.XmlBeanDefinitionReader;
import com.javapex.context.ApplicationContext;
import com.javapex.core.io.FileSystemResource;
import com.javapex.core.io.Resource;

public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory defaultBeanFactory = null;

    public AbstractApplicationContext(String configFile) {
        defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = this.getResourceByPath(configFile);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
    }

    public Object getBean(String beanID) {
        return defaultBeanFactory.getBean(beanID);
    }

    protected abstract Resource getResourceByPath(String path);

}
