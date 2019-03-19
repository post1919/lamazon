package com.lamazon.filter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * 공통 컨트롤러 인터셉터 - 컨트롤러 실행 전/후에 항상 실행됨
 */
public class SuccessLoggingInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SuccessLoggingInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if( logger.isInfoEnabled() ) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			logger.info("[SUCCESS CONTROLLER] {}.{}", method.getDeclaringClass().getSimpleName(), method.getName());
		}
		//super.postHandle(request, response, handler, modelAndView);
	}
}
