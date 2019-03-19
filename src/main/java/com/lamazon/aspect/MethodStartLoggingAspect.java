package com.lamazon.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;

/*@Aspect
@Component*/
public class MethodStartLoggingAspect {
	@Before("execution(* *..*Controller.*(..))")
	public void startLog(JoinPoint jp) {

		System.out.println("jp.getTarget() : " + jp.getTarget());
		System.out.println("jp.getThis() : " + jp.getThis());
		System.out.println("jp.getArgs() : " + jp.getArgs()[0].toString());

		for (Object aa : jp.getArgs()) {
			System.out.println("aa : " + aa);
		}

		System.out.println("메서드 시작 : " + jp.getSignature().getName());
	}
}
