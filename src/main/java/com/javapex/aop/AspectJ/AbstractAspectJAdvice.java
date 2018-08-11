package com.javapex.aop.AspectJ;

import com.javapex.aop.Advice;
import com.javapex.aop.Pointcut;

import java.lang.reflect.Method;

public abstract class AbstractAspectJAdvice implements Advice {


    protected Method adviceMethod;
    protected AspectJExpressionPointcut pointcut;
    protected Object adviceObject;



    public AbstractAspectJAdvice(Method adviceMethod,
                                 AspectJExpressionPointcut pointcut,
                                 Object adviceObject){

        this.adviceMethod = adviceMethod;
        this.pointcut = pointcut;
        this.adviceObject = adviceObject;
    }


    public void invokeAdviceMethod() throws  Throwable{

        adviceMethod.invoke(adviceObject);
    }
    public Pointcut getPointcut(){
        return this.pointcut;
    }
    public Method getAdviceMethod() {
        return adviceMethod;
    }
}
