package com.javapex.context.support;

import com.javapex.beans.BeanDefinition;
import com.javapex.beans.factory.support.DefaultBeanFactory;
import com.javapex.beans.factory.xml.XmlBeanDefinitionReader;
import com.javapex.context.ApplicationContext;

public class ClassPathXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory defaultBeanFactory;

    public ClassPathXmlApplicationContext(String configFile) {
        defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(configFile);
    }

    public Object getBean(String beanID) {
        return defaultBeanFactory.getBean(beanID);
    }
}
