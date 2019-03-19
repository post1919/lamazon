package com.lamazon.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@PropertySource ({"classpath:lamazon.properties","classpath:lamazon_db.properties","classpath:lamazon_message_ko.properties","classpath:lamazon_message_en.properties"})
public class PropertiesConfig {

	/*
	 *  프로퍼티 값(JVM 시스템 프로퍼티, 환경 변수, 프로퍼티 파일에 정의한 값)을
	 *  DI 컨테이너에서 관리하는 컴포넌트에 인젝션하기 위한 컴포넌트
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/*
	 * Properties파일 메세지 관리 셋팅
	 */
	//한국어
	@Bean
	public MessageSource messageSourceKo() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		//클래스패스 상에 있는 프로퍼티 파일의 이름을 확장자를 제외하고 지정한다.
		messageSource.setBasenames("lamazon_message_ko");

		//properties파일을 UTF-8로 인코딩
		messageSource.setDefaultEncoding("UTF-8");

		return messageSource;
	}

	//영어
	@Bean
	public MessageSource messageSourceEn() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		//클래스패스 상에 있는 프로퍼티 파일의 이름을 확장자를 제외하고 지정한다.
		messageSource.setBasenames("lamazon_message_en");

		//properties파일을 UTF-8로 인코딩
		messageSource.setDefaultEncoding("UTF-8");

		return messageSource;
	}
}
