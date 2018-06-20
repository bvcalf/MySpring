package com.javapex.test.v1;

import com.javapex.beans.BeanDefinition;
import com.javapex.beans.factory.BeanCreationException;
import com.javapex.beans.factory.BeanDefinitionStoreException;
import com.javapex.beans.factory.BeanFactory;
import com.javapex.beans.factory.support.DefaultBeanFactory;
import com.javapex.beans.factory.xml.XmlBeanDefinitionReader;
import com.javapex.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanFactoryTest {

    @Test
    public void testGetBean() {

        //BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("petstore-v1.xml");

        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");
        assertEquals("com.javapex.service.v1.PetStoreService",beanDefinition.getBeansClassName());
        PetStoreService petStoreService = (PetStoreService)factory.getBean("petStore");
        assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidBean() {
        //BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("petstore-v1.xml");
        try {
            factory.getBean("invalidBean");
        }catch (BeanCreationException e){
            return;
        }
        Assert.fail("expect BeanCreationException");
    }

    @Test
    public void testInvalidXml() {
        try {
            DefaultBeanFactory factory = new DefaultBeanFactory();
            XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
            reader.loadBeanDefinitions("eeeeeeeeeeee.xml");

            //new DefaltBeanFactory("eeeeeeeeeeee.xml");
        }catch (BeanDefinitionStoreException e){
            return;
        }
        Assert.fail("expect BeanDefinitonStoreException");
    }
}
