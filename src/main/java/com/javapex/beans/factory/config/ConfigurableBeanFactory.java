package com.javapex.beans.factory.config;

import com.javapex.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory {

    void setBeanClassLoader(ClassLoader beanClassLoader);

    ClassLoader getBeanClassLoader();

}
