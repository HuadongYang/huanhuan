package com.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@Aspect
public class InputParamRecordAop {
	
	@Pointcut("@annotation(com.annotation.ControllerAnnotation)")
	public void annotateCut(){}
	
	@Before("annotateCut()")
	public void beforeAnnotate(JoinPoint point){
		System.out.println("进入之前");
		
		Object[] args = point.getArgs();
		Object target = point.getTarget();
		
		Signature signature = point.getSignature();
		
		MethodSignature methodSignature = (MethodSignature)signature;    
		Method targetMethod = methodSignature.getMethod();    
		
		RequestMapping url = targetMethod.getAnnotation(RequestMapping.class);
		System.out.println("url 是 ： " + url.value()[0]);
		
		
		for (int i = 0; i < args.length; i++) {
			System.out.println("参数" + args[i] );
		}
	}
}
