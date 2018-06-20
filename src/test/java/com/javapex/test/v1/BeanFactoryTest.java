package com.javapex.test.v1;

import com.javapex.beans.BeanDefinition;
import com.javapex.beans.factory.BeanCreationException;
import com.javapex.beans.factory.BeanDefinitionStoreException;
import com.javapex.beans.factory.BeanFactory;
import com.javapex.beans.factory.support.DefaultBeanFactory;
import com.javapex.beans.factory.xml.XmlBeanDefinitionReader;
import com.javapex.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;
    @Before
    public void setUp(){
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }
    @Test
    public void testGetBean() {

        reader.loadBeanDefinitions("petstore-v1.xml");

        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");
        assertEquals("com.javapex.service.v1.PetStoreService",beanDefinition.getBeansClassName());
        PetStoreService petStoreService = (PetStoreService)factory.getBean("petStore");
        assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidBean() {
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

            reader.loadBeanDefinitions("eeeeeeeeeeee.xml");

            //new DefaltBeanFactory("eeeeeeeeeeee.xml");
        }catch (BeanDefinitionStoreException e){
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }
}
